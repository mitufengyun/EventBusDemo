package com.example.eventbusdemo;

import androidx.annotation.NonNull;

import java.util.StringJoiner;

/**
 * time: 2020/3/13 18:10
 * author: xpf
 * describe:
 */
public class UserInfo {
    private String name;
    private String password;


    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
