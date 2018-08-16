package com.youedata.utils;

import com.youedata.base.driver.BaseDriver;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by cdyoue on 2018/6/22.
 */
public class Assertion {

    Logger log  =  Logger.getLogger(this.getClass());
    private BaseDriver driver;
    private WaitFor waitFor;

    public Assertion(BaseDriver driver){
        this.driver = driver;
        this.waitFor = new WaitFor(driver);
    }

    /**
     * 验证实际值actual与预期值exceptStr是否相等
     * @param actual 实际值
     * @param exceptStr 预期值
     */
    public  void VerityString(String actual,String exceptStr)
    {
        String verityStr="Assert验证:{"+"实际值:"+actual+","+"预期值:"+exceptStr+"} 实际值与预期值是否一致";
        log.info(verityStr);
        try {
         Assert.assertEquals(actual, exceptStr);
        } catch (Error e) {
          e.printStackTrace();

        }
    }
    /**
     * 验证页面是否出现文本exceptStr
     * @param exceptStr 预期值
     */
    public  boolean  VerityNotTextPresent(String exceptStr) {
        String verityStr = "【Assert验证】:" + "页面是否出现" + "【" + "预期值：" + exceptStr + "】" + "字符串";
        log.info(verityStr);
        boolean flag = false;
        String str = null;
        if(exceptStr == null || exceptStr == ""){
            return false;
        }
        try {
            str = "//*[contains(.,'" + exceptStr + "')]";
            boolean b = waitFor.presenceOfElementLocated(By.xpath(str));
                if(b){
                    WebElement element = driver.findElement(By.xpath(str));
                    if(element != null){
                        log.info("页面出现:"+exceptStr);
                        flag = true;
                    }
                }
            } catch (NoSuchElementException e) {
                log.error("页面没有找到:"+By.xpath(str));
                log.error("页面没有出现:"+exceptStr);
            }
        return flag;
     }

    /**
     * 验证是页面否没有出现某文本---精确匹配
     * @param exceptStr 预期值
     * @param Message 验证中文描述
     */
    public void VerityNotTextPresentPrecision(String exceptStr,String Message) {
        String verityStr = "【Assert验证】:"+"页面是否没有出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
        Boolean flag = false;
        log.info(Message+":"+verityStr);
        try {
            exceptStr = "//*[text()=\""+exceptStr+"\"]";
            driver.findElement(By.xpath(exceptStr));
            flag = false;
        } catch (NoSuchElementException e) {
            flag = true;
        }
    }
}
