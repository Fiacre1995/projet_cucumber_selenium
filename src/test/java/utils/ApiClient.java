package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    public static RequestSpecification request() {
        return RestAssured.given()
                .baseUri(ConfigReader.get("api.base.url"))
                .contentType(ContentType.JSON);
    }
}
