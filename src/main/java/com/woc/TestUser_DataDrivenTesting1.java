package com.woc;

import com.woc.pojo.NewUser;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestUser_DataDrivenTesting1 {
    private static final String BASE_URL = "https://reqres.in";

    @DataProvider(name = "user-data")
    private Object[] getUsers(){
        Object[] users = new Object[2];

        NewUser inputUser1 = new NewUser();
        inputUser1.setName("piyus");
        inputUser1.setJob("student");
        users[0] = inputUser1;

        NewUser inputUser2 = new NewUser();
        inputUser2.setName("sonu");
        inputUser2.setJob("employee");
        users[1] = inputUser2;

        return users;
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
