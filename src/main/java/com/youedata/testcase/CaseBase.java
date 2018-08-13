package com.youedata.testcase;

import com.youedata.base.XACommon;
import com.youedata.base.driver.BaseDriver;
import com.youedata.base.driver.BrowserType;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
  * Case基类
  * @author hezhaowei
  * @create 2018-07-15 12:57
  **/
@Listeners({com.youedata.utils.listeners.ExtentTestNGIReporterListener.class})
public class CaseBase {

    Logger log  =  Logger.getLogger(this.getClass());
    private BaseDriver driver;


    public CaseBase(){
        initLogRecord();
    }

    private static void initLogRecord(){
        Properties props = null;
        FileInputStream fis = null;
        try {
            props = new Properties();
            fis = new FileInputStream(XACommon.getFilePath("properties/log4j.properties"));
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
    public void initDriver(@Optional("chrome") String browserType){

        try {
            if(browserType.equalsIgnoreCase("IE") || browserType.equalsIgnoreCase("InternetExplorer")){
                driver =  new BaseDriver(BrowserType.IE);
            }else if(browserType.equalsIgnoreCase("ff") || browserType.equalsIgnoreCase("FIREFOX")){
                driver =  new BaseDriver(BrowserType.FIREFOX);
            }else{
                driver =  new BaseDriver(BrowserType.CHROME);
            }
            log.info("------------------开始执行测试---------------");
        } catch (Exception e) {
            log.error("没有成功浏览器环境配置错误");
            e.printStackTrace();
        }
    }

    public <T> T getJson(String data,Class<T> bean){
        T t = null;
        if(StrUtil.isNotEmpty(data)){
             t = JSONUtil.toBean(data, bean);
        }
        return t;
    }



    //    @Parameters({"browser"})
//    @BeforeMethod
//    public void init(String browserType){
//        if(browserType == "" || browserType == null){
//            log.error("浏览器传参有误");
//        }else {
//            Browser browser = null;
//            kill(browserType);
//            if(browserType.equals("IE") || browserType.equals("ie") || browserType.equals("InternetExplorer")){
//                browser = new Browser(Browser.BrowserType.IE);
//                log.info("初始化IE浏览器");
//            }else if(browserType.equals("ff") || browserType.equals("FF") || browserType.equals("FIREFOX")){
//                browser = new Browser(Browser.BrowserType.FIREFOX);
//                log.info("初始化火狐浏览器");
//            }else{
//                browser = new Browser(Browser.BrowserType.CHROME);
//                log.info("初始化谷歌浏览器");
//            }
//            try {
//                driver = browser.getDriver();
//                driver.manage().window().maximize();
//                log.info("------------------开始执行测试---------------");
//            } catch (Exception e) {
//                log.error("没有成功浏览器环境配置错误");
//                e.printStackTrace();
//            }
//        }
//    }

//    @Test
//    public void test(){
//        driver.get("http://www.baidu.com");
//    }

//    @Parameters({"browser"})
//    @AfterMethod
//    public void tearDown(String browserType){
//        driver.quit();
//        kill(browserType);
//    }


}
