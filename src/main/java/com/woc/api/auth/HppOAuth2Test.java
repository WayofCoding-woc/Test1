package com.woc.api.auth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

// we have used this website for oauth related apis in this class for learning purpose only.
//https://gorest.co.in/
public class HppOAuth2Test {

    @Test
    public void createUser(){
        RestAssured.baseURI = "https://gorest.co.in/public-api";

        String response = RestAssured.given()
                .header("Authorization", "Bearer dad04885ede1dde068c9eed6b4338056ad42f21bbed3942e2fdbd6cfb48f3ab3")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\"name\":\"Sonu Kumar SDET3\",\n" +
                        " \"gender\":\"Male\",\n" +
                        " \"email\":\"sonu.kumar.sdet3@gmail.com\",\n" +
                        " \"status\":\"Active\"\n" +
                        "}")
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        Assert.assertNotNull(response, "unable to create user");

        System.out.println("response="+response);
    }

    @Test
    public void updateUser(){
        RestAssured.baseURI = "https://gorest.co.in/public-api";

        String response = RestAssured.given()
                .header("Authorization", "Bearer dad04885ede1dde068c9eed6b4338056ad42f21bbed3942e2fdbd6cfb48f3ab3")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        " \"email\":\"sonu.kumar.sdet300@gmail.com\"\n" +
                        "}")
                .when()
                .patch("/users/2177")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        Assert.assertNotNull(response, "unable to update the email id of user");

        System.out.println("response="+response);
    }


}
