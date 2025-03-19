package Tests;

import Pages.*;
import Utilities.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WsOrderCycleTest {
    private static final Logger log = LoggerFactory.getLogger(WsOrderCycleTest.class);

    WebDriver driver;
    WebHomePage webHomePage;
    WebCategoryPage webCategoryPage;
    WebProductPage webProductPage;
    LogInPage logInPage;

    @BeforeTest
    public void prepare() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();

        // Configure ChromeOptions for Incognito Mode //
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*"); // Fix potential CORS issues
        options.addArguments("--disable-extensions");
        // options.addArguments("--incognito"); // Enable incognito mode
        options.addArguments("--start-maximized"); // Start maximized
        options.addArguments("--disable-cache"); // Disable caching
        driver = new ChromeDriver(options); // Initialize driver with options

        // Clear cookies explicitly (if needed)
        driver.manage().deleteAllCookies();

        // Initialize WebHomePage object
        webHomePage = new WebHomePage(driver);
        webCategoryPage = new WebCategoryPage(driver);
        webProductPage = new WebProductPage(driver);
        logInPage = new LogInPage(driver);
    }

    @AfterTest
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
        BasePage.openUrl("https://www.raneen.com/ar/electronics/televisions-accessories/tv?product_list_order=name");
        Utility.waitForPageToLoad(driver, 10); // Ensure the page is fully loaded
        log.info("Clicking on login button...");
        logInPage.clickLoginButton();
        log.info("wait for 6 sec...");
        Thread.sleep(6000);
        log.info("Entering login credentials...");
        logInPage.enterEmail("moatazmustafa123@gmail.com");
        logInPage.enterPassword("mm@123456");
        log.info("Submitting login form...");
        logInPage.clickSubmitLogIn();
        Utility.waitForPageToLoad(driver, 10); // Ensure the page is fully loaded


    }
}
