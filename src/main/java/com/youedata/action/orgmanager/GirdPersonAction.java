package com.youedata.action.orgmanager;

import com.youedata.action.BaseAction;
import com.youedata.base.Common;
import com.youedata.base.driver.BaseDriver;
import com.youedata.handler.orgmanager.GirdPersonHandler;
import com.youedata.pagedata.GirdPersonPageData;
import org.apache.log4j.Logger;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-08-14 10:24
 **/
public class GirdPersonAction extends BaseAction{

    Logger log  =  Logger.getLogger(this.getClass());
    private BaseDriver driver;
    private GirdPersonHandler girdPersonHandler;


    public GirdPersonAction(BaseDriver driver) {
        super(driver);
        this.driver = driver;
        this.girdPersonHandler = new GirdPersonHandler(driver);
    }

    /**
     * 检查页面标题
     */
    public void checkPageTitle(){
        girdPersonHandler.clickOrgMangerMemu();
        girdPersonHandler.clickGirdPersonMemu();
        driver.swithFrame(1);
        Common.sleep(1000);
    }

    /**
     * 检查新增人员弹出框标题
     * @return
     */
    public String  checkAddTitle(){
        checkPageTitle();
        girdPersonHandler.clickAddButton();
        return  girdPersonHandler.getAddTitleText();
    }


    public void addGirdPerson(GirdPersonPageData data){
        checkPageTitle();
        girdPersonHandler.clickAddButton();
        driver.swithFrame(0);
        girdPersonHandler.sendKeysGridPersonName(data.getGirdPersonName());
        girdPersonHandler.sendKeysGridPersonCard(data.getGirdPersinCard());
        girdPersonHandler.clickAddSubmit();
        driver.refresh();
        driver.swithFrame(1);
    }

    public void searchGirdPerson(GirdPersonPageData data){
        checkPageTitle();//切到网格人员管理页面
        girdPersonHandler.selectStreet(data.getStreet());
        girdPersonHandler.selectGirdPersonGird(data.getGirdPersonGird());
        girdPersonHandler.sendKeysGridManName(data.getGirdPersonName());
        girdPersonHandler.clickSearchButton();
    }
}
