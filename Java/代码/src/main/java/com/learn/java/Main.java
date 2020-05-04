package com.learn.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */

public class Main {
    public static void main(String[] args) {
        List<UserIp> userIpList = new ArrayList<>();
        userIpList.add(new UserIp("a", "ip1"));
        userIpList.add(new UserIp("a", "ip2"));
        userIpList.add(new UserIp("b", "ip3"));
        userIpList.add(new UserIp("c", "ip4"));
        userIpList.add(new UserIp("d", "ip5"));
        Map<String, String> user2ip = userIpList
                .stream()
                .collect(Collectors.toMap(
                        UserIp::getUserId,
                        UserIp::getIpAddress,
                        (a, b) -> a + ";" + b));
        Map<String, List<UserIp>> user2ips = userIpList
                .stream()
                .collect(Collectors.groupingBy(UserIp::getUserId));
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