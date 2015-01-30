package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.QRcodeByte;
import com.sunlights.common.vo.Message;
import models.Customer;
import models.CustomerSession;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.mvc.Result;
import sun.misc.BASE64Encoder;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: QRcodeController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class QRcodeController extends ActivityBaseController {

    /**
     * @param token
     * @return
     */
    public Result getQRcode(String content, String token) {
        CommonUtil.getInstance().validateParams(content);
        String qrCode = generateQRCode(content, token);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ABOUT_QUERY_SUCC), qrCode);
        return ok(messageUtil.toJson());
    }

    private String generateQRCode(final String content, final String token) {
        String mobile = getMobile(token);
        String qrCodeContent = content;
        if (StringUtils.isNotEmpty(mobile)) {
            qrCodeContent += "?mobile=" + mobile;
        }

        Logger.info(">> " + qrCodeContent);
        QRcodeByte qrcode = new QRcodeByte();
        byte[] bytes = qrcode.generateQRCode(qrCodeContent);
        if(bytes == null || bytes.length == 0){
            return "";
        }else {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(bytes);
        }
    }

    private String getMobile(final String token) {
        if (StringUtils.isNotEmpty(token)) {
            CustomerSession customerSession = customerService.getCustomerSession(token);
            String custNo = customerSession.getCustomerId();//获得客户id
            Customer customer = customerService.getCustomerByCustomerId(custNo);
            return customer.getMobile();
        } else {
            return "";
        }
    }


}
