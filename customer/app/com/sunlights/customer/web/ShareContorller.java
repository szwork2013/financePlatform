package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.QRcodeByte;

import com.sunlights.common.utils.ShortURLUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.vo.ShareVo;
import models.Activity;
import models.Customer;
import models.CustomerSession;
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
    /**
     * 获取短链接
     *
     * @return
     */
    public Result sendFriend() {

        //首先获得手机号
        CustomerSession customerSession = getCustomerSession();
        String custNo = customerSession.getCustomerId();
        Customer customer=new CustomerDaoImpl().getCustomerByCustomerId(custNo);
        String mobile=customer.getMobile();//获得手机号
        Logger.debug("获得的手机号为:"+mobile);
        //获得活动路径及获得分享描述内容
        String scene="ASC005";
        List<Activity> list=activityService.getActivityByScene(scene);
        Activity activity=list.get(0);
        String url=activity.getShareUrl();//活动路径
        Logger.debug("获得的活动路径url为:"+url);
        StringBuffer bf=new StringBuffer();
        String longurl=bf.append(url).append("?mobileNo=").append(mobile).toString();//拼接成长链接
        Logger.debug("拼接后的长链接:"+longurl);
        String sharetext=activity.getShareText();//获得分享描述内容
        Logger.debug("获得分享描述内容:"+sharetext);
        //通过长链接生成短链接
        String shorturl=ShortURLUtil.getShortURL(longurl);
        Logger.debug("生成的短链接:"+shorturl);
        //将内容存入对象
        ShareVo shareVo=new ShareVo();
        shareVo.setShorturl(shorturl);
        shareVo.setTemplate(sharetext);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.REWARD_QUERY_SUCC), shareVo);
        Logger.debug("返回给前端的内容----》:"+messageUtil.toJson());
        return ok(messageUtil.toJson());
    }

    /**
     * 获得base64编码的字符串图片
     * @param shortpath
     * @return
     */
    public static String getQRcodeToBase64(String shortpath){
        QRcodeByte qrcode = new QRcodeByte();
        byte[] pngData = qrcode.getQRcodeByte(shortpath);//加入短路径"http://t.cn/RzJWtFA"
        return  new BASE64Encoder().encode(pngData);
    }
}
