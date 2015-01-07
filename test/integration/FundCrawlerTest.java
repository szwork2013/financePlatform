package integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.utils.QRcodeByte;
import com.sunlights.common.utils.ShortURLUtil;

import org.junit.Test;
import play.libs.Json;
import sun.misc.BASE64Encoder;


import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class FundCrawlerTest {

    @Test
    public void testFilter() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                  //此处测试短链接！！！
//                ShortURLUtil shorturl = new ShortURLUtil();
//                String str=shorturl.getShortURL("http://www.baidu.com?code=121312312");
//                String str1 = str.replace("\"", ""); //去掉引号
//                System.out.print("====="+str1);

//
//                QRcodeByte qrcode = new QRcodeByte();
//                byte[] pngData = qrcode.generateQRCode("http://t.cn/RzJWtFA");//加入短路径
//                String base64=new BASE64Encoder().encode(pngData);
//                System.out.print("64===="+base64);
//                JsonNode json = Json.toJson(base64);
//                System.out.println("64二进制："+json.toString());


















            }
        });
    }
}