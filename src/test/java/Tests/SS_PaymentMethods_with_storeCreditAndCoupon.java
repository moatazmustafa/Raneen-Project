package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SS_PaymentMethods_with_storeCreditAndCoupon {

    WebDriver driver;


    @BeforeTest
    public void prepare() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @AfterTest
    public void shutdown() {

        driver.quit();

    }


    @Test
    public void open_screen_setup() throws InterruptedException {

        driver.get("https://www.raneen.com/admin/admin/");  //open magento
        driver.findElement(By.cssSelector("#username")).sendKeys("motaz.mostafa");  //user name
        driver.findElement(By.cssSelector("#login")).sendKeys("mm@123456");  //password
        driver.findElement(By.cssSelector("#login-form > fieldset > div.form-actions > div.actions > button")).click();  //sign in
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#menu-magento-catalog-catalog > a")).click();  // catalog
        driver.findElement(By.cssSelector("#menu-magento-catalog-catalog > div > ul > li.item-inventory.parent.level-1 > div > ul > li.item-catalog-products.level-2 > a")).click();  //products
        Thread.sleep(20000);
        driver.findElement(By.xpath("//*[@id=\"fulltext\"]")).clear();  // product search field
        driver.findElement(By.xpath("//*[@id=\"fulltext\"]")).sendKeys("test33"); //
        driver.findElement(By.cssSelector("#container > div > div.admin__data-grid-header > div:nth-child(1) > div.data-grid-search-control-wrap > button")).click();  // search
        driver.findElement(By.cssSelector("#idscheck413176")).click();  // checkbox
        driver.findElement(By.cssSelector("#container > div > div.admin__data-grid-header > div.admin__data-grid-header-row.row.row-gutter > div.col-xs-2 > div > button")).click();  // mass action
        driver.findElement(By.cssSelector("#container > div > div.admin__data-grid-header > div.admin__data-grid-header-row.row.row-gutter > div.col-xs-2 > div > div > ul > li._parent > span")).click();  //change status
        driver.findElement(By.cssSelector("#container > div > div.admin__data-grid-header > div.admin__data-grid-header-row.row.row-gutter > div.col-xs-2 > div > div > ul > li._parent._visible > ul > li:nth-child(1) > span")).click();  //enable
        driver.switchTo().newWindow(WindowType.TAB);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        driver.get("https://www.raneen.com/ss_zayed/catalog/product/view/id/413176"); // test product
        driver.findElement(By.cssSelector("#smart-addtocart-button")).click();  // add t cart
        driver.findElement(By.cssSelector("#phone-number")).sendKeys("01500008863");  // phone num
        driver.findElement(By.cssSelector("#html-body > div.modals-wrapper > aside > div.modal-inner-wrap > footer > button")).click();  // add t cart
        Thread.sleep(5000);
        driver.switchTo().newWindow(WindowType.TAB);
        Object[] windowHandles2=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles2[1]);
        driver.get("https://www.raneen.com/ss_zayed/smartstore/screen/setup");
        driver.findElement(By.cssSelector("#screen_type")).click();  // screen type
        driver.findElement(By.cssSelector("#screen_type > option:nth-child(4)")).click();  //checkout screen
        driver.findElement(By.cssSelector("#identifier")).sendKeys("motaz checkout");  //
        driver.findElement(By.cssSelector("#password")).sendKeys("mm@123456");  //
        driver.findElement(By.cssSelector("#screen-setup-form > div.actions-toolbar > div > button")).click();  // setup screen
        driver.findElement(By.cssSelector("#phone")).sendKeys("01500008863");  //send OTP
        driver.findElement(By.cssSelector("#smartstore-otp > button")).click();  //send
        driver.switchTo().window((String) windowHandles2[0]); //magento
        driver.findElement(By.cssSelector("#menu-ocean-core-ocean > a")).click();  //ocean
        driver.findElement(By.cssSelector("#menu-ocean-core-ocean > div > ul > li:nth-child(2) > ul > li:nth-child(1) > div > ul > li.item-smartotp.level-2 > a")).click();  //OTPs
        String OTP =driver.findElement(By.xpath("//tbody/tr/td[3]")).getText(); //copy
        driver.switchTo().window((String) windowHandles2[1]); //magento
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("")).click();  //
        driver.findElement(By.cssSelector("")).click();  //
        driver.findElement(By.cssSelector("")).click();  //
        driver.findElement(By.cssSelector("")).click();  //
        driver.findElement(By.cssSelector("")).click();  //







    }
}