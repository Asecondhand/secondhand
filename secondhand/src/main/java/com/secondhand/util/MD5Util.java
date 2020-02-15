package com.secondhand.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author Erica
 * @since 2020/2/3
 */
public class MD5Util {

    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";

    /**  循环次数 */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

}
