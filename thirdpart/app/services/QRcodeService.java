package services;

import com.sunlights.common.utils.QRcodeByte;
import com.sunlights.common.vo.QRcodeVo;
import play.Logger;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: QRcodeService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class QRcodeService {
    public QRcodeVo getQRcodeVo(String shorturl) {
        QRcodeByte qrcode = new QRcodeByte();        //将内容存入对象
        byte[] pngData = qrcode.generateQRCode(shorturl);//加入短路径,如："http://t.cn/RzJWtFA"
        QRcodeVo qRcodeVo = new QRcodeVo();
        qRcodeVo.setQrcodeByte(pngData);
        Logger.debug("图片二进制流:" + qRcodeVo.getQrcodeByte());
        return qRcodeVo;
    }
}
