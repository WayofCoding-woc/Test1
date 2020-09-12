package com.woc.util;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestSystemProperties {
    public static void main(String[] args) {
        String userName = System.getProperty("user.name");
        System.out.println("userName="+userName);

        System.out.println("______________________");

        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for(Map.Entry<Object, Object> entry : entries){
            System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
        }

        System.out.println("___________________________________");

        String testRestApiBaseUrl = PropertyUtil.getProperty("test.rest.api.base.url");
        System.out.println("testRestApiBaseUrl="+testRestApiBaseUrl);

    }
}
