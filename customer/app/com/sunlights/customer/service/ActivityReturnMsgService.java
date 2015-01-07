package com.sunlights.customer.service;

/**
 * Created by tangweiqun on 2014/12/23.
 */
public interface ActivityReturnMsgService {

    /**
     * 获取活动中各种场景的返回给客户端的模版信息
     *
     * @param scene 场景代码  包括活动场景和兑换场景
     * @param type  获取的话对应着活动类型，兑换对应着兑换类型
     * @param rewardType    奖励类型
     * @param category  种类  1 展示  2发送消息  3奖励交易
     * @param errorCode 错误码
     * @return  模版信息
     */
    public String getReturnMsg(String scene, String type, String rewardType, String category, String errorCode);

}
