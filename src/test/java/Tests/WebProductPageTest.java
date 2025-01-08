package Tests;

import Pages.BasePage;
import Pages.WebCategoryPage;
import Pages.WebHomePage;
import Pages.WebProductPage;
import Utilities.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WebProductPageTest {
    private static final Logger log = LoggerFactory.getLogger(WebProductPageTest.class);

    WebDriver driver;
    WebHomePage webHomePage;
    WebCategoryPage webCategoryPage;
    WebProductPage webProductPage;

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
    }

    @AfterTest
    public void teardown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Test script for verifying the Web product page functionality")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Moataz Mustafa")
    @Link(name = "Raneen WS-product-page", url = "https://www.raneen.com/ar/")
    @Issue("https://raneen.atlassian.net/browse/RT-838")
    @TmsLink("Zephyr Scale")
    @Epic("Testing Automation")
    @Feature("product page")
    @Story("Verify product page functionality")
    @Test
    public void testProductPageFunctionality() throws InterruptedException {
        log.info("Starting test for product page functionality...");

        // Step 1: Navigate to the homepage
        log.info("Navigating to the product page: https://www.raneen.com/ar/test33");

        BasePage.openUrl("https://www.raneen.com/ar/test33");
        Utility.waitForPageToLoad(driver, 10); // Ensure the page is fully loaded
        log.info("Validating product title...");

        webProductPage.assertProductTitle();
        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-main > div.page-title-wrapper.product > h1 > span")).getText(), is("test33")); //prod title
        log.info("Validating product price...");
        webProductPage.assertProductPrice();
        log.info("Validating product image...");
        webProductPage.assertProductImage();
        log.info("Validating product brand...");
        webProductPage.assertProductBrand();
        log.info("Validating product reviews summary...");
        webProductPage.assertProductReviewsSummary(); //stars review section
        log.info("Clicking on the delivery time button...");
        webProductPage.assertDeliveryTimeButton();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("/html/body/div[3]/main/div[3]/div/div[3]/div[2]/div[9]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        log.info("Closing the delivery time popup...");
        Utility.clickingOnElement(driver, By.cssSelector("#delivery-time-popup > span")); //close delivery time popup
        log.info("Validating additional product details..Warranty.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.product-warranty > div.value > div.title")).getText(), is("الضمان"));
        log.info("Validating additional product details..Free-Return.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.product-3-info > div:nth-child(1) > div.value > div.title")).getText(), is("إرجاع مجاني"));
        log.info("Validating additional product details..Shop-Privately.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.product-3-info > div:nth-child(2) > div.value > div.title")).getText(), is("تسوق بخصوصية"));
        log.info("Validating additional product details..Seller-Name.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.product-seller > a")).getText(), is("testsellertwo"));
        log.info("Validating additional product details..Seller-Rating.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.seller-ratings-wrapper > div > div:nth-child(1) > div.value > div.title")).getText(), is("تقييم البائع"));
        log.info("Validating additional product details..Seller-Quality.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.seller-ratings-wrapper > div > div:nth-child(2) > div.value > div > h3")).getText(), is("أداء البائع"));
        log.info("Validating additional product details..Quality-Rating.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.seller-ratings-wrapper > div > div:nth-child(3) > div.value > div.title")).getText(), is("تقييم الجودة"));
        log.info("Validating additional product details..Average-Speed-Rating.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.seller-ratings-wrapper > div > div:nth-child(4) > div.value > div.title")).getText(), is("سرعة التحضير"));
        log.info("Validating additional product details..Customers-Rating.");

        assertThat(driver.findElement(By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary > div.seller-ratings-wrapper > div > div:nth-child(5) > div.value > div.title")).getText(), is("تقييم العملاء"));
        Utility.findWebElement(driver, By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-main > div.product-info-secondary"));
        log.info("Validating installment section...");

        Utility.findWebElement(driver, By.cssSelector("#maincontent > div.columns > div > div.ocean-product-view-block.ocean-product-installment > div")); //installment section
        log.info("Validating more info section...");

        Utility.findWebElement(driver, By.xpath("/html/body/div[3]/div[6]/div/div/div[1]")); //more info section
        log.info("Validating add to wish list button...");

        webProductPage.assertAddToWishList();
        log.info("Validating add to cart button...");

        webProductPage.assertAddToCartButton();
        log.info("Validating aty selector...");

        webProductPage.assertQtySelector();
        log.info("Validating product description...");

        webProductPage.assertProductDescription();
        log.info("Validating product attributes...");

        webProductPage.assertProductAttributes();
        log.info("Validating reviews section...");

        webProductPage.assertReviewsSection();
        log.info("Validating submit review button...");

        webProductPage.assertWriteReviewButton();
        log.info("Validating product first image...");

        Utility.clickingOnElement(driver, By.xpath("/html/body/div[3]/main/div[3]/div/div[3]/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[3]/div/img")); //click on product image
        log.info("Validating product second image...");

        Utility.clickingOnElement(driver, By.xpath("/html/body/div[3]/main/div[3]/div/div[3]/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[2]/div[1]")); //click on product image
        log.info("Validating clicking on product main image...");

        Utility.clickingOnElement(driver, By.xpath("/html/body/div[7]")); //click on product image
        log.info("Test for product page functionality completed successfully.");

    }
}
