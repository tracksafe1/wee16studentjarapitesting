package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD5 {
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

        String data = "{  \"firstName\": \"Ripti\",\n" +
                "        \"lastName\": \"patel\",\n" +
                "        \"email\": \"rip123@gmail.com\",\n" +
                "        \"programme\": \"Software Tester\",\n" +
                "        \"courses\": [\n" +
                "            \"Selenium\",\n" +
                "            \"Junit\"\n" +
                "        ]\n" +
                "    }";
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


        String data = "{  \"firstName\": \"RockstarRipti\",\n" +
                "        \"lastName\": \"patel\",\n" +
                "        \"email\": \"rip123@gmail.com\",\n" +
                "        \"programme\": \"Software Tester\",\n" +
                "        \"courses\": [\n" +
                "            \"Selenium\",\n" +
                "            \"Junit\"\n" +
                "        ]\n" +
                "    }";

        given()
                .baseUri("http://localhost:8080/student/132")
                .contentType(ContentType.JSON)
                .body(data)
                .then().statusCode(200)
                .body("msg", equalTo("Student updated"));

    }
    @Test
    public void patchStudentInfo() {

        String jsonData = "{firstName\": \"Avani}";
        given()
                .baseUri("http://localhost:8080/student/132")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .then().statusCode(200)
                .body("msg", equalTo("Updated"));

    }
    @Test
    public void deleteStudentInfo() {
        given()
                .pathParam("id", 132)
                .when()
                .delete("http://localhost:8080/student/{id}")
                .then()
                .statusCode(204);
    }

    @Test // delete above id
    public void verifyNewStudentInfoDeletedByID() {
        given()
                .pathParam("id", 132)
                .when()
                .get("http://localhost:8080/student/{id}")
                .then().statusCode(404);
    }
}
