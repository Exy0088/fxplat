package com.youedata.pageelement;

import com.youedata.base.driver.BaseDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import com.youedata.utils.Locator;

/**
  * 登录页面元素类
  * @author hezhaowei
  * @create 2018-07-15 16:14
  **/
public class LoginPage {

    Logger log  =  Logger.getLogger(this.getClass());
    private BaseDriver driver;
    private Locator locator;
    private final String path = "yaml/login.yaml";

    public LoginPage(BaseDriver driver) {
        this.driver = driver;
        this.locator = new Locator(driver,path);
    }

    /**
     * 定位username输入框
     * @return
     */
    public WebElement getUserName(){
        return locator.getLocator("username",true);
    }

    /**
     * 定位password输入框
     * @return
     */
    public   WebElement getPassword(){
        return locator.getLocator("password",true);
    }

    /**
     * 点击登录按钮
     * @return
     */
    public WebElement getLoginBtn(){
        return  locator.getLocator("login_button",true);
    }

    public WebElement getLogout(){
        return locator.getLocator("logout",true);
    }

    public WebElement getFxWelcome(){
        return locator.getLocator("fx_welcome",true);
    }
    public WebElement getLoginUser(){
        return locator.getLocator("login_user",true);
    }

    public static void main(String[] arg){

        System.out.println();
    }
}
