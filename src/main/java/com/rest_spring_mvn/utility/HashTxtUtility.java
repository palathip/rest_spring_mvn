package com.rest_spring_mvn.utility;

import lombok.var;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashTxtUtility {

    public static String build(String originalString) throws NoSuchAlgorithmException {
        var digest = MessageDigest.getInstance("SHA-384");
        String salt = "thisisasalt";
        byte[] hash = digest.digest(originalString.concat(salt).getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(hash));
    }
}
