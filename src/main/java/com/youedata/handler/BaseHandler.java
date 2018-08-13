package com.youedata.handler;

import com.youedata.base.driver.BaseDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * 页面操作基类
 * @author hezhaowei
 * @create 2018-07-15 13:57
 **/
public class BaseHandler {

    Logger log  =  Logger.getLogger(this.getClass());
    private BaseDriver driver;

    public BaseHandler(BaseDriver driver){
        this.driver = driver;
    }

    /**
     * 清空输入框并赋值
     * @param element
     * @param value
     */
    public void clearAndSendKeys(WebElement element,String value){
        if(element != null){
            element.clear();
            element.sendKeys(value);
            log.info("Locator:"+element+"输入值："+value);
        }
    }

    /**
     * 点击元素
     * @param element
     */
    public void click(WebElement element){
        if(element != null){
           element.click();
           log.info("点击："+element);
        }
    }

    /**
     * 获取元素文本内容
     * @param element
     * @return
     */
    public String getText(WebElement element){
        if(element != null){
            log.info("获得元素文本内容："+ element.getText());
            return   element.getText();
        }
        return null;
    }

    /**
     * 移动到页面底部
     */
    public void moveScroll(){
        driver.moveScroll();
    }
}
