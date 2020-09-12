package com.woc.bdd.cucumber;

import com.woc.pojo.NewUser;
import com.woc.util.PropertyUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Steps {
    private static final String BASE_URL = PropertyUtil.getProperty("test.rest.api.base.url");

    NewUser inputUser;
    Response response;

    @Given("^Set the base url$")
    public void setBaseUrl(){
        System.out.println("setBaseUrl triggered");
        RestAssured.baseURI=BASE_URL;
    }

    @When("^Enter the Name \"(.*)\" and Job \"(.*)\" hit the post request$")
    public void createUser(String name, String job){
        System.out.println("createUser triggered, name="+name+", job="+job);

        inputUser = new NewUser();
        inputUser.setName(name);
        inputUser.setJob(job);

        response = given()
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .body(inputUser)
                .post();

    }

    @Then("^verify the response$")
    public void verifyResponse(){
        System.out.println("verifyResponse triggered");
        NewUser createdUser = response.then()
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
