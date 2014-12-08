package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.QRcodeByte;
import com.sunlights.common.utils.ShortURLUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.impl.CustJoinActivityServiceImpl;
import com.sunlights.customer.vo.ActivityParamter;
import com.sunlights.customer.vo.QRcodeVo;
import com.sunlights.customer.vo.ShareVo;
import models.Activity;
import models.Customer;
import models.CustomerSession;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Result;
import sun.misc.BASE64Encoder;

import java.util.List;

/**
 * Created by Administrator on 2014/12/3.
 */
@Transactional
public class ShareContorller extends ActivityBaseController{
    private ActivityService activityService =new ActivityServiceImpl();
    private CustJoinActivityService custjoinService=new CustJoinActivityServiceImpl();
    /**
     * 分享好友
     *
     * @return
     */
    public Result sendFriend() {

        //1、首先获得手机号
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();//获得客户id
        if(StringUtils.isEmpty(custNo)){
            return notFound("用户登录已经超时,请重新登录");
        }
        String mobile= getMobile(custNo);//获得手机号

        //2、获得短路径及获得分享描述内容
        ActivityParamter activityParamter = getActivityParamter();
        String scene = activityParamter.getScene();
        if(StringUtils.isEmpty(scene)){
            scene= ActivityConstant.ACTIVITY_INVITE_SCENE_CODE;//邀请好友配置场景
        }
        List<Activity> list=activityService.getActivityByScene(scene);
        if(list.size()<1){
            return notFound("暂时没有活动");
        }
        Activity activity=list.get(0);
        String url=activity.getShareUrl();//活动路径
        Logger.debug("获得的活动路径url为:"+url);
        String sharetext=activity.getShareText();//获得分享描述内容
        Logger.debug("获得分享描述内容:"+sharetext);
        Long activatyid=activity.getId();//活动id
        String shorturl= getShortUrl(custNo,activatyid,scene,mobile,url);  //获得短路径
        if(StringUtils.isEmpty(shorturl)){
            return notFound("获取短路径失败");
        }
        //3、将内容存入对象
        ShareVo shareVo=new ShareVo();
        shareVo.setShorturl(shorturl);
        shareVo.setDescription(sharetext);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.SHARE_QUERY_SUCC), shareVo);
        Logger.debug("返回给前端的内容----》:"+messageUtil.toJson());
        return ok(messageUtil.toJson());
    }


    /**
     * 获得byte流图片
     * @return
     */
    public Result getQRcodeToByte(){

        //1、首先获得手机号
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();//获得客户id
        if(StringUtils.isEmpty(custNo)){
            return notFound("用户登录已经超时,请重新登录");
        }
        String mobile= getMobile(custNo);//获得手机号
        //2、获得短路径
        ActivityParamter activityParamter = getActivityParamter();
        String scene = activityParamter.getScene();
        if(StringUtils.isEmpty(scene)){
            scene= ActivityConstant.ACTIVITY_INVITE_SCENE_CODE;//邀请好友配置场景
        }
        List<Activity> list=activityService.getActivityByScene(scene);
        if(list.size()<1){
            return notFound("暂时没有活动");
        }
        Activity activity=list.get(0);
        String url=activity.getShareUrl();//活动路径
        Logger.debug("获得的活动路径url为:"+url);
        Long activatyid=activity.getId();//活动id
        String shorturl= getShortUrl(custNo,activatyid,scene,mobile,url);//获得短路径
        if(StringUtils.isEmpty(shorturl)){
            return notFound("获取短路径失败");
        }

        //3、将内容存入对象
        QRcodeByte qrcode = new QRcodeByte();
        byte[] pngData = qrcode.getQRcodeByte(shorturl);//加入短路径如："http://t.cn/RzJWtFA"
        QRcodeVo qRcodeVo=new QRcodeVo();
        qRcodeVo.setQrcodeByte(pngData);
        Logger.debug("图片二进制流:"+qRcodeVo.getQrcodeByte());

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ABOUT_QUERY_SUCC), qRcodeVo);
        Logger.debug("返回给前端的内容----:"+messageUtil.toJson());
        return ok(messageUtil.toJson());

    }

    /**
     * 获得手机号
     * @return
     */
    private String getMobile(String custNo){
        //1、首先获得手机号

        Customer customer=new CustomerDaoImpl().getCustomerByCustomerId(custNo);
        String mobile=customer.getMobile();//获得手机号
        Logger.debug("获得的手机号为:"+mobile);
        return mobile;
    }

    /**
     * 获取短链接或生成短链接
     * @param custNo
     * @param activatyid
     * @param scene
     * @param mobile
     * @param url
     * @return
     */
    private String getShortUrl(String custNo,Long activatyid,String scene,String mobile,String url){
        String shorturl=null;
        String getShortUrl=custjoinService.getShortUrl(custNo,activatyid,scene);
        Logger.debug("活动id:"+activatyid+",客户号："+custNo+"，数据库的短链接："+getShortUrl);
        if(StringUtils.isNotEmpty(getShortUrl)){//首先查询有无短链接（无则生成，有则直接拿）
            shorturl=getShortUrl;
            Logger.debug("数据库的短链接:"+shorturl);
            return shorturl;
        }else{
            StringBuffer bf=new StringBuffer();
            String longurl=bf.append(url).append("?mobileNo=").append(mobile).toString();//拼接成长链接
            Logger.debug("拼接后的长链接:"+longurl);
            //通过长链接生成短链接
            shorturl=ShortURLUtil.getShortURL(longurl);
            Logger.debug("生成的短链接:"+shorturl);
            custjoinService.saveShortUrl(custNo,activatyid,scene, shorturl);//保存短链接
            return shorturl;
        }

    }
}
