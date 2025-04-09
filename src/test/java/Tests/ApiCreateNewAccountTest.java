package Tests;

import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class ApiCreateNewAccountTest {
    private static final Logger log = LoggerFactory.getLogger(ApiCreateNewAccountTest.class);

    WebDriver driver;
    String customerToken;
    private String email;

    @BeforeTest
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage",
                "--disable-gpu", "--remote-allow-origins=*", "--disable-extensions", "--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        // Visit site before setting cookie
        driver.get("https://www.raneen.com/customer/account");

      //  driver.get("https://www.raneen.com"); // triggers domain registration
      //  ApiUtils.injectFrontendLogin(driver);

        // Step 4: Wait for login element to appear
        Utility.waitForPageToLoad(driver, 10);
        driver.navigate().refresh();
    }

    @AfterTest
    public void teardown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Test script for verifying the create new account functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Moataz Mustafa")
    @Link(name = "Create new account", url = "https://www.raneen.com/ar/")
    @Issue("https://raneen.atlassian.net/browse/RT-840")
    @TmsLink("Zephyr Scale")
    @Epic("Testing Automation")
    @Feature("Create new account")
    @Story("Verify Create new account functionality")

    @Test(priority = 1)
    public void validCreateNewAccount() {
        log.info("Starting valid Create New Account...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "2")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

            response.prettyPrint(); // ✅ Prints JSON body
        JsonPath json = response.jsonPath();

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(true))
                .body("message", equalTo("تم إنشاء حسابك بنجاح ، مرحبا بك"))
                .body("customerName", equalTo("Test User"))
                .body("customerId", notNullValue())
                .body("cartCount", equalTo(0))
                .body("customerEmail", equalTo(email))
                .body("customerToken", notNullValue());
        // ✅ Print token if you want to use it later
        this.customerToken = response.jsonPath().getString("customerToken");
        log.info("extracting customerToken + customerID + customerEmail...");
        System.out.println("🎉 Registration customerToken: " + json.getString("customerToken"));
        System.out.println("🎉 Registration customerId: " + json.getString("customerId"));
        System.out.println("🎉 Registration customerEmail: " + json.getString("customerEmail"));
        log.info("Finished test Register New User");
    }
    @Test(priority = 2)
    public void invalidPassword() {
        log.info("Starting invalid password test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "T")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "2")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

        response.prettyPrint(); // ✅ Prints JSON body

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("تحتاج كلمة المرور إلى 8  حرفًا على الأقل. أنشئ كلمة مرور جديدة وحاول مرة أخرى."));
        log.info("Finished invalid Password test");
    }
    @Test(priority = 3)
    public void invalidEmail() {
        log.info("Starting invalid Email test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "2")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

        response.prettyPrint(); // ✅ Prints JSON body

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("عنوان البريد الإلكتروني غير صالح."));
        log.info("Finished invalid Email test");
    }
    @Test(priority = 4)
    public void invalidFirstName() {
        log.info("Starting invalid First Name test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "2")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

        response.prettyPrint(); // ✅ Prints JSON body

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("\" الاسم الاول \" قيمة مطلوبة."));
        log.info("Finished invalid First Name test");
    }
    @Test(priority = 5)
    public void invalidLastName() {
        log.info("Starting invalid LastName test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "2")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!
        response.prettyPrint(); // ✅ Prints JSON body
        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("\" الاسم الأخير \" قيمة مطلوبة."));
        log.info("Finished invalid Last Name test");
    }
    @Test(priority = 6)
    public void validCreateNewAccountEN() {
        log.info("Starting validCreateNewAccount EN...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "4")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

        response.prettyPrint(); // ✅ Prints JSON body
        JsonPath json = response.jsonPath();

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(true))
                .body("message", equalTo("Your Account has been successfully created"))
                .body("customerName", equalTo("Test User"))
                .body("customerId", notNullValue())
                .body("cartCount", equalTo(0))
                .body("customerEmail", equalTo(email))
                .body("customerToken", notNullValue());
        // ✅ Print token if you want to use it later
        this.customerToken = response.jsonPath().getString("customerToken");
        log.info("extracting customerToken + customerID + customerEmail for EN store...");
        System.out.println("🎉 Registration customerToken: " + json.getString("customerToken"));
        System.out.println("🎉 Registration customerId: " + json.getString("customerId"));
        System.out.println("🎉 Registration customerEmail: " + json.getString("customerEmail"));
        log.info("Finished test Register New User EN");
    }
    @Test(priority = 7)
    public void invalidPasswordEN() {
        log.info("Starting invalid password EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "T")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "4")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

        response.prettyPrint(); // ✅ Prints JSON body

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("The password needs at least 8 characters. Create a new password and try again."));
        log.info("Finished invalid Password EN test");
    }
    @Test(priority = 8)
    public void invalidEmailEN() {
        log.info("Starting invalid Email EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "4")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

        response.prettyPrint(); // ✅ Prints JSON body

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("Invalid email address."));
        log.info("Finished invalid Email EN test");
    }
    @Test(priority = 9)
    public void invalidFirstNameEN() {
        log.info("Starting invalid First Name EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "")
                .multiPart("lastName", "User")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "4")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!

        response.prettyPrint(); // ✅ Prints JSON body

        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("\"First Name\" is a required value."));
        log.info("Finished invalidFirstName test");
    }
    @Test(priority = 10)
    public void invalidLastNameEN() {
        log.info("Starting invalid Last Name EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        String API_KEY = "Y5ZmtCOv1hqj8sTXFtQJrCPGgv6FyT4o";
        String API_SECRET = "fmBlzxAUlMsfI7ifCyyCYRArlUOuEwYZ";
        this.email = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "")
                .multiPart("email", email)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "4")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!
        response.prettyPrint(); // ✅ Prints JSON body
        // ✅ Field assertions
        log.info("Starting field assertions...");
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body("success", equalTo(false))
                .body("message", equalTo("\"Last Name\" is a required value."));
        log.info("Finished invalid Last Name EN test");
        log.info("Test Cases Passed Successfully");
    }
}

