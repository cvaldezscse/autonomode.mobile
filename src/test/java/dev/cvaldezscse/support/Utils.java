package dev.cvaldezscse.support;

import java.security.SecureRandom;

/**
 * Utils
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class Utils {
    public static String randomString(int length) {

        final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        final String NUMBER = "0123456789";

        final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
}
