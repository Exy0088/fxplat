package com.youedata.base;

import cn.hutool.core.util.StrUtil;
import com.youedata.utils.PropertiesUtil;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-30 15:57
 **/
public class GlobalConfig {

    private static PropertiesUtil propertiesUtil = new PropertiesUtil("properties\\global.properties");

    public static String getKeyValue(String key){
       return propertiesUtil.getPro(key);
    }

    public static void setKeyValue(String file,String key, String value){
        if(StrUtil.isNotEmpty(key) && StrUtil.isNotEmpty(value)){
            propertiesUtil.writePops(file,key,value);
        }
    }

    public static String getUrl(UrlType urlType){
        String baseUrl =  getKeyValue("PROTOCOL")+"://"+getKeyValue("DOMAIN")+":"+getKeyValue("PORT");
        if(urlType == UrlType.LOGIN){
            return baseUrl+getKeyValue("LOGINURI");
        }

        return null;
    }

    public static void main(String[] args) {
        //setKeyValue("session","dsfsadfsadfsadfsadfsadfsda");
    }



}
