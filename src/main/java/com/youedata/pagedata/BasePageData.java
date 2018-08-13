package com.youedata.pagedata;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-08-03 9:19
 **/
public class BasePageData {

    private String ID;//用例ID
    private String TestSuite;//测试集
    private String TestCaseType;//测试用例类型
    private String TestCaseName;//测试用例名称
    private String InputData;//输入数据
    private String Expected;//预期结果
    private String IsRun;//是否执行
    private String TestExecuteTime;//执行时间
    private String TestResult;//执行结果

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTestSuite() {
        return TestSuite;
    }

    public void setTestSuite(String testSuite) {
        TestSuite = testSuite;
    }

    public String getTestCaseType() {
        return TestCaseType;
    }

    public void setTestCaseType(String testCaseType) {
        TestCaseType = testCaseType;
    }

    public String getTestCaseName() {
        return TestCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        TestCaseName = testCaseName;
    }

    public String getInputData() {
        return InputData;
    }

    public void setInputData(String inputData) {
        InputData = inputData;
    }

    public String getExpected() {
        return Expected;
    }

    public void setExpected(String expected) {
        Expected = expected;
    }

    public String getIsRun() {
        return IsRun;
    }

    public void setIsRun(String isRun) {
        IsRun = isRun;
    }

    public String getTestExecuteTime() {
        return TestExecuteTime;
    }

    public void setTestExecuteTime(String testExecuteTime) {
        TestExecuteTime = testExecuteTime;
    }

    public String getTestResult() {
        return TestResult;
    }

    public void setTestResult(String testResult) {
        TestResult = testResult;
    }

    @Override
    public String toString() {
        return "CaseData{" +
                "ID='" + ID + '\'' +
                ", TestSuite='" + TestSuite + '\'' +
                ", TestCaseType='" + TestCaseType + '\'' +
                ", TestCaseName='" + TestCaseName + '\'' +
                ", InputData='" + InputData + '\'' +
                ", Expected='" + Expected + '\'' +
                ", IsRun='" + IsRun + '\'' +
                '}';
    }
}
