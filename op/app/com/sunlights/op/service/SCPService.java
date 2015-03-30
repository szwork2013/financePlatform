package com.sunlights.op.service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import play.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public class SCPService {

    public static Connection getConnection(String host, String user, String password) {
        Connection connection = new Connection(host);
        try {
            connection.connect();
            boolean isAuthed = connection.authenticateWithPassword(user, password);
            Logger.debug("isAuthed====" + isAuthed);


            return connection;
        } catch (Exception e) {
            Logger.error("获取SCP Connection失败", e);
        }
        return null;
    }

    public static void uploadFile(String host, String user, String password, String remoteDir, File file, String fileName) {
        Connection connection = null;
        FileInputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        SCPClient client = null;
        try {
            connection = SCPService.getConnection(host, user, password);
            client = connection.createSCPClient();
            inputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            //转化为字节数组流
            while ((i = inputStream.read()) != -1) {
                byteArrayOutputStream.write(i);
            }
            // 把文件存在一个字节数组中
            byte[] fileDate = byteArrayOutputStream.toByteArray();
            client.put(fileDate, fileName, remoteDir, "0664");
        } catch (Exception e) {
            Logger.error("上传失败", e);
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
                connection.close();
            } catch (Exception e) {

            }

        }

    }
}
