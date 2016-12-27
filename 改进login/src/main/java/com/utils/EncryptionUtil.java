package com.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Gunn on 2016/12/20.
 */
public class EncryptionUtil {
    public static String base64Encode(String data){
        return Base64.encodeBase64String(data.getBytes());
    }

    public static String base64Decode(String data) throws UnsupportedEncodingException{
        return new String(Base64.decodeBase64(data.getBytes()), "utf-8");
    }

    public static String md5Hex(String data){
        return DigestUtils.md5Hex(data);
    }

    public static String sha1Hex(String data){
        return DigestUtils.sha1Hex(data);
    }

    public static String sha256Hex(String data){
        return DigestUtils.sha256Hex(data);
    }

}
