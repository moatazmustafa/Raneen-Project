package Tests;

import Pages.LogInPage;
import Pages.WebCategoryPage;
import Pages.WebHomePage;
import Pages.WebProductPage;
import Utils.ApiUtils;
import Utils.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SSProductPageTest {
    private static final Logger log = LoggerFactory.getLogger(SSProductPageTest.class);

    WebDriver driver;
    WebHomePage webHomePage;
    WebCategoryPage webCategoryPage;
    WebProductPage webProductPage;
    LogInPage logInPage;
    String customerToken;

    @BeforeTest
    public void prepare() {
        customerToken = ApiUtils.loginAndGetToken("motaz.mostafa@raneen.com", "mm@123456");

        if (customerToken == null) {
            throw new RuntimeException("❌ Login via API failed. Token is null.");
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        driver = new ChromeDriver(options);

        driver.get("https://www.raneen.com");

        // Inject token
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('customerToken', '" + customerToken + "');"
        );
        driver.navigate().refresh();
    }


  //  @AfterTest
    public void teardown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Test script for verifying the web wish list functionality")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Moataz Mustafa")
    @Link(name = "Raneen web wish list", url = "https://www.raneen.com/ar/")
    @Issue("https://raneen.atlassian.net/browse/RT-840")
    @TmsLink("Zephyr Scale")
    @Epic("Testing Automation")
    @Feature("web wish list")
    @Story("Verify web wish list functionality")
    @Test
    public void testOrderCycleFunctionality() throws InterruptedException {

        // Step 1: Navigate to the homepage
        log.info("Starting test for wish list functionality...");
        log.info("open TV category page...");
        Utility.openUrl("https://www.raneen.com/ar/electronics/televisions-accessories/tv?product_list_order=name");
//        Utility.waitForPageToLoad(driver, 10); // Ensure the page is fully loaded
//        log.info("Clicking on login button...");
//        logInPage.clickLoginButton();
        log.info("wait for 6 sec...");
//        Thread.sleep(6000);
//        log.info("Entering login credentials...");
//        logInPage.enterEmail("moatazmustafa123@gmail.com");
//        logInPage.enterPassword("mm@123456");
//        log.info("Submitting login form...");
//        logInPage.clickSubmitLogIn();
//        Utility.waitForPageToLoad(driver, 10); // Ensure the page is fully loaded

        Utility.findWebElement(driver, By.xpath("/html/body/div[3]/main/div[3]/h1/span"));
        Utility.assertText(driver, By.xpath("/html/body/div[3]/main/div[3]/h1/span"),"التلفزيونات");
        Utility.findWebElement(driver, By.xpath("/html/body/div[3]/header/div[1]/div/ul/li[1]/span")); // or any known user element

    }
}
