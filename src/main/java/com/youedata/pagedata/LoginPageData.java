package com.youedata.pagedata;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-23 15:48
 **/
public class LoginPageData extends BasePageData {


    private String username;//输入用户名
    private String password;//输入密码


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}