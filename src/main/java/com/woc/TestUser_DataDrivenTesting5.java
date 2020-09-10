package com.woc;

import com.woc.pojo.ResponseData;
import com.woc.pojo.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestUser_DataDrivenTesting5 {
    private static final String BASE_URL = "https://reqres.in";

    @DataProvider(name = "user-data")
    private Object[][] getUsers(){
        Object[][] data = new Object[2][1];

        data[0][0] = 1;
        data[1][0] = 2;

        return data;
    }

    @Test(dataProvider = "user-data")
    public void getUsers(Integer id){
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
        System.out.println("***********************************");
        data.stream().forEach(System.out::println);
        System.out.println("***********************************");

    }

}
