package com.youedata.handler;

import com.youedata.base.driver.BaseDriver;
import org.apache.log4j.Logger;
import com.youedata.pageelement.LoginPage;

/**
  * 登录页面元素操作类
  * @author hezhaowei
  * @create 2018-07-15 16:30
 **/
public class LoginHandler extends BaseHandler {

    Logger log  =  Logger.getLogger(this.getClass());
    private LoginPage lg;

    public LoginHandler(BaseDriver driver) {
        super(driver);
        lg = new LoginPage(driver);
    }


    /**
     * 输入用户名
     * @param username
     */
    public void SendKeysUserName(String username){
        this.clearAndSendKeys(lg.getUserName(),username);
    }

    /**
     * 输入密码
     * @param password
     */
    public void SendKeysPass(String password){
        this.clearAndSendKeys(lg.getPassword(),password);
    }

    /**
     * 点击登录
     */
    public void clickLoginBtn(){
        this.click(lg.getLoginBtn());
    }

    public String getLoginUser(){
        return this.getText(lg.getLoginUser());
    }

    public String getFxWelcome(){
        return this.getText(lg.getFxWelcome());
    }
    public void clikLogoutBtn(){
        if(lg.getLogout().isDisplayed()){
            this.click(lg.getLogout());
        }
    }


}
