package com.youedata.utils;

import com.youedata.base.driver.BrowserType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
  * 监听进程，杀进程
  * @author hezhaowei
  * @create 2018-07-15 12:54
  **/
public class KillProcess {
    public static void kill(String processName){
        try {
            while (isProcessRunning(processName)){
                String taskkill = "taskkill /IM "+ processName+" /F";
                Runtime.getRuntime().exec(taskkill);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isProcessRunning(String processName) throws IOException {
        Process process=null;
        try {
            process = Runtime.getRuntime().exec("tasklist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        try {
            boolean flag = false;
            while ((line=reader.readLine())!=null){
                if(line.toLowerCase().contains(processName)){
                    flag=true;
                    break;
                }
            }
            return flag;
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void kill(BrowserType browserType) {
        switch (browserType) {
            case IE:
                break;
            case FIREFOX:
                KillProcess.kill("geckodriver.exe");
                KillProcess.kill("firefox.exe");
                break;
            case CHROME:
                //关闭浏览器进程
                KillProcess.kill("chrome.exe");
                KillProcess.kill("chromedriver.exe");
                break;
        }
    }
}
