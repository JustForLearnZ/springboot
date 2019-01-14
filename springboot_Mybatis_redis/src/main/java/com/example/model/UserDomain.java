package com.example.model;

import java.io.Serializable;

public class UserDomain implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String userName;
    private String password;
    private String phone;
    @Override
	public String toString() {
		return "UserDomain [userId=" + userId + ", userName=" + userName + ", password=" + password + ", phone=" + phone
				+ "]";
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
	
