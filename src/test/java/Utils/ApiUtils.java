package Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class ApiUtils {

    private static final String BASE_URI = "https://www.raneen.com";
    private static final String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
    private static final String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";

    private static final String USERNAME = "motaz.mostafa@raneen.com";
    private static final String PASSWORD = "mm@123456";

    /**
     * Logs in via mobile API and returns the customerToken.
     */
    public static String loginAndGetCustomerToken() {
        RestAssured.baseURI = BASE_URI;

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
             //   .header("apiKey", API_KEY)
              //  .header("apiSecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("storeId", "2")
                .multiPart("mFactor", "0")
                .multiPart("username", USERNAME)
                .multiPart("password", PASSWORD)
             //   .multiPart("mobile", "01500008863")
                .post("/mobileapi/customer/login/");

        response.prettyPrint();

        if (response.jsonPath().getBoolean("success")) {
            String token = response.jsonPath().getString("customerToken");
            System.out.println("✅ customerToken: " + token);
            return token;
        } else {
            throw new RuntimeException("❌ Login failed: " + response.jsonPath().getString("message"));
        }
    }

    /**
     * Logs in and returns the PHPSESSID cookie to use with Selenium.
     */
    public static String loginAndGetSessionId() {
        RestAssured.baseURI = BASE_URI;

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
           //     .header("apiKey", API_KEY)
            //    .header("apiSecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("storeId", "2")
                .multiPart("mFactor", "0")
                .multiPart("username", USERNAME)
                .multiPart("password", PASSWORD)
         //       .multiPart("mobile", "01500008863")
                .post("/mobileapi/customer/login/");

        String sessionId = response.getCookie("PHPSESSID");
        if (sessionId != null) {
            System.out.println("✅ PHPSESSID: " + sessionId);
            return sessionId;
        } else {
            throw new RuntimeException("❌ Failed to get PHPSESSID from response.");
        }

    }

    /**
     * Injects the login session into WebDriver browser.
     */
    public static void injectFrontendLogin(WebDriver driver) {
        String sessionId = loginAndGetSessionId();

        // Step 1: Inject PHPSESSID cookie
        Cookie sessionCookie = new Cookie.Builder("PHPSESSID", sessionId)
                .domain("www.raneen.com")
                .path("/")
                .isHttpOnly(true)
                .build();
        driver.manage().addCookie(sessionCookie);

        // Step 2: Visit page that will trigger Magento customer session recognition
        driver.get("https://www.raneen.com/customer/account");
        driver.navigate().refresh();

        System.out.println("✅ Injected PHPSESSID. Magento will now sync login via GUI.");
    }

}
