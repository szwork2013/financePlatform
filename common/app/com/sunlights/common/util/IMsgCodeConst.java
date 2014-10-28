package com.sunlights.common.util;


/**
 * Created by wangjiaming on 2014/9/26.
 */
public interface IMsgCodeConst {
    /***
     * XXX_XXXX
     *
     * _前为业务类型
     * _后4位为数字：第一位：0(info) 1(warn) 2(error业务逻辑) 3(fatal系统异常)
     *             第二位：业务代码
     *                    0 公用部分
     *                    1 注册登录
     *                    2 账户中心
     */


    /**
     * 操作成功
     */
    public static String COM_0000 = "0000";

    /**
     * 访问失败，参数为空
     */
    public static String COM_2001 = "2001";

    /**
     * 登录超时，请重新登录
     */
    public static String COM_2002 = "2002";

    /**
     * 参数未配置
     */
    public static String COM_2003 = "2003";
    /**
     * 参数不是数字类型
     */
    public static String COM_2004 = "2004";


    /**>>>>>>>>>>>>>>>>>>>>>>>>>>>注册登录>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
    /**
     * 注册成功
     */
    public static String LOG_0100 = "0100";
    /**
     * 登录成功
     */
    public static String LOG_0101 = "0101";
    /**
     * 修改密码成功
     */
    public static String LOG_0102 = "0102";
    /**
     * 手势密码设置成功
     */
    public static String LOG_0103 = "0103";
    /**
     * 登出成功
     */
    public static String LOG_0104 = "0104";


    /**
     * 该手机号未注册
     */
    public static String LOG_2100 = "2100";
    /**
     * 该手机号已注册
     */
    public static String LOG_2101 = "2101";
    /**
     * 验证码超过最大次数
     */
    public static String LOG_2102 = "2102";
    /**
     * 验证码不正确
     */
    public static String LOG_2103 = "2103";
    /**
     * 验证码超时失效
     */
    public static String LOG_2104 = "2104";

    /**
     * 未获取验证码
     */
    public static String LOG_2105 = "2105";
    /**
     * 密码错误次数过多,约xx分后再试
     */
    public static String LOG_2106 = "2106";
    /**
     * 密码错误，剩余次数xx
     */
    public static String LOG_2107 = "2107";
    /**
     * 手势密码错误，剩余次数xx
     */
    public static String LOG_2108 = "2108";
    /**
     * 手势登录失败超过最大次数 ，请换密码登录
     */
    public static String LOG_2109 = "2109";
    /**
     * 密码错误
     */
    public static String LOG_2110 = "2110";
    /**
     * 手势未设置
     */
    public static String LOG_2111 = "2111";
    /**
     * 验证码与设备号不匹配
     */
    public static String LOG_2112 = "2112";

    /**>>>>>>>>>>>>>>>>>>>>>>>>>>>账户中心>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
    /**
     * 交易密码重置成功
     */
    public static String ACC_0200 = "0200";
    /**
     * 认证成功
     */
    public static String ACC_0201 = "0201";

    /**
     * 真实姓名或身份证号错误
     */
    public static String ACC_2200 = "2200";
    /**
     * 认证失败，不一致/库中无此号，请重新输入
     */
    public static String ACC_2201 = "2201";
    /**
     * 交易密码错误
     */
    public static String ACC_2202 = "2202";

    /**
     * 实名认证失败
     */
    public static String ACC_3200 = "3200";

    /**
     * 不存在该账户
     */
    public static String ACC_4100 = "4100";


    /**
     * 协议编号不能为空
     */
    public static String AGREE_2300 = "2300";

    /**
     * 没有该协议
     */
    public static String AGREE_2301 = "2301";

    /**
     * 银行卡添加成功
     */
    public static String ABNK_0210 = "0210";

    /**
     * 银行卡删除成功
     */
    public static String ABNK_0211 = "0211";
    /**
     * 请先实名认证
     */
    public static String ABNK_2210 = "2210";
    /**
     * 该银行卡以被绑定，不能重复绑定
     */
    public static String ABNK_2211 = "2211";
    /**
     * 银行卡添加失败
     */
    public static String ABNK_2212 = "2212";
    /**
     * 银行卡删除失败
     */
    public static String ABNK_2213 = "2213";
    /**
     * 银行卡验证失败
     */
    public static String ABNK_2214 = "2214";
    /**
     * 银行不能为空
     */
    public static String ABNK_2215 = "2215";


}
