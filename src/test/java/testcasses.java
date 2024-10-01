import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class testcasses {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeMethod
    public void setUp()
    {
     // System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-cache", "--disable-application-cache", "--incognito");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @org.testng.annotations.Test
    public void signin() {
        driver.get("https://www.raneen.com/en/");
        driver.manage().window().setSize(new Dimension(1440, 797));
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("Ahmedtimoon@gmail.com");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("Ag@123456");
        try {
            Thread.sleep(1000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("customer-form-login-popup-send2")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".custom-cat-slider .pagebuilder-column:nth-child(3) .pagebuilder-mobile-hidden"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }

    }
}
