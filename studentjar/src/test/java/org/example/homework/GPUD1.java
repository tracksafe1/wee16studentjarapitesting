package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD1 {
    Response response;
    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;
    @Test
    public void studentApp() {
        given()

                .get("http://localhost:8080/student/list")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void createStudentInfo() {

        String data = "{\n" +
                "        \"firstName\": \"shikha\",\n" +
                "        \"lastName\": \"kapur\",\n" +
                "        \"email\": \"shikh42a@gmail.com\",\n" +
                "        \"programme\": \"Financial Analysis\",\n" +
                "        \"courses\": [\n" +
                "            \"Accounting\",\n" +
                "            \"Statistics\"\n" +
                "        ]}";
        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(data)
                .post("http://localhost:8080/student");
        response.then().log().all().statusCode(201)
                .body("msg", equalTo("Student added"));


    }
    @Test

    public void updateStudentInfo() {

        String jsonData = "{\n" +
                "    \"id\": 116,\n" +
                "    \"firstName\": \"shikha23\",\n" +
                "    \"lastName\": \"kapur\",\n" +
                "    \"email\": \"shikh42a@gmail.com\",\n" +
                "    \"programme\": \"Financial Analysis\",\n" +
                "    \"courses\": [\n" +
                "        \"Accounting\",\n" +
                "        \"Statistics\"\n" +
                "    ]\n" +
                "}";

        given()
                .baseUri("http://localhost:8080/student/116")
                .contentType(ContentType.JSON)
                .body(jsonData)

                .then().statusCode(200)
    .body("msg", equalTo("Student added"));

    }

    @Test // delete above id
    public void verifyNewStudentInfoDeletedByID() {
        given()
                .pathParam("id", 116)
                .when()
                .delete("http://localhost:8080/student/{id}")
                .then()
                .statusCode(204);
    }








}


