package com.youedata.base.driver;

import com.youedata.base.GlobalConfig;
import cn.hutool.core.util.StrUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.youedata.utils.KillProcess;
;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-17 13:18
 **/
public class BaseDriver {

    Logger log  =  Logger.getLogger(this.getClass());
    private  WebDriver driver;
    private  BrowserType browserType;

    public BaseDriver(BrowserType browserType){
       this.driver = getDriver( browserType);
       this.browserType = browserType;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public  WebDriver getDriver() {
        return driver;
    }

    public WebDriver getDriver(BrowserType browserType){
        Browser browser = null;
        if( browserType == null) {
            log.error("浏览器传参有误");
        }else {
            KillProcess.kill(browserType);
            log.info("读取浏览器并将其初始化");
            switch (browserType){
                case IE:
                    browser = new Browser(BrowserType.IE);
                    log.info("初始化IE浏览器");
                    break;
                case FIREFOX:
                    browser = new Browser(BrowserType.FIREFOX);
                    log.info("初始化火狐浏览器");
                    break;
                case CHROME:
                    browser = new Browser(BrowserType.CHROME);
                    log.info("初始化谷歌浏览器");
                    break;
                default:
                    browser = new Browser(BrowserType.CHROME);
                    log.info("初始化谷歌浏览器");
                    break;
            }
        }
        return browser.getDriver();
    }




    /**
     * 打开浏览器
     * @param url
     */
    public void get(String url){
        driver.get(url);
        log.info("打开浏览器，访问"+url+"网址!");
    }

    /**
     * 查找元素
     * @param by
     */
    public WebElement findElement(By by){
        return  driver.findElement(by);
    }

    /**
     * 关闭浏览器
     */
    public void closed(){
        driver.close();
        log.info("关闭浏览器");
    }

    /**
     * 获取cookie
     */
    public Set<Cookie> getCookies(){
        return driver.manage().getCookies();
    }

    /**
     * 获取cooke,并写入到properties中
     */
    public Cookie getCookieName(String name){
        return driver.manage().getCookieNamed(name);
    }

    /**
     * 设置Cookie
     * @param map
     */
    public void setCookie(Map map){
        Cookie cookie = null;
       if(map.size() > 0){
           Iterator it = map.entrySet().iterator();
           while (it.hasNext()){
               Map.Entry entry = (Map.Entry)it.next();
               cookie = new Cookie((String)entry.getKey(),(String)entry.getValue());
           }
       }
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(cookie);
        refresh();
    }

    /**
     * 设置Cookie
     * @param key
     */
    public void setCookie(String key){
        Cookie cookie = null;
        if(StrUtil.isNotEmpty(key)){
            cookie = new Cookie(key,GlobalConfig.getKeyValue(key));
        }
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(cookie);
        refresh();
    }


    public void refresh(){
        driver.navigate().refresh();
    }

    public void browserMax(){
        driver.manage().window().maximize();
    }

    public void swithFrame(int index){
        driver.switchTo().frame(index);

    }

    public void swithFrame(String name){
        driver.switchTo().frame(name);
    }
    public void defaultFrame(){
        driver.switchTo().defaultContent();
    }
    public void parentFrame(){
        driver.switchTo().parentFrame();
    }

    public void swichWindow(){
        Set<String> windowHandles = driver.getWindowHandles();
        for(String s : windowHandles){
        }
        String windowHandle = driver.getWindowHandle();
    }

    /**
     * 移动到页面底部
     */
    public void moveScroll(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    public static void main(String[] args) {

    }

    public void isDisplay(WebElement element){
       element.isDisplayed();
    }
}
