package Tests;

import Utilities.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
    }


    @AfterTest
    public void shutdown() {
        driver.quit();

    }

    @Description("this script for testing smart store registration")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Moataz Mustafa")
    @Link(name = "smart store", url = "https://www.raneen.com/ss_zayed/smartstore/home/map")
    @Issue("https://raneen.atlassian.net/browse/RT-521?atlOrigin=eyJpIjoiODg0YjM1MmU5NjdhNDlmOTk2MTE3MTlhMGY1YzkxYjMiLCJwIjoiaiJ9")
    @TmsLink("Zephyr Scale")
    @Epic("Testing Automation")
    @Feature("Registration")
    @Story("valid registration")
    @Test
    public void open_screen_setup() throws InterruptedException {

        Utility.openUrl(driver, "https://www.raneen.com/ss_zayed/smartstore/screen/setup/");
        driver.findElement(By.id("screen_type")).click();
        {
            WebElement dropdown = driver.findElement(By.id("screen_type"));
            dropdown.findElement(By.xpath("//option[. = 'التسجيل']")).click();
        }
        Utility.sendData(driver, By.id("identifier"), "motaz registration");
        Utility.sendData(driver, By.id("password"), "mm@123456");
        Utility.clickingOnElement(driver, By.xpath("//form[@id='screen-setup-form']/div[5]/div/button/span"));
        Utility.sendData(driver, By.id("phone"), "01555888444");
        Utility.clickingOnElement(driver, By.xpath("//form/button"));
        driver.switchTo().newWindow(WindowType.TAB);
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        driver.get("https://www.raneen.com/admin/admin/");
        Utility.sendData(driver, By.id("username"), "motaz.mostafa");
        Utility.sendData(driver, By.id("login"), "mm@123456");
        Utility.clickingOnElement(driver, By.xpath("//form[@id='login-form']/fieldset/div[3]/div/button/span"));  //login button
        Utility.clickingOnElement(driver, By.xpath("//li[@id='menu-ocean-core-ocean']/a")); //ocean tab
        Utility.clickingOnElement(driver, By.xpath("//li[@id='menu-ocean-core-ocean']/div/ul/li[2]/ul/li/div/ul/li[4]/a/span")); //otp tab
        String OTP = Utility.getText(driver, By.xpath("//tbody/tr/td[3]"));
        //String OTP =driver.findElement(By.xpath("//tbody/tr/td[3]")).getText(); //copy
        driver.switchTo().window((String) windowHandles[0]);  //smart store
        Utility.sendData(driver, By.id("otp"), OTP);  //paste otp
        //driver.findElement(By.id("otp")).sendKeys(OTP);    //paste otp
        Utility.sendData(driver, By.id("firstname"), "moataz");  //firstname
        Utility.sendData(driver, By.id("lastname"), "mustafa");  //lastname
        Utility.clickingOnElement(driver, By.xpath("//div[2]/div/form/button")); //submit

        driver.switchTo().window((String) windowHandles[1]);   //back to admin
        Utility.clickingOnElement(driver, By.xpath("//nav/ul/li[8]/a/span")); //customers
        Utility.clickingOnElement(driver, By.xpath("//li[8]/div/ul/li/a/span")); //all customer tab

        Utility.waitForPageToLoad(driver, 1000);

        Utility.findWebElement(driver, By.xpath("//div[2]/div/div[2]/input")).clear();
        Utility.sendData(driver, By.xpath("//div[2]/div/div[2]/input"), "01555888444");  //lastname
        Thread.sleep(5000);
        Utility.clickingOnElement(driver, By.xpath("//div[2]/div/div[2]/button"));
        Thread.sleep(10000);
        Utility.clickingOnElement(driver, By.xpath("//td[22]/a"));
        Thread.sleep(5000);
        Utility.clickingOnElement(driver, By.xpath("//button[3]/span"));
        Thread.sleep(5000);
        Utility.clickingOnElement(driver, By.xpath("//footer/button[2]/span"));

        Thread.sleep(5000);
    }
}
