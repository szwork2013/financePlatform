package com.sunlights.common.utils;
import com.swetake.util.Qrcode;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRcodeByte {

    /**
     * 获得二维码的二进制流
     * @param content 需要生成二维码的文本内容
     * @return
     */
    public byte[] generateQRCode(String content){
        QRcodeByte handler = new QRcodeByte();
        return  handler.encoderQRCode(content,"png");
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 存储内容
     * @param imgType 图片类型
     */
    private byte[] encoderQRCode(String content, String imgType) {
        return this.encoderQRCode(content, imgType, 7);
    }

    /**
     * 生成二维码图片,返回byte
     * @param content 存储内容
     * @param imgType 图片类型
     * @param size 二维码尺寸
     */
    private byte[] encoderQRCode(String content, String imgType, int size) {
        ByteArrayOutputStream out=null;
        try {
            BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
            //此处将BufferedImage数据转换为二进制
            out=new ByteArrayOutputStream();
            ImageIO.write(bufImg,imgType,out);
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 生成二维码图片的公共方法
     * @param content 存储内容
     * @param imgType 图片类型
     * @param size 二维码尺寸
     * @return
     */
    private BufferedImage qRCodeCommon(String content, String imgType, int size) {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
            qrcodeHandler.setQrcodeVersion(size);
            // 获得内容的字节数组，设置编码格式
            byte[] contentBytes = content.getBytes("utf-8");
            // 图片尺寸
            int imgSize = 67 + 12 * (size - 1);
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
            // 设置背景颜色
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);

            // 设定图像颜色> BLACK
            gs.setColor(Color.BLACK);
            // 设置偏移量，不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容> 二维码
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }
            gs.dispose();
            bufImg.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImg;
    }

}
