import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
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
        driver = new EdgeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @org.testng.annotations.Test
    public void signUp() {
        {
            driver.get("https://www.raneen.com/en/");
            driver.manage().window().setSize(new Dimension(1440, 797));
            driver.findElement(By.linkText("Create an Account")).click();
            driver.findElement(By.id("firstname")).sendKeys("ahmed");
            driver.findElement(By.id("lastname")).click();
            driver.findElement(By.id("lastname")).sendKeys("hussein");
            driver.findElement(By.id("mobile_number")).click();
            driver.findElement(By.id("mobile_number")).sendKeys("01144439988");
            driver.findElement(By.id("am-email-address")).click();
            driver.findElement(By.id("am-email-address")).sendKeys("ahmedtimoooooon@gmail.com");
            driver.findElement(By.id("password")).click();
            driver.findElement(By.id("password")).sendKeys("Ahmed@1995");
            driver.findElement(By.id("password-confirmation")).click();
            driver.findElement(By.id("password-confirmation")).sendKeys("Ahmed@1995");
            driver.findElement(By.id("customer_form_register_popup_showPassword")).click();
            driver.findElement(By.cssSelector("#customer-form-register-popup-send2 > span")).click();
            {
                WebElement element = driver.findElement(By.cssSelector("#customer-form-register-popup-send2 > span"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            {
                WebElement element = driver.findElement(By.tagName("body"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element, 0, 0).perform();
            }
        }
    }
}
