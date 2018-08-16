package com.youedata.testcase;

import com.youedata.base.Common;
import com.youedata.base.driver.BaseDriver;
import com.youedata.base.driver.BrowserType;
import com.youedata.utils.KillProcess;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
  * Case基类
  * @author hezhaowei
  * @create 2018-07-15 12:57
  **/
@Listeners({com.youedata.utils.listeners.ExtentTestNGIReporterListener.class})
public class BaseCase {

    Logger log  =  Logger.getLogger(this.getClass());
    private BaseDriver driver;


    public BaseCase(){
        initLogRecord();
    }

    private static void initLogRecord(){
        Properties props = null;
        FileInputStream fis = null;
        try {
            props = new Properties();
            fis = new FileInputStream(Common.getFilePath("properties/log4j.properties"));
            props.load(fis);
            PropertyConfigurator.configure(props);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            fis = null;
        }
    }

    public BaseDriver getDriver() {
        return driver;
    }

    @Parameters({"testBrowser"})
    @BeforeMethod
    public void initDriver(@Optional("js") String browserType){

        try {
            if(browserType.equalsIgnoreCase("IE") || browserType.equalsIgnoreCase("InternetExplorer")){
                driver =  new BaseDriver(BrowserType.IE);
            }else if(browserType.equalsIgnoreCase("ff") || browserType.equalsIgnoreCase("FIREFOX")){
                driver =  new BaseDriver(BrowserType.FIREFOX);
            }else if(browserType.equalsIgnoreCase("js") || browserType.equalsIgnoreCase("PHANTOMJS")){
                driver =  new BaseDriver(BrowserType.PHANTOMJS);
            } else{
                driver =  new BaseDriver(BrowserType.CHROME);
            }
            log.info("------------------开始执行测试---------------");
        } catch (Exception e) {
            log.error("没有成功浏览器环境配置错误");
            e.printStackTrace();
        }
    }


    @AfterMethod
    public void tearDown() throws Exception {
        driver.closed();
        //KillProcess.kill(driver.getBrowserType());
    }



    /**
     * 运行结果，写入响应的状态
     * @param ir
     * @return
     */
    public String result(ITestResult ir){
        String result = "";
        if(ir.getStatus() == 1){
            result = "Pass";
        }else if(ir.getStatus() == 2){
            result = "Fail";
        }
       return result;
    }

}
