package com.demeter.portal.pojo;

public class UserInfo {
    private int userid;
    private String username;
    private int numRestaurant;
    private int numOrder;

    public UserInfo() {
    }

    public UserInfo(int userid, String username, int numRestaurant, int numOrder) {
        this.userid = userid;
        this.username = username;
        this.numRestaurant = numRestaurant;
        this.numOrder = numOrder;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumRestaurant() {
        return numRestaurant;
    }

    public void setNumRestaurant(int numRestaurant) {
        this.numRestaurant = numRestaurant;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", numRestaurant=" + numRestaurant +
                ", numOrder=" + numOrder +
                '}';
    }
}
