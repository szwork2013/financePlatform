package com.sunlights.common.utils;

import org.junit.Test;
import play.test.WithApplication;

import javax.imageio.ImageIO;
import java.io.File;

public class QRcodeByteTest extends WithApplication {

    @Test
    public void testQRCodeCommon() throws Exception {
        String userHome = System.getProperty("user.home");
        File imgFile = new File(userHome + "/qrcode.jpg");
        QRcodeByte qRcodeByte = new QRcodeByte();

        ImageIO.write(qRcodeByte.qRCodeCommon("www.baidu.com", 7), "jpg", imgFile);
    }
}