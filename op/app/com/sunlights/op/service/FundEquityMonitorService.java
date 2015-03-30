package com.sunlights.op.service;

import com.sunlights.op.common.util.ConfigUtil;
import com.sunlights.op.service.impl.EmailServiceImpl;
import com.sunlights.op.vo.EmailVo;
import org.joda.time.DateTime;
import play.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.sunlights.op.common.util.ConfigUtil.SMTP_SUBJECT;
import static com.sunlights.op.common.util.ConfigUtil.SMTP_TO;

/**
 * Created by guxuelong on 2014/12/12.
 */
public class FundEquityMonitorService{

    protected String getEmailContent(String fundCode, String FundName, Date dateTime, String type, String value) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("基金[");
        strBuilder.append(FundName);
        strBuilder.append("(");
        strBuilder.append(fundCode);
        strBuilder.append(")]");
        strBuilder.append(new DateTime(dateTime).toString("yyyy-MM-dd"));
        strBuilder.append("的");
        strBuilder.append(type);
        strBuilder.append("为");
        strBuilder.append(value);
        strBuilder.append("<br>");
        return strBuilder.toString();
    }


    protected String getEmailContents(List<String> msgList){
        StringBuilder strBuilder = new StringBuilder();
        for (String str : msgList) {
            strBuilder.append(str);
        }
        return strBuilder.toString();
    }

    protected void sendEmail(List<String> msgList) {
        EmailService service = new EmailServiceImpl();
        EmailVo email = new EmailVo();
        email.setSubject(ConfigUtil.getConfigValue(SMTP_SUBJECT));
        email.addTo(ConfigUtil.getConfigValue(SMTP_TO));
        email.setBodyHtml(getEmailContents(msgList));
        service.sendEmail(email);
        Logger.info("基金净值监控邮件发送成功");
    }

    protected boolean isExceedsThreshold(BigDecimal value, BigDecimal min, BigDecimal max) {
        if (value == null) {
            return true;
        }
        if (value.equals(min)) {
            return true;
        }
        if (min.compareTo(value) == 1 || max.compareTo(value) == -1) {
            return true;
        }
        return false;
    }

    protected String getDecimalStr(BigDecimal value){
        return value != null ? value.toPlainString():"null";
    }
}
