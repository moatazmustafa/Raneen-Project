package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases {
    private WebDriver driver;
    private BasePage basePage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--disable-cache", "--disable-application-cache", "--incognito");
        driver = new ChromeDriver(options);
        basePage = new BasePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        basePage.quitDriver();
    }

    @Test
    public void signin() {
        basePage.openUrl("https://www.raneen.com/en/");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 797));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login("Ahmedtimoon@gmail.com", "Ag@123456");
    }
}