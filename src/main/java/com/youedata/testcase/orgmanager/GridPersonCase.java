package com.youedata.testcase.orgmanager;

import com.youedata.action.orgmanager.GirdPersonAction;
import com.youedata.base.GlobalConfig;
import com.youedata.base.Message;
import com.youedata.base.UrlType;
import com.youedata.base.Common;
import com.youedata.base.driver.BaseDriver;
import com.youedata.pagedata.BasePageData;
import com.youedata.pagedata.GirdPersonPageData;
import com.youedata.testcase.BaseCase;
import com.youedata.utils.Assertion;
import com.youedata.utils.ExcelUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-08-14 10:29
 **/
public class GridPersonCase extends BaseCase {

    Logger log  =  Logger.getLogger(this.getClass().getName());
    private GirdPersonAction girdPersonAction;
    private GirdPersonPageData pageData;
    private BaseDriver driver;
    private Assertion assertion;
    private final String filePath = "testdata/fxplat.xls";
    private final String caseSheet = "组织管理系统-网格人员档案管理";
    private ExcelUtils excelUtils;

    /**
     * 注入数据驱动
     * @param method 获得调用该方法的方法名
     * @return
     * @throws Exception
     */
    @DataProvider(name = "girdPersonData")
    public Iterator<Object[]> loginData(Method method) throws Exception{
        excelUtils = new ExcelUtils(filePath,caseSheet);
        return Common.casesData(excelUtils,method);
    }

    @BeforeMethod
    public void setUp(){
        this.driver = super.getDriver();
        this.girdPersonAction = new GirdPersonAction(driver);
        this.assertion = new Assertion(driver);
        driver.browserMax();
        driver.get(GlobalConfig.getUrl(UrlType.LOGIN));
        girdPersonAction.NOLogin();
    }

    @Test(description = "网格人员档案管理页面title检查用例", dataProvider = "girdPersonData")//注入数据
    public void checkPageTitle(BasePageData basePageData){
        girdPersonAction.checkPageTitle();
        Assert.assertTrue(assertion.VerityNotTextPresent(basePageData.getExpected()));
    }

    @Test(description = "新增网格人员标题检查用例", dataProvider = "girdPersonData")//注入数据
    public void checkAddTitle(BasePageData basePageData){
        String addTitle = girdPersonAction.checkAddTitle();
        assertion.VerityString(addTitle,basePageData.getExpected());
    }

    @Test(description = "新增网格人员用例", dataProvider = "girdPersonData")//注入数据
    public void addGirdPerson(BasePageData basePageData){
        pageData = Common.getJson(basePageData.getInputData(), GirdPersonPageData.class);
        girdPersonAction.addGirdPerson(pageData);
        String message = assertion.VerityNotTextPresent(pageData.getGirdPersinCard()) ? Message.ADDSUCCESS : Message.ADDFAIL;
        assertion.VerityString(message,basePageData.getExpected());
    }

    @Test(description = "检索网格人员用例", dataProvider = "girdPersonData")//注入数据
    public void searchGirdPerson(BasePageData basePageData){
        pageData = Common.getJson(basePageData.getInputData(), GirdPersonPageData.class);
        girdPersonAction.searchGirdPerson(pageData);
        String message = assertion.VerityNotTextPresent(pageData.getGirdPersonName()) ? Message.SEARCHSUCCESS : Message.SEARCHFAIL;
        assertion.VerityString(message,basePageData.getExpected());
    }


}

