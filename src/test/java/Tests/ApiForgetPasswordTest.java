package Tests;

import BaseApi.BaseApiTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiForgetPasswordTest extends BaseApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiForgetPasswordTest.class);

    @Test(priority = 1)
    public void testForgetPassword_ValidEmail() {
        log.info("Starting valid forget password...");

        String email = existingEmail();

        Response response = withDefaultHeaders()
                .multiPart("email", email)
                .multiPart("storeId", "2")
                .multiPart("websiteId", "0")
                .post("/ar/mobileapi/customer/forgotpassword/");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("message", containsString("فستتلقى بريدًا إلكترونيًا به ارتباط لإعادة تعيين كلمة المرور الخاصة بك."));
    }

    @Test(priority = 2)
    public void testForgetPassword_InvalidEmail() {
        log.info("Starting invalid Email forget password...");
        String email = "motaz.mostafa@.com";

        Response response = withDefaultHeaders()
                .multiPart("email", email)
                .multiPart("storeId", "2")
                .multiPart("websiteId", "0")
                .post("/ar/mobileapi/customer/forgotpassword/");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("عنوان البريد الإلكتروني غير صالح."));
    }

    @Test(priority = 3)
    public void testForgetPassword_EmptyEmail() {
        log.info("Starting invalid empty Email forget password...");

        Response response = withDefaultHeaders()
                .multiPart("email", "")
                .multiPart("storeId", "2")
                .multiPart("websiteId", "0")
                .post("/ar/mobileapi/customer/forgotpassword/");
        response.prettyPrint(); // ✅ Prints JSON body

        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .body("success", equalTo(false))
                .body("message", containsString("عنوان البريد الإلكتروني غير صالح."));
    }
}
