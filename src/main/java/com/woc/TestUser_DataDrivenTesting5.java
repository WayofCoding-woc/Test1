package com.woc;

import com.woc.pojo.ResponseData;
import com.woc.pojo.User;
import com.woc.util.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

//logger level order
//ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF.
public class TestUser_DataDrivenTesting5 {
    private static final Logger logger = LogManager.getLogger(TestUser_DataDrivenTesting5.class);

    private static final String BASE_URL = PropertyUtil.getProperty("test.rest.api.base.url");

    @DataProvider(name = "user-data")
    private Object[][] getUsers(){
        Object[][] data = new Object[2][1];

        data[0][0] = 1;
        data[1][0] = 2;

        return data;
    }

    @Test(dataProvider = "user-data")
    public void getUsers(Integer id){
        logger.info("getUsers request has come for id="+id);

        ResponseData responseData = given()
                .queryParam("page", id)
                .get(BASE_URL + "/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .thenReturn()
                .as(ResponseData.class);

        List<User> data = responseData.getData();
        logger.debug("***********************************");
        data.stream().forEach(logger::info);
        logger.error("###################");

    }

}
