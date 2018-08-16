package com.youedata.handler.orgmanager;

import com.youedata.base.driver.BaseDriver;
import com.youedata.handler.BaseHandler;
import com.youedata.pageelement.LoginPage;
import com.youedata.pageelement.orgmanager.GirdPersionPage;
import org.apache.log4j.Logger;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-08-14 10:19
 **/
public class GirdPersonHandler extends BaseHandler{

    Logger log  =  Logger.getLogger(this.getClass());
    private GirdPersionPage girdPersionPage;

    public GirdPersonHandler(BaseDriver driver) {
        super(driver);
        girdPersionPage = new GirdPersionPage(driver);
    }

    /**
     * 点击组织管理菜单按钮
     */
    public void clickOrgMangerMemu(){
        this.click(girdPersionPage.orgmanagerMemu());
    }

    /**
     * 点击网格人员档案管理按钮
     */
    public void clickGirdPersonMemu(){
        this.click(girdPersionPage.gridPersonMemu());
    }

    /**
     * 点击新增网格人员按钮
     */
    public void clickAddButton(){
        this.click(girdPersionPage.addButton());
    }

    /**
     * 获得新增弹出框的标题
     * @return
     */
    public String getAddTitleText(){
        return  this.getText(girdPersionPage.getAddTitle());
    }

    public void sendKeysGridPersonName(String gridPersonName){
        this.clearAndSendKeys(girdPersionPage.gridPersonName(),gridPersonName);
    }
    public void sendKeysGridPersonCard(String gridPersonCard){
        this.clearAndSendKeys(girdPersionPage.gridPersonCard(),gridPersonCard);
    }
    public void clickAddSubmit(){
        this.click(girdPersionPage.addSubmit());
    }

    /**
     * 行政区域下拉选定某个值
     * @param value
     */
    public void selectStreet(String value){
        this.selectValue(girdPersionPage.street(),value);
    }
    /**
     * 网格分类下拉选定某个值
     * @param value
     */
    public void selectGirdPersonGird(String value){
        this.selectValue(girdPersionPage.gridPersonGrid(),value);
    }

    /**
     * 输入网格人员
     * @param gridManName
     */
    public void sendKeysGridManName(String gridManName){
        this.clearAndSendKeys(girdPersionPage.gridManName(),gridManName);
    }

    /**
     * 点击查询按钮
     */
    public void clickSearchButton(){
        this.click(girdPersionPage.searchButton());
    }
}


