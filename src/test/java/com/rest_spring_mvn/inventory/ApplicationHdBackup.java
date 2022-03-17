package com.rest_spring_mvn.inventory;

public class ApplicationHdBackup {
    public Integer getRecordCount(String s) {
        if (s.length() < 20 || 'Z' != s.charAt(0)) return null;
        return Integer.parseInt(s.substring(13, 20));
    }
}
