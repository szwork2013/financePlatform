package com.sunlights.customer;

/**
 * Created by Administrator on 2014/12/2.
 */
public interface ActivityConstant {
    /**
     *  活动相关实体的状态--表示正常状态
     */
    String ACTIVITY_STATUS_NOMAL = "N";
    /**
     *  活动相关实体的状态--表示禁止状态
     */
    String ACTIVITY_STATUS_FORBIDDEN = "Y";

    /**
     * 活动中的签到场景的编码
     */
    String ACTIVITY_SIGNIN_SCENE_CODE = "ASC001";

    /**
     * 活动中的邀请朋友场景的编码
     */
    String ACTIVITY_INVITE_SCENE_CODE = "ASC002";

    /**
     * 活动中的图片活动场景的编码
     */
    String ACTIVITY_PICTURE_SCENE_CODE = "ASC003";

    /**
     * 活动中的注册场景的编码
     */
    String ACTIVITY_REGISTER_SCENE_CODE = "ASC004";

    /**
     * 活动中的首次购买的编码
     */
    String ACTIVITY_FIRST_PURCHASE_SCENE_CODE = "ASC005";

    /**
     * 购买推荐场景
     */
    String ACTIVITY_PURCHASE_RECOMMEND_SCENE_CODE = "ASC006";

    /**
     * 购买场景
     */
    String ACTIVITY_PURCHASE_SCENE_CODE = "ASC007";

    



    /**
     * 兑换场景
     */
    String ACTIVITY_EXCHANGE_RED_PACKET_SCENE_CODE = "EXC001";//红包取现

    /**
     * 以下是活动类型
     */
    String ACTIVITY_TYPE_REGISTER = "ATT001";
    String ACTIVITY_TYPE_FIRST_PURCHASE = "ATT002";
    String ACTIVITY_TYPE_PURCHASE = "ATT003";
    String ACTIVITY_TYPE_SIGNIN = "ATT004";

    /**
     * 生成奖励流水的时候操作--获取
     */
    Integer REWARD_FLOW_OBTAIN = 1;
    /**
     * 生成奖励流水的时候操作--兑换
     */
    Integer REWARD_FLOW_EXCHANGE = 2;

    /**
     * 奖励流水的状态
     */
    Integer REWARD_FLOW_STATUS_NOT_OBTAIN = 1;
    Integer REWARD_FLOW_STATUS_ALREADY_OBTAIN = 2;
    Integer REWARD_FLOW_STATUS_FAIL_OBTAIN = 3;
    Integer REWARD_FLOW_STATUS_WITHDRAW_SUCC = 4;
    Integer REWARD_FLOW_STATUS_WITHDRAW_FAIL = 5;
    Integer REWARD_FLOW_STATUS_ALREADY_EXCHANGE = 6;

    /**
     * 奖励类型
     */
    String REWARD_TYPE_JINDOU = "ART00J";


    String ACTIVITY_CUSTONER_STATUS_NOMAL = "N";

    String ACTIVITY_CUSTONER_STATUS_FORBIDDEN = "F";


    int ACCOUNT_COMMON_ZERO = 0;

    int ACCOUNT_COMMON_ONE = 1;

    String TRADE_TYPE_PURCHASE = "0";
    String TRADE_TYPE_REDEEM = "1";

}