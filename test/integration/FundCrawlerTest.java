package integration;

import org.junit.Test;
import play.test.WithApplication;

public class FundCrawlerTest extends WithApplication {

    @Test
    public void testFilter() throws Exception {

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
}