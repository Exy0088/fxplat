package com.youedata.pageelement.orgmanager;

import com.youedata.base.driver.BaseDriver;
import com.youedata.utils.Locator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-08-14 10:10
 **/
public class GirdPersionPage {

    Logger log  =  Logger.getLogger(this.getClass());
    private Locator locator;
    private final String path = "yaml/orgmanager/gridperson.yaml";

    public GirdPersionPage(BaseDriver driver){
        this.locator = new Locator(driver,path);
    }

    /**
     * 定位组织管理系统菜单
     * @return
     */
    public WebElement orgmanagerMemu(){
        return locator.getLocator("orgmanager_memu",true);
    }

    /**
     * 定位网格人员档案管理菜单
     * @return
     */
    public WebElement gridPersonMemu(){
        return locator.getLocator("grid_person_memu",true);
    }

    /**
     * 定位新增人员按钮
     * @return
     */
    public WebElement addButton(){
        return locator.getLocator("add_button",true);
    }

    /**
     * 定位新增网格员title
     * @return
     */
    public WebElement getAddTitle(){
        return locator.getLocator("add_title",true);
    }

    /**
     *
     * @return
     */
    public WebElement gridPersonName(){
        return locator.getLocator("grid_person_name",true);
    }

    public WebElement gridPersonCard(){
        return locator.getLocator("grid_person_Card",true);
    }

    public WebElement addSubmit(){
        return locator.getLocator("add_submit",true);
    }

    //查询网格人员

    /**
     * 定位行政区域下拉框
     * @return
     */
    public WebElement street(){
        return locator.getLocator("street",true);
    }

    /**
     * 定位网格分类下拉框
     * @return
     */
    public WebElement gridPersonGrid(){
        return locator.getLocator("grid_person_Grid",true);
    }

    /**
     * 定位人员姓名输入框
     * @return
     */
    public WebElement gridManName(){
        return locator.getLocator("grid_man_name",true);
    }

    /**
     * 定位查询按钮
     * @return
     */
    public WebElement searchButton(){
        return locator.getLocator("search_button",true);
    }
}
