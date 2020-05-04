package com.learn.java;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 5, 7, 4, 9, 1, 3, 5, 7, 9};
        byte[] 
    }
}

class UserIp {
    private String userId;
    private String ipAddress;

    public String getUserId() {
        return userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserIp(String userId, String ipAddress) {
        this.userId = userId;
        this.ipAddress = ipAddress;
    }

    public UserIp() {
    }
}