package com.example.meetingroom.entity;

public abstract class Member {
    private String userId;
    private String userName;
    private String passwd;


    public Member(String userId, String userName, String passwd) {
        this.userId = userId;
        this.userName = userName;
        this.passwd = passwd;
    }

    protected void resetPassword(String passwd) {
        System.out.println("Reset password");
    }
}
