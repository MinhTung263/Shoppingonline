package com.minhtung.muasamonline.Model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    private String user_id;
    private String userName;
    private String email;

    public TaiKhoan() {
    }

    public TaiKhoan(String user_id, String userName, String email) {
        this.user_id = user_id;
        this.userName = userName;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
