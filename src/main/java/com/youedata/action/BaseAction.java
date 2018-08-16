package com.youedata.action;

import com.youedata.base.GlobalConfig;
import com.youedata.base.Message;
import com.youedata.base.driver.BaseDriver;
import com.youedata.handler.LoginHandler;
import com.youedata.utils.Assertion;
import org.apache.log4j.Logger;

/**
 *
 * 业务基类
 * @author hezhaowei
 * @create 2018-08-10 16:14
 **/
public class BaseAction {

    Logger log = Logger.getLogger(this.getClass());
    private LoginHandler loginHandler;
    private Assertion assertion;

    public BaseAction(BaseDriver driver){
        this.loginHandler = new LoginHandler(driver);
        this.assertion = new Assertion(driver);
    }

    /**
     * 免登陆
     */
    public void NOLogin(){
        loginHandler.SendKeysUserName(GlobalConfig.getKeyValue("DEFAULTUSERNAME"));
        loginHandler.SendKeysPass(GlobalConfig.getKeyValue("DEFAULTPASSWORD"));
        loginHandler.clickLoginBtn();
        if(assertion.VerityNotTextPresent("首页"))
            log.info(Message.LOGINSUCCESS);
        else
            log.info(Message.LOGINFAIL);
    }
}
