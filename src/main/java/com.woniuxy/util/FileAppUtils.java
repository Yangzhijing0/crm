package com.woniuxy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileAppUtils {
    public static String UPLOAD_PATH="C:/upload/"; //默认图片路径
    static{
        InputStream in=FileAppUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties=new Properties();
        try {
            properties.load(in);
            String property=properties.getProperty("filepath");
            if(property!=null){
                UPLOAD_PATH=property;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
