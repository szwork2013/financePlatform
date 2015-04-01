package com.sunlights.op.vo.statistics;

/**
 * Created by Administrator on 2015/1/14.
 */
public class RecommenderInfoVo {

    private String recommenderNo;

    private String mobile;

    private String realName;

    private String customerId;

    private String recommondPhone;

    private String cbRealName;

    private String cbMobile;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRecommenderNo() {
        return recommenderNo;
    }

    public void setRecommenderNo(String recommenderNo) {
        this.recommenderNo = recommenderNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRecommondPhone() {
        return recommondPhone;
    }

    public void setRecommondPhone(String recommondPhone) {
        this.recommondPhone = recommondPhone;
    }

    public String getCbRealName() {
        return cbRealName;
    }

    public void setCbRealName(String cbRealName) {
        this.cbRealName = cbRealName;
    }

    public String getCbMobile() {
        return cbMobile;
    }

    public void setCbMobile(String cbMobile) {
        this.cbMobile = cbMobile;
    }
}
