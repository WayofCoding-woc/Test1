package com.woc;

import com.woc.pojo.NewUser;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestUser {
    private static final String BASE_URL = "https://reqres.in";

    //please go through this link for understanding the http status code
    //https://www.tutorialspoint.com/http/http_status_codes.htm

    @Test
    public void createUser(){
        NewUser inputUser = null;//new NewUser();
        inputUser.setName("piyus");
        inputUser.setJob("student");

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

    @Test
    public void updateUser(){
        NewUser inputUser = new NewUser();
        inputUser.setName("piyus kumar srivastava");
        //inputUser.setJob("student");

        NewUser updatedUser = given()
                .contentType(ContentType.JSON)
                .body(inputUser)
                .put(BASE_URL + "/api/users/"+18)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .thenReturn()
                .as(NewUser.class);

        System.out.println("updatedUser="+updatedUser);
    }


}
