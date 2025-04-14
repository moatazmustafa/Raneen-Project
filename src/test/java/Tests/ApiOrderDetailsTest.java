package Tests;

import BaseApi.BaseApiTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ApiOrderDetailsTest extends BaseApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiOrderDetailsTest.class);
    // Test data
     String validToken = "56ie5hmwbni9b02wppiqi27a3qjejx8m";
     String invalidToken = "invalid_token";
     String validIncrementId = "2003262640";
     String invalidIncrementId = "123456";

    @Test(priority = 1)
    public void testOrderDetails_Valid_AR() {
        log.info(" Testing Order Details API with valid token and increment ID in Arabic");

        Response response  = withDefaultHeaders()
                .queryParam("storeId", 2) //AR storeId
                .queryParam("customerToken", validToken)
                .queryParam("incrementId", validIncrementId)
                .get("/ar/mobileapi/v2_customer/orderdetails");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("customerEmail", equalTo("motaz.mostafa@raneen.com"))
                .body("incrementId", equalTo(validIncrementId))
                .body("state", equalTo("canceled"))
                .body("orderTotal", containsString("جنيه"))
                .body("orderData.itemList[0].name", equalTo("test33"));
    }

    @Test(priority = 2)
    public void testOrderDetails_Valid_EN() {
        log.info(" Testing Order Details API with valid token and increment ID in English");
        Response response  = withDefaultHeaders()
                .queryParam("storeId", 4)
                .queryParam("customerToken", validToken)
                .queryParam("incrementId", validIncrementId)
                .get("/en/mobileapi/v2_customer/orderdetails");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("customerEmail", equalTo("motaz.mostafa@raneen.com"))
                .body("incrementId", equalTo(validIncrementId))
                .body("state", equalTo("canceled"))
                .body("orderTotal", containsString("EGP"))
                .body("orderData.itemList[0].name", equalTo("test33"));
    }

    @Test(priority = 3)
    public void testOrderDetails_InvalidToken_AR() {
        log.info(" Testing Order Details API with invalid token in Arabic");
        Response response  = withDefaultHeaders()
                .queryParam("storeId", 2)
                .queryParam("customerToken", invalidToken)
                .queryParam("incrementId", validIncrementId)
                .get("/ar/mobileapi/v2_customer/orderdetails");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("كعميل تطلبه غير موجود"))
                .body("otherError", equalTo("customerNotExist"));
    }

    @Test(priority = 4)
    public void testOrderDetails_InvalidToken_EN() {
        log.info(" Testing Order Details API with invalid token in English");
        Response response  = withDefaultHeaders()
                .queryParam("storeId", 4)
                .queryParam("customerToken", invalidToken)
                .queryParam("incrementId", validIncrementId)
                .get("/en/mobileapi/v2_customer/orderdetails");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("As customer you are requesting does not exist, so you need to logout."))
                .body("shippingMethod", containsString("No shipping information available"))
                .body("otherError", equalTo("customerNotExist"));
    }

    @Test(priority = 5)
    public void testOrderDetails_InvalidIncrementId_AR() {
        log.info(" Testing Order Details API with invalid increment ID in Arabic");
        Response response  = withDefaultHeaders()
                .queryParam("storeId", 2)
                .queryParam("customerToken", validToken)
                .queryParam("incrementId", invalidIncrementId)
                .get("/ar/mobileapi/v2_customer/orderdetails");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("طلب غير صحيح."))
                .body("shippingMethod", containsString("لا تتوفر معلومات الشحن"));
    }

    @Test(priority = 6)
    public void testOrderDetails_InvalidIncrementId_EN() {
        log.info(" Testing Order Details API with invalid increment ID in English");
        Response response  = withDefaultHeaders()
                .queryParam("storeId", 4)
                .queryParam("customerToken", validToken)
                .queryParam("incrementId", invalidIncrementId)
                .get("/mobileapi/v2_customer/orderdetails");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("Invalid Order"))
                .body("shippingMethod", containsString("No shipping information available"));
    }

}
