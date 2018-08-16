package com.youedata.testcase;

import com.youedata.action.LoginAction;
import com.youedata.base.GlobalConfig;
import com.youedata.base.UrlType;
import com.youedata.base.Common;
import com.youedata.base.driver.BaseDriver;
import com.youedata.utils.Assertion;
import com.youedata.utils.ExcelUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.youedata.pagedata.LoginPageData;
import com.youedata.pagedata.BasePageData;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
  * 登录case
  * @author hezhaowei
  * @create 2018-07-15 17:05
 **/
public class LoginCase extends BaseCase {

    Logger log  =  Logger.getLogger(this.getClass().getName());
    private LoginAction loginAction;
    private BaseDriver driver;
    private LoginPageData caseData;
    private Assertion assertion;
    private final String filePath = "testdata/fxplat.xls";
    private final String caseSheet = "登录测试数据";
    private ExcelUtils excelUtils;

    /**
     * 注入数据驱动
     * @param method 获得调用该方法的方法名
     * @return
     * @throws Exception
     */
    @DataProvider(name = "longinData")
    public Iterator<Object[]> loginData(Method method) throws Exception{
        excelUtils = new ExcelUtils(filePath,caseSheet);
       return Common.casesData(excelUtils,method);
    }

    //每个Test运行时之前都要调用该方法
    @BeforeMethod
    public void setUp() throws Exception {//@Optional 这个注解是给传入的参数一个默认值，String browser是获得 @Parameters({"browser"})的值
                                          //若参数有多个@Parameters({"browser","par"}),String browser,String par,必须一一对应
        //使用父类的获得driver的方法
        this.driver = super.getDriver();
        //初始化LoginAction业务类
        loginAction = new LoginAction(driver);
        this.assertion = new Assertion(driver);
        //浏览器最大化
        driver.browserMax();
        //打开浏览器输入访问地址
        driver.get(GlobalConfig.getUrl(UrlType.LOGIN));//读取global.properties文件中的"LOGINURL"
    }

    /**
     * 登录成功
     * @throws Exception
     */
    @Test(description = "登录成功用例", dataProvider = "longinData")//注入数据
    public void loginSuccess(BasePageData data)throws Exception {//需要一个LoginCaseData 这个实体类，获得Excel文件中的用户名、密码数据
        caseData = Common.getJson(data.getInputData(),LoginPageData.class);
        String message = loginAction.loginSuccess(caseData.getUsername(), caseData.getPassword());//调用loginaction业务类中login方法，
        //调用Assert类中的方法，实际值与预期值进行比较
        assertion.VerityString(message,data.getExpected());
    }

    /**
     * 登录失败
     * @throws Exception
     */
    @Test(description = "登录失败用例", dataProvider = "longinData")
    public void loginFail(BasePageData data)throws Exception{
        caseData = Common.getJson(data.getInputData(),LoginPageData.class);
        String message = loginAction.loginFail(caseData.getUsername(),caseData.getPassword());
        //调用Assert类中的方法，实际值与预期值进行比较
        assertion.VerityString(message,data.getExpected());
    }

    @Test(description = "退出登录用例", dataProvider = "longinData")
    public void logout(BasePageData data)throws Exception{
        String message = loginAction.logout();
        //调用Assert类中的方法，实际值与预期值进行比较
        assertion.VerityString(message,data.getExpected());
    }

}
