package Tests;

import Pages.BasePage;
import Pages.WebHomePage;
import Utilities.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WebSideBarMenuTest {

    WebDriver driver;
    WebHomePage webHomePage;

    @BeforeTest
    public void setup() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize WebHomePage object
        webHomePage = new WebHomePage(driver);
    }

    @AfterTest
    public void teardown() {
        // Close the browser after the test
        if (driver != null) {
            //    driver.quit();
        }
    }

    @Description("Test script for verifying the Web Sidebar functionality")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Moataz Mustafa")
    @Link(name = "Raneen Sidebar", url = "https://www.raneen.com/ar/")
    @Issue("https://raneen.atlassian.net/browse/RT-534")
    @TmsLink("Zephyr Scale")
    @Epic("Testing Automation")
    @Feature("Sidebar Menu")
    @Story("Verify sidebar menu functionality")
    @Test
    public void testSidebarMenuFunctionality() {
        // Step 1: Navigate to the homepage
        BasePage.openUrl("https://www.raneen.com/ar/");
        Utility.waitForPageToLoad(driver, 10); // Ensure the page is fully loaded

        // Step 2: Interact with Sidebar Menu
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load

        // Click on each sidebar menu item
        webHomePage.clickAccountTab();
        webHomePage.clickSignInFromAccount();
        assertThat(Utility.findWebElement(driver, By.cssSelector("#ammenu-sidebar > section.ammenu-account-section > ul > li:nth-child(1) > a > p")).getText(), is("تسجيل الدخول"));
        Utility.clickingOnElement(driver, By.cssSelector("#social-login-popup > button\n"));
        webHomePage.clickSignUpFromAccount();
        assertThat(Utility.findWebElement(driver, By.cssSelector("#maincontent > div.page-title-wrapper > h1")).getText(), is("إنشاء حساب جديد"));
        Utility.findWebElement(driver, By.cssSelector("#form-validate"));  //assertions for signup forum
        driver.navigate().back();
        Utility.waitForPageToLoad(driver, 10); // Wait for menu to load
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickAccountTab();
        webHomePage.clickHelpAndSettings();
        webHomePage.clickMenuTab();
        webHomePage.clickAppliances();
        webHomePage.clickMobiles();
        webHomePage.clickElectronics();
        webHomePage.clickSecuritySurveillanceSystems();
        webHomePage.clickHome();
        webHomePage.clickKitchen();
        webHomePage.clickTextile();
        webHomePage.clickFurniture();
        webHomePage.clickFamilyProducts();
        webHomePage.clickFashion();
        webHomePage.clickLightingHomeDecore();
        webHomePage.clickHomeEssentials();
        webHomePage.clickAccountTab();
    }
}
