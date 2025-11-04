package com.vijay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RollNumberGenerator {
    private static final Logger log = LoggerFactory.getLogger(RollNumberGenerator.class);

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * generateRollNumber method is used to generate 5 random alphanumeric chars
     *
     * @param name this request contains such as name
     * @return string
     */
    public static String generateRollNumber(String name) {
        log.info("Inside generateRollNumber method with request name {}", name);
        String prefix = name.length() >= 3 ? name.substring(0, 3).toUpperCase() : name.toUpperCase();
        String randomPart = getRandomAlphaNumeric(5); // generate 5 random alphanumeric chars
        return prefix + randomPart;
    }

    /**
     * getRandomAlphaNumeric method is used to append alphanumerical value
     *
     * @param length this request contains such as length
     * @return string
     */
    private static String getRandomAlphaNumeric(int length) {
        log.info("Inside getRandomAlphaNumeric method with request length {}", length);
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

}

