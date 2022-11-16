package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class APIHelper {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/api")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void shouldPayByApprovedCard(DataHelper.InfoForPayByCard infoForPay) {
        String requestBody = DataHelper.createJSON(infoForPay);
        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/v1/pay")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    public static void shouldPayByDeclinedCard(DataHelper.InfoForPayByCard infoForPay) {
        String requestBody = DataHelper.createJSON(infoForPay);
        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/v1/pay")
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }

    public static void shouldPayByRandomCard(DataHelper.InfoForPayByCard infoForPay) {
        String requestBody = DataHelper.createJSON(infoForPay);
        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/v1/pay")
                .then()
                .statusCode(500);
    }

    public static void shouldCreditByApprovedCard(DataHelper.InfoForPayByCard infoForPay) {
        String requestBody = DataHelper.createJSON(infoForPay);
        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/v1/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("APPROVED"));
    }

    public static void shouldCreditByDeclinedCard(DataHelper.InfoForPayByCard infoForPay) {
        String requestBody = DataHelper.createJSON(infoForPay);
        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/v1/credit")
                .then()
                .statusCode(200)
                .body("status", equalTo("DECLINED"));
    }

    public static void shouldCreditByRandomCard(DataHelper.InfoForPayByCard infoForPay) {
        String requestBody = DataHelper.createJSON(infoForPay);
        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/v1/credit")
                .then()
                .statusCode(500);
    }
}

