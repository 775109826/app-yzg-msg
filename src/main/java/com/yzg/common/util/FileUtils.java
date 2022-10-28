package com.yzg.common.util;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件工具类
 */
public class FileUtils {

    public static void mkdirsFilePath(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) file.mkdirs();
    }

    /**
     * 获取文件的base64
     *
     * @param file
     * @return
     */
    public static String getFileBase64(File file) {
        byte[] data = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 对字节数组进行Base64编码，得到Base64编码的字符串
        return Base64.encodeBase64String(data).replaceAll("\r|\n", "");
    }

    /**
     * 获取文件的MD5
     * @param file
     * @return
     */
    public static String getFileMd5(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            return bigInt.toString(16);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
