package com.sunlights.op.service.impl;

import com.sunlights.op.common.util.ConfigUtil;
import com.sunlights.op.service.EmailService;
import com.sunlights.op.vo.EmailVo;
import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;

import static com.sunlights.op.common.util.ConfigUtil.SMTP_FROM;

/**
 * Created by guxuelong on 2014/12/12.
 */
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(EmailVo emailVo) {
        final Email email = new Email();
        email.setSubject(emailVo.getSubject());
        email.setTo(emailVo.getTo());
        email.setBodyHtml(emailVo.getBodyHtml());
        email.setFrom(ConfigUtil.getConfigValue(SMTP_FROM));
        MailerPlugin.send(email);
    }
}
