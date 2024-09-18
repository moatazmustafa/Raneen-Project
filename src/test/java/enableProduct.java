
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class enableProduct {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
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
        driver.findElement(By.cssSelector("#menu-magento-catalog-catalog > a > span")).click();
        driver.findElement(By.cssSelector(".item-catalog-products span")).click();
        driver.findElement(By.id("fulltext")).click();
        driver.findElement(By.id("fulltext")).sendKeys("0123 test");
        driver.findElement(By.cssSelector(".data-grid-search-control-wrap:nth-child(5) > .action-submit")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".data-grid-search-control-wrap:nth-child(5) > .action-submit"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.id("fulltext")).click();
        {
            WebElement element = driver.findElement(By.id("fulltext"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.id("fulltext")).sendKeys("0123 AAA");
        driver.findElement(By.id("fulltext")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("td:nth-child(8) > .data-grid-cell-content")).click();
        driver.findElement(By.cssSelector("td:nth-child(8) > .data-grid-cell-content")).click();
        driver.findElement(By.linkText("Edit")).click();
        driver.findElement(By.cssSelector(".admin__field:nth-child(2) .admin__actions-switch-label")).click();
        driver.findElement(By.cssSelector("#save-button > span")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("#save-button > span"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.close();
    }
}
