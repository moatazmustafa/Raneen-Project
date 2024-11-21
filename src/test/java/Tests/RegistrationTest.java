package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void prepare ()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
    }


    @AfterTest
    public void shutdown ()
    {
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

        driver.get("https://www.raneen.com/ss_zayed/smartstore/screen/setup/");
        driver.findElement(By.id("screen_type")).click();
        {
            WebElement dropdown = driver.findElement(By.id("screen_type"));
            dropdown.findElement(By.xpath("//option[. = 'التسجيل']")).click();
        }

        driver.findElement(By.id("identifier")).sendKeys("motaz registration");
        driver.findElement(By.id("password")).sendKeys("mm@123456");
        driver.findElement(By.xpath("//form[@id='screen-setup-form']/div[5]/div/button/span")).click();
        driver.findElement(By.id("phone")).sendKeys("01555777444");
        driver.findElement(By.xpath("//form/button")).click();
        Thread.sleep(3000);
        driver.switchTo().newWindow(WindowType.TAB);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        driver.get("https://www.raneen.com/admin/admin/");
        driver.findElement(By.id("username")).sendKeys("motaz.mostafa");
        driver.findElement(By.id("login")).sendKeys("mm@123456");
        driver.findElement(By.xpath("//form[@id='login-form']/fieldset/div[3]/div/button/span")).click(); //login button
        //driver.findElement(By.xpath("//li[@id='menu-ocean-core-ocean']/a")).click();                      // ocean tab
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='menu-ocean-core-ocean']/a"))).click();

        //driver.findElement(By.xpath("//li[@id='menu-ocean-core-ocean']/div/ul/li[2]/ul/li/div/ul/li[4]/a/span")).click();  //otp tab
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='menu-ocean-core-ocean']/div/ul/li[2]/ul/li/div/ul/li[4]/a/span"))).click();
        String OTP =driver.findElement(By.xpath("//tbody/tr/td[3]")).getText(); //copy
        driver.switchTo().window((String) windowHandles[0]);
        driver.findElement(By.id("otp")).sendKeys(OTP);    //paste otp
        driver.findElement(By.id("firstname")).sendKeys("moataz"); //firstname
        driver.findElement(By.id("lastname")).sendKeys("mustafa"); //lastname
        driver.findElement(By.xpath("//div[2]/div/form/button")).click(); //submit
        Thread.sleep(3000);
        driver.switchTo().window((String) windowHandles[1]);   //back to admin
        //driver.findElement(By.xpath("//nav/ul/li[8]/a/span")).click(); //customers
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nav/ul/li[8]/a/span"))).click();//customers
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[8]/div/ul/li/a/span"))).click();//all customer tab

        //driver.findElement(By.xpath("//li[8]/div/ul/li/a/span")).click(); //all customer tab
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[2]/div/div[2]/input")).clear();
        driver.findElement(By.xpath("//div[2]/div/div[2]/input")).sendKeys("01555777444");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[2]/div/div[2]/button")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//td[22]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[3]/span")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//footer/button[2]/span")).click();
        Thread.sleep(5000);
    }
}
