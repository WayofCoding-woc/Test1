package com.woc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static Properties properties;

    static{
        System.out.println("going to load the config.properties file");
        properties = new Properties();
        String propertiesFilePath = "src"+ File.separator+"main"+File.separator+"resources"+File.separator+"config.properties";
        System.out.println("propertiesFilePath="+propertiesFilePath);

        try(FileReader reader = new FileReader(propertiesFilePath)){
            properties.load(reader);
            System.out.println("config.properties file loaded successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        return value;
    }

}
