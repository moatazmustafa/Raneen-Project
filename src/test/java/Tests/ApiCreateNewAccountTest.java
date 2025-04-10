package Tests;

import BaseApi.BaseApiTest;
import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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


public class ApiCreateNewAccountTest extends BaseApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiCreateNewAccountTest.class);

    String customerToken;
    String existingEmail;
    String randomEmail;
    private RequestSpecification createAccountRequest(String firstName, String lastName, String email, String password, String confirmation, String storeId) {
        return withDefaultHeaders()
                .multiPart("firstName", firstName)
                .multiPart("lastName", lastName)
                .multiPart("email", email)
                .multiPart("password", password)
                .multiPart("confirmation", confirmation)
                .multiPart("storeId", storeId);
    }

    @Description("Test script for verifying the create new account functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Moataz Mustafa")
    @Link(name = "Create new account", url = "https://www.raneen.com/ar/")
    @Issue("https://raneen.atlassian.net/browse/RT-840")
    @TmsLink("Zephyr Scale")
    @Epic("Testing Automation")
    @Feature("Create new account")
    @Story("Create new account")


    @Test(priority = 1)
    public void validCreateNewAccount() {
        log.info("Starting valid Create New Account...");
        randomEmail = randomEmail();

        Response response = given()
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
                .multiPart("password", "Test@123456")
                .multiPart("confirmation", "Test@123456")
                .multiPart("storeId", "2")
                .post("mobileapi/customer/createaccount"); // ✅ Make sure this is a real endpoint!


        log.info("validCreateNewAccount response: " + response.asString());

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
                .body("customerEmail", equalTo(randomEmail))
                .body("customerToken", notNullValue());
        // ✅ Print token if you want to use it later
        this.customerToken = response.jsonPath().getString("customerToken");
        this.randomEmail = response.jsonPath().getString("customerEmail");

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
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
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
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
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
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
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
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "")
                .multiPart("email", randomEmail)
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
    public void validateExistingEmail() {
        log.info("Starting existing Email test...");
        RestAssured.baseURI = "https://www.raneen.com";
        existingEmail = existingEmail(); // ✅ existing Email
        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", existingEmail)
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
                .body("message", equalTo("يوجد بالفعل حساب بعنوان البريد الإلكتروني هذا."));
        log.info("Finished existing Email test");
    }
    @Test(priority = 7)
    public void validCreateNewAccountEN() {
        log.info("Starting validCreateNewAccount EN...");
        RestAssured.baseURI = "https://www.raneen.com";
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email


        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
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
                .body("customerEmail", equalTo(randomEmail))
                .body("customerToken", notNullValue());
        // ✅ Print token if you want to use it later
        this.customerToken = response.jsonPath().getString("customerToken");
        this.randomEmail = response.jsonPath().getString("customerEmail");

        log.info("extracting customerToken + customerID + customerEmail for EN store...");
        System.out.println("🎉 Registration customerToken: " + json.getString("customerToken"));
        System.out.println("🎉 Registration customerId: " + json.getString("customerId"));
        System.out.println("🎉 Registration customerEmail: " + json.getString("customerEmail"));
        log.info("Finished test Register New User EN");
    }
    @Test(priority = 8)
    public void invalidPasswordEN() {
        log.info("Starting invalid password EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
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
    @Test(priority = 9)
    public void invalidEmailEN() {
        log.info("Starting invalid Email EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
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
    @Test(priority = 10)
    public void invalidFirstNameEN() {
        log.info("Starting invalid First Name EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "")
                .multiPart("lastName", "User")
                .multiPart("email", randomEmail)
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
    @Test(priority = 11)
    public void invalidLastNameEN() {
        log.info("Starting invalid Last Name EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        this.randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com"; // ✅ Generate a unique email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "")
                .multiPart("email", randomEmail)
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
    }
    @Test(priority = 12)
    public void validateExistingEmailEN() {
        log.info("Starting existing Email EN test...");
        RestAssured.baseURI = "https://www.raneen.com";
        existingEmail = existingEmail(); // ✅ existing Email

        Response response = given()
                .config(RestAssured.config().encoderConfig(
                        encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT)))
                .header("APIKey", API_KEY)
                .header("APISecret", API_SECRET)
                .contentType("multipart/form-data")
                .multiPart("firstName", "Test")
                .multiPart("lastName", "User")
                .multiPart("email", existingEmail)
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
                .body("message", equalTo("There is already an account with this email address."));
        log.info("Finished existingE mail EN test");
        log.info("Test Cases Passed Successfully");
    }
}

