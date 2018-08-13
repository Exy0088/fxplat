package com.youedata.action;

import com.youedata.base.Message;
import com.youedata.base.driver.BaseDriver;
import com.youedata.handler.LoginHandler;
import org.apache.log4j.Logger;


/**
 * 登录业务类
 * @author hezhaowei
 * @create 2018-07-15 16:39
 **/
public class LoginAction extends BaseAction{

    Logger log  =  Logger.getLogger(this.getClass());
    private BaseDriver driver;
    private LoginHandler loginHandler;

    public LoginAction(BaseDriver driver) {
        super(driver);
        this.loginHandler = new LoginHandler(driver);
    }


    /**
     * 登录平台成功
     * @param username
     * @param password
     */
    public String loginSuccess(String username, String password){
        if(username != null && username != ""){
            loginHandler.SendKeysUserName(username);
        }
        if(password != null && password != ""){
            loginHandler.SendKeysPass(password);
        }
        loginHandler.clickLoginBtn();
        if(loginHandler.getLoginUser() != null){
            return Message.LOGINSUCCESS;
        }
        return null;
    }

    /**
     * 登录平台失败
     * @param username
     * @param password
     */
    public String loginFail(String username, String password){
        if(username != null && username != ""){
            loginHandler.SendKeysUserName(username);
        }
        if(password != null && password != ""){
            loginHandler.SendKeysPass(password);
        }
        loginHandler.clickLoginBtn();
        if(loginHandler.getFxWelcome() != null){
            return Message.LOGINFAIL;
        }
        return null;
    }



    /**
     * 退出平台
     * @return
     */
    public String logout(){
        this.NOLogin();
        loginHandler.clikLogoutBtn();
        if(loginHandler.getFxWelcome() != null){
            return Message.LOGOUTSUCCESS;
        }
        return null;
    }
}
