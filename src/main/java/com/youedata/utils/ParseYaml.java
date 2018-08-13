package com.youedata.utils;

import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-15 14:39
 **/
public class ParseYaml {


    /**
     * 读取yaml文件
     * @param path
     * @return
     */
    public static HashMap<String, HashMap<String, String>> parseYamlFile(String path){
        HashMap<String, HashMap<String, String>> map = null;
        if(path.endsWith(".yaml") || path.endsWith(".yml")){
            File f = new File(path);
            if(!f.exists()){
                System.out.println("文件不存在");
            }else if(f.exists() && !f.isDirectory()){
                try {
                    map = new HashMap<String, HashMap<String, String>>();
                    if (Yaml.loadType(new FileInputStream(f.getAbsoluteFile()), HashMap.class) != null){
                        map = Yaml.loadType(new FileInputStream(f.getAbsoluteFile()), HashMap.class);
                    }else{
                        System.out.println("文件内容不能为空");
                    };
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return map;
            }
        }else{
            System.out.println("文件格式不正确");

        }
        return null;
    }

    public static void main(String[] args) {
        String path = "src/test/resources/yaml/login.yaml";
        HashMap hashMap = parseYamlFile(path);
        if(hashMap != null){

            Iterator iterator = hashMap.entrySet().iterator();
            while(iterator.hasNext()){
                Object next = iterator.next();
                System.out.println(next);
            }
        }
    }
}
