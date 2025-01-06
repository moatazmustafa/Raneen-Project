package Tests;

import Pages.BasePage;
import Pages.WebCategoryPage;
import Pages.WebHomePage;
import Utilities.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WebSideBarMenuTest {

    WebDriver driver;
    WebHomePage webHomePage;
    WebCategoryPage webCategoryPage;

    @BeforeTest
    public void prepare() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();

        // Configure ChromeOptions for Incognito Mode //
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
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
    }

    @AfterTest
    public void teardown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
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
    public void testSidebarMenuFunctionality() throws InterruptedException {
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
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();


        webHomePage.clickMobiles();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar

        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        //   webHomePage.clickMenuTab();


        webHomePage.clickSecuritySurveillanceSystems();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        Thread.sleep(3000);
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

        webHomePage.clickHome();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

        webHomePage.clickKitchen();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

        webHomePage.clickTextile();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

        webHomePage.clickFurniture();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

        webHomePage.clickFamilyProducts();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        Utility.findWebElement(driver, By.cssSelector("#amasty-shopby-product-list > div.products.wrapper.grid.products-grid.amscroll-page > ol > li:nth-child(3) > div > div.product.details.product-item-details > span > div.price-box.price-final_price")); //assert product price
        //  webCategoryPage.assertProductPrice();
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

        webHomePage.clickFashion();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        Utility.findWebElement(driver, By.cssSelector("#amasty-shopby-product-list > div.products.wrapper.grid.products-grid.amscroll-page > ol > li:nth-child(3) > div > div.product.details.product-item-details > span > div.price-box.price-final_price")); //assert product price
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

        webHomePage.clickLightingHomeDecore();
        webCategoryPage.assertallProductsDiv();
        webCategoryPage.assertFirstProduct();
        webCategoryPage.assertSecondProduct();
        webCategoryPage.assertThirdProduct();
        Utility.findWebElement(driver, By.cssSelector("#amasty-shopby-product-list > div.products.wrapper.grid.products-grid.amscroll-page > ol > li:nth-child(3) > div > div.product.details.product-item-details > span > div.price-box.price-final_price")); //assert product price
        webCategoryPage.assertProductTitle();
        webCategoryPage.assertFiltersSideBar();
        webCategoryPage.assertSortByButton();
        webCategoryPage.assertSortByDropDownButton();
        webCategoryPage.switchToListView();
        Thread.sleep(3000);
        webCategoryPage.switchToGridView();
        webHomePage.clickAllCategories(); // Open sidebar
        Utility.waitForPageToLoad(driver, 5); // Wait for menu to load
        webHomePage.clickMenuTab();
        Thread.sleep(3000);

    }
}
