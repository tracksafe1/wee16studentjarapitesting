package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD2 {
    Response response;
    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;

    @Test
    public void getAllStudentinfo() {
        given().log().all()
                .get("http://localhost:8080/student/list")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void createStudentInfo() {

        String data = "{\n" +
                "        \"firstName\": \"kinjal1\",\n" +
                "        \"lastName\": \"shah\",\n" +
                "        \"email\": \"ki2212a@gmail.com\",\n" +
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


//    public void createStudentinfo1() {
//        String jsondata = "{\n" +
//                "        \"firstName\": \"kinjal\",\n" +
//                "        \"lastName\": \"shah\",\n" +
//                "        \"email\": \"egest1as.rhoncus.Proin@massaQuisqueporttitor.org\",\n" +
//                "        \"programme\": \"Financial Analysis\",\n" +
//                "        \"courses\": [\n" +
//                "            \"Accounting\",\n" +
//                "            \"Statistics\"\n" +
//                "        ]\n" +
//                "    }";
//
//        response = RestAssured.given().log().all()
//               .contentType(ContentType.JSON)
//                .when()
//               .body(jsondata)
//              .post("http://localhost:8080/student");
//       response.then().statusCode(201)
//               .body("msg", equalTo("Student added"));
//   }

    @Test
    public void patchStudentInfo() {
        String jsondata = "{\"email\": \"ki4a@gmail.com\"}";
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
    public void updateStudentInfo(){
        String jsondata="{\n" +
                "    \"firstName\": \"kinjal21\",\n" +
                "    \"lastName\": \"shah\",\n" +
                "    \"email\": \"ki4a@gmail.com\",\n" +
                "    \"programme\": \"Financial Analysis\",\n" +
                "    \"courses\": [\n" +
                "        \"Accounting\",\n" +
                "        \"Statistics\"\n" +
                "    ]\n" +
                "}";
        given()
                .baseUri("http://localhost:8080/student/126")
                .contentType(ContentType.JSON)
                .body(jsondata)
                .then().statusCode(200)
                .body("msg", equalTo("Student Updated"));
    }
    @Test
    public void deleteStudentInfo(){
        given()
               .pathParam("id",126)
                .when()
                .delete("http://localhost:8080/student/{id}")
                .then()
                .statusCode(204);
    }
    @Test
    public void verifyDeletedStudentInfo(){
        given()
                .pathParam("id",126)
                .when()
                .get("http://localhost:8080/student/{id}")
                .then().statusCode(404);
    }

}
