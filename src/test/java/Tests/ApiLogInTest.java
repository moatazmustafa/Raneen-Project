package Tests;

import BaseApi.BaseApiTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiLogInTest extends BaseApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiLogInTest.class);

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        log.info("Starting valid LogIn...");

        String email = existingEmail(); // valid email
        String password = "mm@123456";  // valid password

        Response response = loginCustomer(email, password);

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("مرحبا test sellertow"))
                .body("is_locked", equalTo(false))
                .body("customerName", equalTo("test sellertow"))
                .body("customerEmail", equalTo(email))
                .body("customerId", notNullValue())
                .body("customerToken", notNullValue())
                .body("cartCount", equalTo(0));
        response.prettyPrint(); // ✅ Prints JSON body

        String token = extractToken(response);
        System.out.println("✅ Login successful. Token: " + token);
    }

    @Test(priority = 2)
    public void testLoginWithInvalidPassword() {
        log.info("Starting LogIn with invalid password...");

        String email = existingEmail();
        String password = "WrongPassword123";

        Response response = loginCustomer(email, password);

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", equalTo("معلومات الدخول خاطئة او كلمة المرور خاطئة."));
        response.prettyPrint(); // ✅ Prints JSON body

    }

    @Test(priority = 3)
    public void testLoginWithInvalidEmail() {
        log.info("Starting LogIn with invalid Email...");

        String email = "wrong" + System.currentTimeMillis() + "@gmail.com";
        String password = "mm@123456";

        Response response = loginCustomer(email, password);

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", equalTo("معلومات الدخول خاطئة او كلمة المرور خاطئة."));
        response.prettyPrint(); // ✅ Prints JSON body

    }

    @Test(priority = 4)
    public void testLoginWithEmptyCredentials() {
        log.info("Starting LogIn with empty credentials...");

        Response response = loginCustomer("", "");

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", equalTo("معلومات الدخول خاطئة او كلمة المرور خاطئة."));
        response.prettyPrint(); // ✅ Prints JSON body

    }
}
