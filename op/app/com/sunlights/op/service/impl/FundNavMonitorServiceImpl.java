package com.sunlights.op.service.impl;

import com.sunlights.common.dal.FundNavDao;
import com.sunlights.common.dal.impl.FundNavDaoImpl;
import com.sunlights.op.service.EmailService;
import com.sunlights.op.service.FundEquityMonitorService;
import models.FundNav;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.Logger;
import play.db.jpa.JPA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.sunlights.op.common.constants.MonitorConstants.*;

/**
 * Created by guxuelong on 2014/12/15.
 */
public class FundNavMonitorServiceImpl extends FundEquityMonitorService implements Runnable {

    private EmailService service = new EmailServiceImpl();
    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public void run() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            public void invoke() {
                Logger.info("FundNav monitor has started successfully");
                // 获取当日更新的数据列表
                List<FundNav> fundNavList = getUpdatedFundNavOfToday();
                // 判断数据是否在阀值区间内,返回异常数据
                List<String> msgList = monitor(fundNavList);
                // 发送提醒邮件
                if(msgList.size() != 0) {
                    sendEmail(msgList);
                }
            }
        });
    }

    private List<String> monitor(List<FundNav> fundNavList) {
        List<String> msgList = new ArrayList<>();
        for (FundNav fundNav : fundNavList) {
            monitorPurchaseLimitMin(fundNav, msgList);
            monitorLastestTotalAsset(fundNav, msgList);
            monitorIaGuid(fundNav, msgList);
            monitorRiskLevel(fundNav, msgList);
            monitorRapidRedeem(fundNav, msgList);
        }
        return msgList;
    }

    private List<FundNav> getUpdatedFundNavOfToday() {
        FundNavDao dao = new FundNavDaoImpl();
        return dao.queryByUpdateTime(new DateTime().toString("yyyy-MM-dd"));
    }

    private void monitorPurchaseLimitMin(FundNav fundNav, List<String> msgList) {
        BigDecimal purchaseLimitMin = fundNav.getPurchaseLimitMin();
        if (isExceedsThreshold(purchaseLimitMin, THRESHOLD_PLM_MIN, THRESHOLD_PLM_MAX)) {
            msgList.add(getEmailContent(fundNav.getFundcode(), fundNav.getFundname(),
                    DateTime.parse(fundNav.getCurrDate(), format).toDate(),
                    MONITOR_TYPE_PLM, getDecimalStr(purchaseLimitMin)));
        }
    }

    private void monitorLastestTotalAsset(FundNav fundNav, List<String> msgList) {
        BigDecimal lastestTotalAsset = fundNav.getLastestTotalAsset();
        if (THRESHOLD_LTA.compareTo(lastestTotalAsset) == 1) {
            msgList.add(getEmailContent(fundNav.getFundcode(), fundNav.getFundname(),
                    DateTime.parse(fundNav.getCurrDate(), format).toDate(),
                    MONITOR_TYPE_LTA, getDecimalStr(lastestTotalAsset)));
        }
    }

    private void monitorIaGuid(FundNav fundNav, List<String> msgList) {
        String iaGuid = fundNav.getIaGuid();
        if (iaGuid == null) {
            msgList.add(getEmailContent(fundNav.getFundcode(), fundNav.getFundname(),
                    DateTime.parse(fundNav.getCurrDate(), format).toDate(),
                    MONITOR_TYPE_COMPANY, iaGuid));
        }
    }

    private void monitorRiskLevel(FundNav fundNav, List<String> msgList) {
        List<String> riskLevelLst = new ArrayList();
        riskLevelLst.add(RISK_LEVEL_LOW);
        riskLevelLst.add(RISK_LEVEL_MID);
        riskLevelLst.add(RISK_LEVEL_HIGH);
        String riskLevel = fundNav.getRiskLevel();
        if (!riskLevelLst.contains(riskLevel)) {
            msgList.add(getEmailContent(fundNav.getFundcode(), fundNav.getFundname(),
                    DateTime.parse(fundNav.getCurrDate(), format).toDate(),
                    MONITOR_TYPE_RISK_LEVEL, riskLevel));
        }
    }

    private void monitorRapidRedeem(FundNav fundNav, List<String> msgList) {

        Integer redeem = fundNav.getRapidRedeem();
        if (!(redeem == GENERAL_REDEEM || redeem == FAST_REDEEM)) {
            msgList.add(getEmailContent(fundNav.getFundcode(), fundNav.getFundname(),
                    DateTime.parse(fundNav.getCurrDate(), format).toDate(),
                    MONITOR_TYPE_REDEEM, redeem != null ? redeem.toString() : "null"));
        }
    }
}
