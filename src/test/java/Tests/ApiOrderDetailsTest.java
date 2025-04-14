package Tests;

import BaseApi.BaseApiTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ApiOrderDetailsTest extends BaseApiTest {

    private final String validToken = "56ie5hmwbni9b02wppiqi27a3qjejx8m";
    private final String invalidToken = "invalid_token";
    private final String validIncrementId = "2003262640";
    private final String invalidIncrementId = "123456";

    @Test(priority = 1)
    public void testOrderDetails_Valid_AR() {
        Response response = sendOrderDetailsRequest(validToken, validIncrementId, 2);
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
        Response response = sendOrderDetailsRequest(validToken, validIncrementId, 4);
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
        Response response = sendOrderDetailsRequest(invalidToken, validIncrementId, 2);
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("كعميل تطلبه غير موجود"))
                .body("otherError", equalTo("customerNotExist"));
    }

    @Test(priority = 4)
    public void testOrderDetails_InvalidToken_EN() {
        Response response = sendOrderDetailsRequest(invalidToken, validIncrementId, 4);
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("customer you are requesting does not exist"))
                .body("otherError", equalTo("customerNotExist"));
    }

    @Test(priority = 5)
    public void testOrderDetails_InvalidIncrementId_AR() {
        Response response = sendOrderDetailsRequest(validToken, invalidIncrementId, 2);
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("طلب غير صحيح"))
                .body("shippingMethod", containsString("لا تتوفر معلومات الشحن"));
    }

    @Test(priority = 6)
    public void testOrderDetails_InvalidIncrementId_EN() {
        Response response = sendOrderDetailsRequest(validToken, invalidIncrementId, 4);
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("Invalid Order"))
                .body("shippingMethod", containsString("No shipping information available"));
    }

    private Response sendOrderDetailsRequest(String token, String incrementId, int storeId) {
        return withDefaultHeaders()
                .queryParam("storeId", storeId)
                .queryParam("customerToken", token)
                .queryParam("incrementId", incrementId)
                .get("/mobileapi/v2_customer/orderdetails");
    }
}
