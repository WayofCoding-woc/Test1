package com.woc.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PropertyUtilTest {

    @Test
    public void getProperty(){
        String testRestApiBaseUrl = PropertyUtil.getProperty("test.rest.api.base.url");
        Assert.assertEquals(testRestApiBaseUrl, "https://reqres.in");
    }
}
