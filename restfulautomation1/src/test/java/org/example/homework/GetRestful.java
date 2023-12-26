package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRestful {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    @Test
    public void getBooking(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com/booking/2142";
        requestSpecification=RestAssured.given();
        response=requestSpecification.get();
        System.out.println(response.prettyPrint());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
}}
