package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static String loginAndGetToken(String username, String password) {
        RestAssured.baseURI = "https://www.raneen.com";

        Response response = given()
                .contentType("application/form-data")
     //           .formParam("os", "ios")
                
                .formParam("storeId", "2")
                .formParam("mFactor", "0")
     //           .formParam("quoteId", "0")
                .formParam("username", username)
                .formParam("password", password)
                .when()
                .post("/mobileapi/customer/login/")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("🔁 Login response: " + response.asString());

        String token = response.jsonPath().getString("customerToken");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("❌ Login failed: customerToken is missing in response.");
        }

        System.out.println("🔐 Extracted customerToken: " + token);
        return token;
    }

}
