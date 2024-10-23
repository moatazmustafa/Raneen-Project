import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethod {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-cache", "--disable-application-cache", "--incognito");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @org.testng.annotations.Test
    public void cOD() {
        driver.get("https://www.raneen.com/en");
        driver.manage().window().setSize(new Dimension(1440, 783));

        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.name("login[username]")).click();
        driver.findElement(By.name("login[username]")).sendKeys("ahmedtimoon@gmail.com");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("Ag@123456");

        driver.findElement(By.id("customer_form_login_popup_showPassword")).click();

        driver.findElement(By.id("customer-form-login-popup-send2")).click();

        try {
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.linkText("Kids & Toys")).click();



        driver.findElement(By.cssSelector(".slide:nth-child(2) img")).click();

        driver.findElement(By.cssSelector(".slide:nth-child(3) img")).click();

        driver.findElement(By.id("sorter")).click();
        {
            WebElement dropdown = driver.findElement(By.id("sorter"));
            dropdown.findElement(By.xpath("//option[. = 'Product Name']")).click();
        }
        js.executeScript("window.scrollTo(0,338)");

        driver.findElement(By.linkText("0123 AAA Test")).click();

        driver.findElement(By.id("product-addtocart-button")).click();

        try {
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        js.executeScript("window.scrollTo(0,132)");

        {
            WebElement element = driver.findElement(By.cssSelector(".counter:nth-child(2)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".counter:nth-child(2)")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }


        driver.findElement(By.id("top-cart-btn-checkout")).click();

        try {
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        js.executeScript("window.scrollTo(0,132)");
        js.executeScript("window.scrollTo(0,175)");

        driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/div/button")).click();

        try {
            Thread.sleep(4000); // Wait for 4 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        js.executeScript("window.scrollTo(0,0)");
        driver.findElement(By.xpath("//*[@id=\"cashondelivery\"]")).click();
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("agreement_cashondelivery_8")).click();

        driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button")).click();
        try {
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

}
