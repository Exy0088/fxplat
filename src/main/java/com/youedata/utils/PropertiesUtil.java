package com.youedata.utils;



import com.youedata.base.XACommon;
import java.io.*;
import java.util.Properties;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-15 15:16
 **/
public class PropertiesUtil {

    private  Properties props;
    private  String[] filePath;

//    public PropertiesUtil() {
//
//    }
//
//    public static class PropertiesInstance {
//        private static PropertiesUtil prop = new PropertiesUtil();
//    }
//
//
//    public static PropertiesUtil getProperInstance(String... filePath){
//        readProperties( filePath);
//        return PropertiesInstance.prop;
//    }


    public PropertiesUtil(String... filePath) {
        this.filePath = new String[filePath.length];
        for (int i=0;i<filePath.length;i++){
           this.filePath[i]= XACommon.getFilePath(filePath[i]);
        }
    }

    private  Properties readProperties(){

        props = new Properties();
        if(filePath == null ){
            System.out.println("文件路径不能为空");
        }else {
            for (int i = 0; i <filePath.length; i++){
                String path = filePath[i];
                if(path.endsWith(".properties")){
                    File f = new File(path);
                    InputStream is = null;
                    BufferedReader bf = null;
                    if(f.exists()){
                        try {
                            is = new BufferedInputStream(new FileInputStream(path));
                            bf = new BufferedReader(new  InputStreamReader(is,"UTF-8"));//解决读取properties文件中产生中文乱码的问题
                            props.load(bf);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if(is != null){
                                try {
                                    is.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(bf != null){
                                try {
                                    bf.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }else {
                        System.out.println("文件不存在");
                    }
                }else{
                    System.out.println("文件格式不正确");
                }
            }

            }

        return props;
    }

    /**
     * xieru
     * @param key
     * @param value
     */
    public  void writePops(String file,String key,String value){
        props = new Properties();
        FileOutputStream fos = null;
        if(key != null && key != "" && value != null && value != ""){

              try {
//                if(!props.isEmpty()){
//                    for(Enumeration e = props.propertyNames(); e.hasMoreElements();){
//                        String s = (String) e.nextElement(); // 遍历所有元素
//                        if (s.equals(key)) {
//                            /**如果键相同则覆盖*/
//                            props.setProperty(key,value);
//
//                        } else {/**否则就原样写入*/
//                            props.setProperty(s,props.getProperty(s));
//                        }
//                    }
//                }else{
                    props.setProperty(key,value);
//                }
                fos = new FileOutputStream(XACommon.getFilePath(file));
                props.store(fos,"SaveCookie");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * 根据传入的key,获取相应的值
     * @param key
     * @return
     */
    public    String getPro(String key){
        props = readProperties();
        String property = null;
        if(key != "" && key !=null){

            if(props.containsKey(key)){
                property = props.getProperty(key);
            }
            return property;
        }
        return null;
    }
//    private  String getFilePath(String FileName) {
//        String userdirPath = System.getProperty("user.dir");
//        if (userdirPath.endsWith("target")) {
//            userdirPath = userdirPath.replace("target", "");
//        }
//        return userdirPath + "/src/test/resources/" + FileName;
//    }
    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        PropertiesUtil parseProperties = getProperInstance("src\\test\\resources\\properties\\global.properties");
//       // PropertiesUtil pp = getProperInstance("src\\test\\resources\\properties\\db.properties");
//        //System.out.println(pp ==parseProperties);
//        String loginurl = parseProperties.getPro("WAITTIME");
//        long l1 = System.currentTimeMillis();
//        System.out.println(l1-l+"ms");
//        System.out.println(loginurl);
//        PropertiesUtil parseProperties = getProperInstance("src\\test\\resources\\properties\\global.properties","src\\test\\resources\\properties\\consult.properties");
//
//        String searchcontent = parseProperties.getPro("SEARCHCONTENT");
//        System.out.println(searchcontent);
        //getPro("WAITTIME");

        //writePops("src\\\\test\\\\resources\\\\properties\\\\global.properties","ss","dd");

    }
}
