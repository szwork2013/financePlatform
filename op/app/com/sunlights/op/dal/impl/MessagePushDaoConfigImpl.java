package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.MessagePushConfigDao;
import com.sunlights.op.vo.MessagePushConfigVo;
import models.MessagePushConfig;
import play.Logger;

import java.util.List;

/**
 * Created by yanghong on 2014/12/14.
 */
public class MessagePushDaoConfigImpl extends EntityBaseDao implements MessagePushConfigDao {
    private PageService pageService = new PageService();
    @Override
    public List<MessagePushConfigVo> findMessagePushConfig(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select new com.sunlights.op.vo.MessagePushConfigVo(c) from MessagePushConfig c");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and c.id = {id}~/ ");
        sb.append("  /~and c.remarks like {remarks}~/ ");
        sb.append(" order by c.createTime desc");


        List<MessagePushConfigVo> messageList = pageService.findXsqlBy(sb.toString(), pageVo);
        CommonService commonService = new CommonService();
        for(MessagePushConfigVo pushConfigVo :  messageList){
            pushConfigVo.setPlatformDes(commonService.findValueByCatPointKey(pushConfigVo.getPlatform()));
            pushConfigVo.setPushTypeDes(commonService.findValueByCatPointKey(pushConfigVo.getPushType()));
        }

        Logger.debug("messageList size= " + messageList.size());

        return messageList;
    }

    @Override
    public void update(MessagePushConfig messagePushConfig) {
        super.update(messagePushConfig);
    }

    @Override
    public void save(MessagePushConfig messagePushConfig) {
        super.create(messagePushConfig);
    }


}
