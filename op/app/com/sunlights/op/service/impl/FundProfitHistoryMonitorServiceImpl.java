package com.sunlights.op.service.impl;

import com.sunlights.common.dal.FundProfitHistoryDao;
import com.sunlights.common.dal.impl.FundProfitHistoryDaoImpl;
import com.sunlights.op.service.FundEquityMonitorService;
import models.FundProfitHistory;
import org.joda.time.DateTime;
import play.Logger;
import play.db.jpa.JPA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.sunlights.op.common.constants.MonitorConstants.*;

/**
 * Created by guxuelong on 2014/12/12.
 */
public class FundProfitHistoryMonitorServiceImpl extends FundEquityMonitorService implements Runnable {

    @Override
    public void run() {
        JPA.withTransaction(new play.libs.F.Callback0() {
            public void invoke() {
                Logger.info("FundProfitHistory monitor has started successfully");
                // 获取当日更新的数据列表
                List<FundProfitHistory> list = getUpdatedFundProfitHistoriesOfToday();
                // 判断数据是否在阀值区间内,返回异常数据
                List<String> msgList = monitor(list);
                // 发送提醒邮件
                if(msgList.size() != 0) {
                    sendEmail(msgList);
                }
            }
        });
    }

    private List<String> monitor(List<FundProfitHistory> list) {
        List<String> msgList = new ArrayList<>();
        for (FundProfitHistory history : list) {
            monitorPercentSevenDays(history, msgList);
            monitorIncomePerTenThousand(history, msgList);
        }
        return msgList;
    }

    private List<FundProfitHistory> getUpdatedFundProfitHistoriesOfToday() {
        FundProfitHistoryDao dao = new FundProfitHistoryDaoImpl();
        return dao.findByUpdateDate(new DateTime().toString("yyyy-MM-dd"));
    }

    private void monitorIncomePerTenThousand(FundProfitHistory history, List<String> msgList) {
        // 万份收益阀值(大于0；小于等于10)
        BigDecimal incomePerTenThousand = history.getIncomePerTenThousand();
        if (isExceedsThreshold(incomePerTenThousand, THRESHOLD_IPTT_MIN, THRESHOLD_IPTT_MAX)) {
            msgList.add(getEmailContent(history.getFundCode(), history.getFundname(), history.getDateTime(),
                    MONITOR_TYPE_IPTT, getDecimalStr(incomePerTenThousand)));
        }
    }

    private void monitorPercentSevenDays(FundProfitHistory history, List<String> msgList) {
        // 七日年化(大于0；小于等于15%)
        BigDecimal percentSevenDays = history.getPercentSevenDays();
        if (isExceedsThreshold(percentSevenDays, THRESHOLD_PSD_MIN, THRESHOLD_PSD_MAX)) {
            msgList.add(getEmailContent(history.getFundCode(), history.getFundname(), history.getDateTime(),
                    MONITOR_TYPE_PSD, getDecimalStr(percentSevenDays)));
        }
    }
}