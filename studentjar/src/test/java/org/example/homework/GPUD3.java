package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD3 {
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

        String data = "{ \n" +
                "    \"firstName\": \"sherlok\",\n" +
                "        \"lastName\": \"Holmes\",\n" +
                "        \"email\": \"holmes@Duisac.net\",\n" +
                "        \"programme\": \"Financial Analysis\",\n" +
                "        \"courses\": [\n" +
                "            \"Accounting\",\n" +
                "            \"Statistics\"\n" +
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

        String jsonData = "{ \n" +
                "    \"firstName\": \"sherlok\",\n" +
                "        \"lastName\": \"Holmes\",\n" +
                "        \"email\": \"holme1s@Duisac.net\",\n" +
                "        \"programme\": \"Financial Analysis\",\n" +
                "        \"courses\": [\n" +
                "            \"Accounting\",\n" +
                "            \"Statistics\"\n" +
                "        ]\n" +
                "    }";

        given()
                .baseUri("http://localhost:8080/student/128")
                .contentType(ContentType.JSON)
                .body(jsonData)

                .then().statusCode(200)
                .body("msg", equalTo("Student updated"));

    }
    @Test
    public void patchStudentInfo() {
        String jsondata = "{\"email\": \"holme1s@Duisac.net\"}";
        given()
                .baseUri("http://localhost:8080/student/126")
                .contentType(ContentType.JSON)
                .body(jsondata)
                .when()
                .patch()
                .then().statusCode(200)
                .body("msg", equalTo("Updated"));

    }

    @Test
    public void deleteStudentInfo() {
        given()
                .pathParam("id", 126)
                .when()
                .delete("http://localhost:8080/student/{id}")
                .then()
                .statusCode(204);
    }

    @Test // delete above id
    public void verifyNewStudentInfoDeletedByID() {
        given()
                .pathParam("id", 126)
                .when()
                .get("http://localhost:8080/student/{id}")
                .then().statusCode(404);
    }


}
