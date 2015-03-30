package com.sunlights.op.vo.statistics;

/**
 * Created by tangweiqun on 2015/1/13.
 */
public class RecommenderStaVo {

    private String recommenderName;

    private Integer recommendSucc;

    private String purchaseAmt;

    private Integer registerPeoples;

    private Integer unPurchasePeoples;

    public String getRecommenderName() {
        return recommenderName;
    }

    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommenderName;
    }

    public Integer getRecommendSucc() {
        return recommendSucc;
    }

    public void setRecommendSucc(Integer recommendSucc) {
        this.recommendSucc = recommendSucc;
    }

    public String getPurchaseAmt() {
        return purchaseAmt;
    }

    public void setPurchaseAmt(String purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
    }

    public Integer getRegisterPeoples() {
        return registerPeoples;
    }

    public void setRegisterPeoples(Integer registerPeoples) {
        this.registerPeoples = registerPeoples;
    }

    public Integer getUnPurchasePeoples() {
        return unPurchasePeoples;
    }

    public void setUnPurchasePeoples(Integer unPurchasePeoples) {
        this.unPurchasePeoples = unPurchasePeoples;
    }
}
