package Tests;

import Pages.AdminPage;
import Pages.SSRegistrationPage;
import Utilities.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationTest {

    WebDriver driver;
    SSRegistrationPage SSRegistrationPage;
    AdminPage adminPage;

    @BeforeTest
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        SSRegistrationPage = new SSRegistrationPage(driver);
        adminPage = new AdminPage(driver);
    }

    @AfterTest
    public void shutdown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testRegistrationFlow() throws InterruptedException {
        try {
            // Step 1: Navigate to Registration Page
            SSRegistrationPage.openUrl("https://www.raneen.com/ss_zayed/smartstore/screen/setup/");
            SSRegistrationPage.selectScreenType("التسجيل");
            SSRegistrationPage.enterIdentifier("motaz registration");
            SSRegistrationPage.enterPassword("mm@123456");
            SSRegistrationPage.submitForm();
            String randomPhoneNumber = Utility.generateRandomPhoneNumber("015");
            SSRegistrationPage.enterPhone(randomPhoneNumber);
            SSRegistrationPage.generateMyBarcode();

            // Step 2: Open Admin Portal
            driver.switchTo().newWindow(WindowType.TAB);
            Object[] windowHandles = driver.getWindowHandles().toArray();
            driver.switchTo().window((String) windowHandles[1]);
            driver.get("https://www.raneen.com/admin/admin/");
            adminPage.login("motaz.mostafa", "mm@123456");
            adminPage.navigateToOtpTab();
            String otp = adminPage.getOtp();

            // Step 3: Complete Registration
            driver.switchTo().window(driver.getWindowHandles().iterator().next());
            SSRegistrationPage.enterOtp(otp);
            SSRegistrationPage.enterFirstName("moataz");
            SSRegistrationPage.enterLastName("mustafa");
            SSRegistrationPage.submitRegistration();

            // Step 4: Verify Registration in Admin Portal
            driver.switchTo().window((String) windowHandles[1]);
            adminPage.navigateToCustomersTab();
            Utility.findWebElement(driver, By.xpath("//div[2]/div/div[2]/input")).clear();
            Utility.sendData(driver, By.xpath("//div[2]/div/div[2]/input"), randomPhoneNumber);
            Thread.sleep(15000);
            Utility.clickingOnElement(driver, By.xpath("//div[2]/div/div[2]/button")); // Search
            Thread.sleep(5000);

            // Open Customer Details
            Utility.clickingOnElement(driver, By.xpath("//td[22]/a")); // Open customer details
            Thread.sleep(3000);
            Utility.clickingOnElement(driver, By.xpath("//button[3]/span")); // Action
            Thread.sleep(3000);
            Utility.clickingOnElement(driver, By.xpath("//footer/button[2]/span")); // Save or confirm

        } catch (Exception e) {
            e.printStackTrace();
            Utility.takeScreenShot(driver, "TestFailure");
            throw e;
        }
    }
}
