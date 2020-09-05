package com.woc;

import com.woc.pojo.ResponseData;
import com.woc.pojo.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TestReqResponse {

    @Test
    public void getUsersDataAndLogIt1() {
        System.out.println("_______started invoking the rest api_____________");
        given().when().get("https://reqres.in/api/users?page=2").then().log().all();
        System.out.println("_______rest api invoke completed_____________");
    }

    @Test
    public void getUsersDataAndLogIt2() {
        System.out.println("_______started invoking the rest api_____________");

        given().queryParam("page", 2)
                .when().get("https://reqres.in/api/users").then().log().all();

        System.out.println("_______rest api invoke completed_____________");
    }


    @Test
    public void getUsersDataAndCheckStatusCode() {
        System.out.println("_______started invoking the rest api_____________");

        int statusCode = given().queryParam("page", 2)
                .when().get("https://reqres.in/api/users").statusCode();

        Assert.assertEquals(statusCode, 200);

        System.out.println("_______rest api invoke completed_____________statusCode="+statusCode);
    }

    @Test
    public void getUsersDataAndCountHowManyUsers() {
        System.out.println("_______started invoking the rest api_____________");

        ResponseData responseData = given().queryParam("page", 2)
                .when().get("https://reqres.in/api/users").thenReturn().as(ResponseData.class);

        Assert.assertNotNull(responseData);

        System.out.println("_______rest api invoke completed______________");
        System.out.println("responseData="+responseData);

        List<User> data = responseData.getData();
        System.out.println("***********************************");
        data.stream().forEach(System.out::println);
        System.out.println("***********************************");


        Assert.assertTrue(!data.isEmpty(), "Unable to fetch the users details");

        Assert.assertEquals(data.size(), 6);

    }

    @Test
    public void getUsersEmailsAndCheckIfMailIdIsMandatory() {
        System.out.println("_______started invoking the rest api_____________");

        ResponseData responseData = given().queryParam("page", 2)
                .when().get("https://reqres.in/api/users").thenReturn().as(ResponseData.class);

        Assert.assertNotNull(responseData);

        System.out.println("_______rest api invoke completed______________");
        System.out.println("responseData="+responseData);

        List<User> users = responseData.getData();

        List<String> usersMails = users.stream().map(User::getEmail).collect(Collectors.toList());

        Assert.assertTrue(!usersMails.isEmpty());
        System.out.println("usersMails="+usersMails);

        Assert.assertEquals(usersMails.size(), users.size(), "user's mail count is not matching with user count");

        Set<String> uniqueMailIds = users.stream().map(User::getEmail).collect(Collectors.toSet());

        Assert.assertEquals(uniqueMailIds.size(), users.size(), "user's distinct mail count is not matching with user count");
    }



}
