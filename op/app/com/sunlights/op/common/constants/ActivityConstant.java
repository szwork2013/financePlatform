package com.sunlights.op.common.constants;

/**
 * Created by Administrator on 2014/12/5.
 */
public interface ActivityConstant {

    Integer COMMON_ZERO = 0;

    Integer COMMOM_ONE = 1;

    /**
     * 生成奖励流水的时候操作--获取
     */
    Integer REWARD_FLOW_OBTAIN = 1;
    /**
     * 生成奖励流水的时候操作--兑换
     */
    Integer REWARD_FLOW_EXCHANGE = 2;

	public static final String NOT_AUDIT_DESC = "未审核";
	public static final String AUDIT_PASS_DESC = "等待兑换";
	public static final String AUDIT_NOT_PASS_DESC = "审核不通过";
	public static final String EXCHANGEING_DESC = "兑换中";
	public static final String EXCHANGE_SUCC_DESC = "兑换成功";
	public static final String EXCHANGE_FAIL_DESC = "兑换失败";


    public static final String EXCHANGE_BEAN_SUC = "1";//兑换成功
    public static final String EXCHANGE_BEAN_FAIL = "2";//失败
    public static final String EXCHANGE_BEAN_MID = "3";//兑换金额 > 实际兑换金额


}
