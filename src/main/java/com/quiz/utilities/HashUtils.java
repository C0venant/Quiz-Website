package com.quiz.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    /*
     * Given a byte[] array, produces a hex String, such as "234a6f". with 2 chars
     * for each byte in the array. (provided code)
     */
    public static String hexToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (int aByte : bytes) {
            int val = aByte;
            val = val & 0xff; // remove higher bits, sign
            if (val < 16)
                buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    /*
     * Method which hashes password
     * Returns byte[] array
     */
    public static byte[] generateHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());
        return md.digest();
    }
}
