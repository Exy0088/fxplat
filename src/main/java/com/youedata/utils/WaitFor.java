package com.youedata.utils;

import com.youedata.base.GlobalConfig;
import com.youedata.base.driver.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-16 14:38
 **/
public class WaitFor {

    private BaseDriver driver;
    private WebDriverWait driverWait;
    private long waitTime;

    public WaitFor(BaseDriver driver) {
       this.driver = driver;
       this.waitTime = Long.parseLong(GlobalConfig.getKeyValue("WAITTIME"));

    }

    /**
     * 页面元素是否在页面上可用和可被单击
     * @param locator
     */
   public boolean elementToBeClickable(By locator){
       WebElement element = new WebDriverWait(driver.getDriver(), waitTime).until(ExpectedConditions.elementToBeClickable(locator));
       if(element.isEnabled()){
           return true;
       }
       return false;
   }

    /**
     * 页面元素处于被选中状态
     * @param element
     * @return
     */
    public boolean elementToBeSelected(WebElement element){
       boolean flag = false;
       if(element != null){
           flag = new WebDriverWait(driver.getDriver(), waitTime).until(ExpectedConditions.elementToBeSelected(element));
           if(flag){
               flag = true;
           }
       }
       return flag;
    }

    /**
     * 页面元素在页面中存在
     * @param locator
     * @return
     */
    public boolean presenceOfElementLocated(By locator){
        boolean flag = false;
        if (locator != null ){
            try{
                WebDriverWait driverWait = new WebDriverWait(driver.getDriver(), waitTime);
                final WebElement until = driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
                return  flag = true;
            }catch (TimeoutException e){
                throw new NoSuchElementException("等待"+waitTime+"秒，没有定位到"+locator.toString());
            }
        }else {
            throw new IllegalArgumentException("Cannot find elements when the selector is null");
        }
    }

    /**
     * 页面元素值
     * @param locator
     * @param value
     * @return
     */
    public boolean textToBePresentInElement(By locator,String value){
        boolean flag = false;
        if (locator != null ){
            flag = new WebDriverWait(driver.getDriver(), waitTime).until(ExpectedConditions.textToBePresentInElementValue(locator, value));
           if(flag){
               flag = true;
           }
        }
        return flag;
    }

    /**
     * 标题包含
     * @param title
     * @return
     */
   public boolean titleContains(String title){
       boolean flag = false;
       if (title != null ){
           flag = new WebDriverWait(driver.getDriver(), waitTime).until(ExpectedConditions.titleContains(title));
           if(flag){
               flag = true;
           }
       }
       return flag;
   }

}
