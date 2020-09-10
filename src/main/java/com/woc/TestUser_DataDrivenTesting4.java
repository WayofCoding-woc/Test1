package com.woc;

import com.woc.pojo.NewUser;
import com.woc.util.CsvReaderUtil;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestUser_DataDrivenTesting4 {
    private static final String BASE_URL = "https://reqres.in";

    @DataProvider(name = "user-data")
    private Object[] getUsers(){

        String testDataFilePath = "test-data"+ File.separator+"student.csv";
        List<String[]> rows = CsvReaderUtil.readCsv(testDataFilePath, true);

        List<NewUser> users = new LinkedList<>();

        for(String[] row : rows){
            String name = row[0];
            String job = row[1];

            NewUser inputUser = new NewUser();
            inputUser.setName(name);
            inputUser.setJob(job);
            users.add(inputUser);
        }

        Object[] usersArray = users.toArray();
        return usersArray;
    }

    @Test(dataProvider = "user-data")
    public void createUser(NewUser inputUser){
        NewUser createdUser = given()
                .contentType(ContentType.JSON)
                .body(inputUser)
                .post(BASE_URL + "/api/users")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .thenReturn()
                .as(NewUser.class);

        System.out.println("createdUser="+createdUser);

        Assert.assertEquals(createdUser.getName(), inputUser.getName());
        Assert.assertEquals(createdUser.getJob(), inputUser.getJob());
        Assert.assertNotNull(createdUser.getCreatedAt(), "Creation time should not be null");
        Assert.assertNotNull(createdUser.getId(), "created user's id should not be null");
    }

}
