package com.youedata.base;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.youedata.pagedata.BasePageData;
import com.youedata.utils.ExcelUtils;
import java.lang.reflect.Method;

import java.net.HttpCookie;
import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-30 16:25
 **/
public class Common {

    public static Map<String, Object> writeSession(String url, String username, String password) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        HttpResponse response = HttpRequest.post(url).form(map).timeout(2000).execute();
        List<HttpCookie> cookie = response.getCookie();
        map.clear();
        for (HttpCookie httpCookie : cookie) {
           map.put(httpCookie.getName(),httpCookie.getValue());
        }
        return map;
    }

//    @Test
//    public void test(){
//        getSession(GlobalConfig.getLoginUrl(),GlobalConfig.DEFAULTUSERNAME,GlobalConfig.DEFAULTPASSWORD);
//    }
    /**
     * 注入数据驱动
     * @param method 获得调用该方法的方法名
     * @param beanType
     * @return
     * @throws Exception
     */
    public static <T> List<List<T>>  casesData(ExcelUtils excelUtils, Method method, Class<T> beanType) throws Exception{
        //创建一个Object数组集合
        List<List<T>> caseDatas = new ArrayList<List<T>>();

        try {
            //调用ExcelUtils中的获得行的方法，需要传入用例名称、列号
            List<Integer> rowNum = excelUtils.getRowNum(method.getName(), ExcelUtils.TestcaseField.TestCaseType);
            //根据行去读取数据，需要传入行号、工作表字段对应的实体类，将获得数据放到泛型类型为映射的实体类中
            caseDatas = excelUtils.readExcel(rowNum,beanType);
//            //循环遍历集合
//            for(int i=0;i<caseDatas.size();i++){
//                for(int j =0;j<caseDatas.get(i).size();j++){
//                    lcd = new LoginCaseData();
//                    lcd = caseDatas.get(i).get(j);
//                    //将遍历出来的映射实体类，添加到Object数组集合中
//                    result.add(new Object[]{lcd});
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caseDatas;
    }

    public static String getNum(String exce){
        String size = "";
        if(exce != null && !"".equals(exce)){
            exce.trim();
            for(int i = 0;i <exce.length();i++ ){
                if(exce.charAt(i) >= 48 && exce.charAt(i) <= 57){
                    size += exce.charAt(i);
                }
            }
        }
        return size;
    }

    public static Iterator<Object[]> casesData(ExcelUtils excelUtils,Method method) throws Exception{
        //创建一个Object数组集合
        List<Object[]> result = new ArrayList<Object[]>();
        List<List<BasePageData>> caseDatas = Common.casesData(excelUtils, method, BasePageData.class);
        //循环遍历集合
        for(int i=0;i<caseDatas.size();i++){
            for(int j =0;j<caseDatas.get(i).size();j++){
                BasePageData data = new BasePageData();
                data = caseDatas.get(i).get(j);
                if(data != null){
                    if(data.getIsRun() == "是" || data.getIsRun().equals("是")){
                        //将遍历出来的映射实体类，添加到Object数组集合中
                        result.add(new Object[]{data});
                    }
                }
            }
        }
        return result.iterator();
    }

    public static String getFilePath(String FileName) {
        String userdirPath = System.getProperty("user.dir");
        if(userdirPath.endsWith("target")){
            userdirPath = userdirPath.replace("target","");
        }
        return userdirPath + "/src/main/resources/" + FileName;
    }

    public static String strSub(String content,int fromIndex, int toIndex,boolean flag){
        String subStr = content;
        if(content.length()>16){
            if(flag == false){
                return subStr = content.substring(fromIndex,toIndex);
            }else {
                return subStr = content.substring(fromIndex,toIndex)+"......";
            }
        }
        return subStr;
    }

    /**
     * 线程等待
     * @param waitTime
     */
    public static void sleep(long waitTime){
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 使用screen模拟点击windows上传控件
//     * @param url
//     * @param clickIcon
//     */
//    public static void uploadImg(String url,String clickIcon){
//        Screen screen = new Screen();
//        screen.type(url);
//        try {
//            screen.click(clickIcon);
//        } catch (FindFailed findFailed) {
//            findFailed.printStackTrace();
//        }
//    }

    /**
     * 上传文件，需要点击弹出上传照片的窗口才行
     *
     * @browser
     *            使用的浏览器名称
     * @filePath
     *            需要上传的文件及文件名
     */
    public static void handleUpload(String browser, String filePath) {
        String executeFile= getFilePath("script\\uploadImg.exe"); //定义了upload.exe文件的路径
        String cmd= "\""+ executeFile+ "\""+ " "+ "\""+ browser+ "\""+ " "+ "\""+ filePath+ "\"";
        try{
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static  <T> T getJson(String data,Class<T> bean){
        T t = null;
        if(StrUtil.isNotEmpty(data)){
            t = JSONUtil.toBean(data, bean);
        }
        return t;
    }

    public static void main(String[] args) {
        String filePath = getFilePath("yaml\\login.yaml");
        System.out.println(filePath);
    }
}
