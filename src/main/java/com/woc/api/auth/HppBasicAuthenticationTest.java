package com.woc.api.auth;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HppBasicAuthenticationTest {

    @Test
    public void fetchEmployee1(){
        RestAssured.baseURI = "http://localhost:8979/api";

        String response = RestAssured.given()
                .auth()
                .basic("sonu", "pwd")
                .when()
                .queryParam("email", "sonu@gmail.com")
                .get("/employee")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        Assert.assertNotNull(response, "unable to get employee details");

        System.out.println("response="+response);
    }

    @Test
    public void fetchEmployee2(){
        RestAssured.baseURI = "http://localhost:8979/api";

        String response = RestAssured.given()
                .auth()
                .basic("piyus", "pass")
                .when()
                .queryParam("email", "piyus.kumar@gmail.com")
                .get("/employee")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        Assert.assertNotNull(response, "unable to get employee details");

        System.out.println("response="+response);
    }

}
