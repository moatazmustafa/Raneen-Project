import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class disableProduct {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeMethod
    public void setUp() {
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
    public void enableproduct() {
        driver.get("https://www.raneen.com/admin/admin/");
        driver.manage().window().setSize(new Dimension(1440, 797));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("ahmed.gaafar");
        driver.findElement(By.id("login")).sendKeys("Ahly@1907");
        driver.findElement(By.cssSelector(".action-login > span")).click();
        try {
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("#menu-magento-catalog-catalog > a > span")).click();
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector(".item-catalog-products span")).click();
        try {
            Thread.sleep(20000); // Wait for 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("fulltext")).click();
        driver.findElement(By.id("fulltext")).clear();
        driver.findElement(By.id("fulltext")).sendKeys("0123 AAA Test");
        driver.findElement(By.cssSelector(".data-grid-search-control-wrap:nth-child(5) > .action-submit")).click();

        try {
            Thread.sleep(20000); // Wait for 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("td:nth-child(8) > .data-grid-cell-content")).click();
        driver.findElement(By.linkText("Edit")).click();
        try {
            Thread.sleep(20000); // Wait for 20 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector(".admin__field:nth-child(2) .admin__actions-switch-label")).click();
        driver.findElement(By.cssSelector("#save-button > span")).click();
        try {
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }
}
