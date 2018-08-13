package com.youedata.action;

import com.youedata.base.GlobalConfig;
import com.youedata.base.driver.BaseDriver;
import com.youedata.handler.LoginHandler;

/**
 *
 * 业务基类
 * @author hezhaowei
 * @create 2018-08-10 16:14
 **/
public class BaseAction {

    private LoginHandler loginHandler;

    public BaseAction(BaseDriver driver){
        this.loginHandler = new LoginHandler(driver);
    }

    /**
     * 免登陆
     */
    public void NOLogin(){
        loginHandler.SendKeysUserName(GlobalConfig.getKeyValue("DEFAULTUSERNAME"));
        loginHandler.SendKeysPass(GlobalConfig.getKeyValue("DEFAULTPASSWORD"));
        loginHandler.clickLoginBtn();
    }
}
