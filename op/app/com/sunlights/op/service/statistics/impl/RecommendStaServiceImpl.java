package com.sunlights.op.service.statistics.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.statistics.RecommenderDao;
import com.sunlights.op.dal.statistics.impl.RecommenderDaoImpl;
import com.sunlights.op.service.statistics.RecommendStaService;
import com.sunlights.op.vo.statistics.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tangweiqun on 2015/1/13.
 */
public class RecommendStaServiceImpl implements RecommendStaService {

    private RecommenderDao recommenderDao = new RecommenderDaoImpl();

    @Override
    public List<CustomerInOutSummaryVo> countCustomerInOutSummary(PageVo pageVo) {
        //TODO
        List<CustomerInOutSummaryVo> customerInOutSummaryVos = recommenderDao.CustomerInOutSummaryVos(pageVo);
        return customerInOutSummaryVos;
    }

    @Override
    public List<PurchaseItemsVo> countPurchases(PageVo pageVo) {
        List<PurchaseInfoVo> purchaseInfoVos = recommenderDao.getCustomerPurchaseItems(pageVo);
        PurchaseItemsVo purchaseItemsVo = null;
        List<PurchaseItemsVo> purchaseItemsVos = Lists.newArrayList();
        for(PurchaseInfoVo purchaseInfoVo : purchaseInfoVos) {
            purchaseItemsVo = new PurchaseItemsVo();
            if(purchaseInfoVo.getCbMobile()!=null){
                purchaseItemsVo.setCbMobile(purchaseInfoVo.getCbMobile().substring(0,3)+"****"+purchaseInfoVo.getCbMobile().substring(7));
            }
            if(purchaseInfoVo.getMobile()!=null){
                purchaseItemsVo.setMobile(purchaseInfoVo.getMobile().substring(0,3)+"****"+purchaseInfoVo.getMobile().substring(7));
            }
            purchaseItemsVo.setRecommenderName(purchaseInfoVo.getRealName());
            purchaseItemsVo.setDate(CommonUtil.dateToString(purchaseInfoVo.getCreateTime(), CommonUtil.DATE_FORMAT_LONG ));
            purchaseItemsVo.setInAmt(purchaseInfoVo.getTradeAmt().toString());
            purchaseItemsVo.setPurchaseName(purchaseInfoVo.getCbRealName());
            purchaseItemsVo.setBankCardNum(StringUtils.isEmpty(purchaseInfoVo.getBankName()) ? 0 : 1);
            purchaseItemsVo.setBankNames(purchaseInfoVo.getBankName());
            purchaseItemsVos.add(purchaseItemsVo);
        }
        return purchaseItemsVos;
    }

    @Override
    public List<RecommenderStaVo> countRecommend(PageVo pageVo) {
        List<RecommenderInfoVo> recommenderInfoVos = recommenderDao.getRecommenderInfo();
        List<PurchaseInfoVo> purchaseInfoVos = recommenderDao.getCustomerPurchaseItems(pageVo);

        List<RecommenderStaVo> recommenderStaVos = Lists.newArrayList();
        RecommenderStaVo recommenderStaVo = null;

        for(RecommenderInfoVo recommenderInfoVo : recommenderInfoVos) {
            recommenderStaVo = new RecommenderStaVo();
            recommenderStaVo.setRecommenderName(recommenderInfoVo.getRealName() == null ? recommenderInfoVo.getRecommondPhone() : recommenderInfoVo.getRealName());
            recommenderStaVo.setRegisterPeoples(recommenderInfoVo.getCount());
            RecommenderStaVo temp = getPurchaseInfoVo(recommenderInfoVo.getRecommondPhone(), purchaseInfoVos);
            recommenderStaVo.setPurchaseAmt(temp.getPurchaseAmt());
            recommenderStaVo.setRecommendSucc(temp.getRecommendSucc());
            recommenderStaVo.setUnPurchasePeoples(recommenderInfoVo.getCount() - temp.getRecommendSucc());
            recommenderStaVos.add(recommenderStaVo);
        }

        return recommenderStaVos;
    }

    private RecommenderStaVo getPurchaseInfoVo(String recommondPhone, List<PurchaseInfoVo> purchaseInfoVos) {
        RecommenderStaVo recommenderStaVo = new RecommenderStaVo();
        BigDecimal totalAmt = BigDecimal.ZERO;
        Integer succRigisters = Integer.valueOf(0);

        for(PurchaseInfoVo purchaseInfoVo : purchaseInfoVos) {
            if(StringUtils.isEmpty(recommondPhone)) {
                if(StringUtils.isEmpty(purchaseInfoVo.getRecommendPhone())) {
                    succRigisters += 1;
                    totalAmt = totalAmt.add(purchaseInfoVo.getTotalAmt());
                }
            } else {
                if(recommondPhone.equals(purchaseInfoVo.getRecommendPhone())) {
                    totalAmt = totalAmt.add(purchaseInfoVo.getTotalAmt());
                    succRigisters += 1;
                }
            }
        }
        recommenderStaVo.setPurchaseAmt(totalAmt.toString());
        recommenderStaVo.setRecommendSucc(succRigisters);

        return recommenderStaVo;
    }

    @Override
    public List<Register4NotPurchaseVo> countRegisterNotPurchases(PageVo pageVo) {
        List<PurchaseInfoVo> purchaseInfoVos = recommenderDao.getCustomerPurchaseInfos(pageVo);

        Register4NotPurchaseVo register4NotPurchaseVo = null;
        List<Register4NotPurchaseVo> register4NotPurchaseVos = Lists.newArrayList();
        for(PurchaseInfoVo purchaseInfoVo : purchaseInfoVos) {
            register4NotPurchaseVo = new Register4NotPurchaseVo();
            register4NotPurchaseVo.setRecommenderName(purchaseInfoVo.getRealName() == null ? purchaseInfoVo.getMobile() : purchaseInfoVo.getRealName());
            register4NotPurchaseVo.setCustomerName(purchaseInfoVo.getCbRealName());
            register4NotPurchaseVo.setMobile(purchaseInfoVo.getCbMobile());
            register4NotPurchaseVos.add(register4NotPurchaseVo);
        }

        return register4NotPurchaseVos;
    }

}
