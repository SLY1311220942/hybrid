package com.sly.hybrid.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.DES;

/**
 * des 加解密工具类
 *
 * @author SLY
 * @date 2022/1/2
 */
public class DesUtil {

    public static String DES_KEY = "AVeN7pkf0RNhNAg4";

    private static final DES DES = new DES(Mode.ECB, Padding.PKCS5Padding, DES_KEY.getBytes());

    /**
     * 解密
     *
     * @param data 加密数据
     * @return {@link String}
     * @author SLY
     * @date 2022/1/2
     */
    public static String decryptStr(String data) {
        return DES.decryptStr(data);
    }

    /**
     * 加密
     *
     * @param data 数据
     * @return {@link String}
     * @author SLY
     * @date 2022/1/2
     */
    public static String encryptStr(String data) {
        return DES.encryptHex(data);
    }
}
