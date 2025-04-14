package Tests;

import BaseApi.BaseApiTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiAccountInfoDataTest extends BaseApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiAccountInfoDataTest.class);

    private final String customerToken = "56ie5hmwbni9b02wppiqi27a3qjejx8m";  // Replace with token from login test

    @Test(priority = 1)
    public void testAccountInfoData_ValidToken_AR() {
        log.info("Starting with valid customer token AR...");

        Response response = withDefaultHeaders()
                .queryParam("customerToken", customerToken)
                .queryParam("storeId", 2)
                .get("/ar/mobileapi/customer/accountinfodata");

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("email", equalTo("motaz.mostafa@raneen.com"))
                .body("firstName", equalTo("test"))
                .body("lastName", equalTo("sellertow"))
                .body("mobile_number", equalTo("01555888986"))
                .body("eTag", notNullValue());
    }

    @Test(priority = 2)
    public void testAccountInfoData_InvalidToken_AR() {
        log.info("Starting with invalid customer token AR...");
        String invalidCustomerToken = "invalid_token_123";

        Response response = withDefaultHeaders()
                .queryParam("customerToken", invalidCustomerToken)
                .queryParam("storeId", 2)
                .get("/ar/mobileapi/customer/accountinfodata");

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", equalTo("كعميل تطلبه غير موجود ، لذلك تحتاج إلى تسجيل الخروج."))
                .body("otherError", equalTo("customerNotExist"));
    }

    @Test(priority = 3)
    public void testAccountInfoData_ValidToken_EN() {
        log.info("Starting with valid customer token EN...");

        Response response = withDefaultHeaders()
                .queryParam("customerToken", customerToken)
                .queryParam("storeId", 4) // EN store
                .get("/en/mobileapi/customer/accountinfodata");

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("email", equalTo("motaz.mostafa@raneen.com"))
                .body("firstName", equalTo("test"))
                .body("lastName", equalTo("sellertow"))
                .body("mobile_number", equalTo("01555888986"))
                .body("eTag", notNullValue());
    }

    @Test(priority = 4)
    public void testAccountInfoData_InvalidToken_EN() {
        log.info("Starting with invalid customer token EN...");

        String invalidCustomerToken = "invalid_token_123";
        Response response = withDefaultHeaders()
                .queryParam("customerToken", invalidCustomerToken)
                .queryParam("storeId", 4) // EN store
                .get("/en/mobileapi/customer/accountinfodata");

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", equalTo("As customer you are requesting does not exist, so you need to logout."))
                .body("otherError", equalTo("customerNotExist"));
    }
}
