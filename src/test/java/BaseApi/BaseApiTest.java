package BaseApi;

import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    public WebDriver driver;
    public String baseUrl = "https://www.raneen.com";
    public final String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
    public final String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";

    @BeforeClass
    public void setup() {
        setupRestAssured();
        setupWebDriver();
    }

    private void setupRestAssured() {
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssured.config().encoderConfig(
                EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT));
    }

    private void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080", "--no-sandbox",
                "--disable-dev-shm-usage", "--disable-gpu", "--remote-allow-origins=*", "--disable-extensions");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.get(baseUrl + "/customer/account");
        Utility.waitForPageToLoad(driver, 10);
        driver.navigate().refresh();
    }

    protected RequestSpecification withDefaultHeaders() {
        return RestAssured.given()
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data");
    }

    public static String randomEmail() {
        return "testuser" + System.currentTimeMillis() + "@gmail.com";
    }

    public static String existingEmail() {
        return "motaz.mostafa@raneen.com";
    }

    protected String extractToken(Response response) {
        return response.jsonPath().getString("customerToken");
    }

    protected String buildLoginPayload(String email, String password) {
        return "{ \"username\": \"" + email + "\", \"password\": \"" + password + "\" }";
    }

    protected Response loginCustomer(String email, String password) {
        return withDefaultHeaders()
                .multiPart("username", email)
                .multiPart("password", password)
                .multiPart("storeId", "2")
                .post("/mobileapi/customer/login");
    }

    protected String extractSessionId(Response response) {
        return response.getCookie("PHPSESSID");
    }

    protected void injectSessionIntoBrowser(String sessionId) {
        Cookie sessionCookie = new Cookie.Builder("PHPSESSID", sessionId)
                .domain("www.raneen.com")
                .path("/")
                .isHttpOnly(true)
                .build();
        driver.manage().addCookie(sessionCookie);
        driver.navigate().refresh();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
