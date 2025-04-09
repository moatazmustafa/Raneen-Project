import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class testcasses {
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
        driver.findElement(By.name("login[username]")).sendKeys("#####");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("######");

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
        driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button")).click();
//        js.executeScript("window.scrollTo(0,2)");
//        js.executeScript("window.scrollTo(0,194)");
//        vars.put("ahmedtimoon@gmail.com", "id=email");
//        vars.put("Ag@123456", "id=password");
//        vars.put("ahmedtimoon@gmail.com", "id=customer-email");
//        vars.put("Ag@123456", "id=pass");
    }
    @org.testng.annotations.Test
    public void aman() {
        driver.get("https://www.raneen.com/en");
        driver.manage().window().setSize(new Dimension(1440, 783));
        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.name("login[username]")).click();
        driver.findElement(By.name("login[username]")).sendKeys("######");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("######");

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
        js.executeScript("window.scrollTo(0,1)");
        driver.findElement(By.xpath("//*[@id=\"aman\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[4]/div[2]/div[5]/div/button")).click();
    }
    @org.testng.annotations.Test
    public void installmentwithNationalBankofQatarAlAhly() {
        driver.get("https://www.raneen.com/en");
        driver.manage().window().setSize(new Dimension(1440, 783));

        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.name("login[username]")).click();
        driver.findElement(By.name("login[username]")).sendKeys("######");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("######");

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
        try {
            Thread.sleep(5000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        js.executeScript("window.scrollTo(0,101)");
        js.executeScript("window.scrollTo(0,0)");

        driver.findElement(By.id("ocean_QNBGate_installments")).click();

        try {
            Thread.sleep(4000); // Wait for 4 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div[@id=\'checkout-payment-method-load\']/div/div/div[7]/div[2]/div[4]/div/button")).click();
        driver.close();
    }
    @org.testng.annotations.Test
    public void nBECash() {
        driver.get("https://www.raneen.com/en");
        driver.manage().window().setSize(new Dimension(1440, 783));
        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.name("login[username]")).click();
        driver.findElement(By.name("login[username]")).sendKeys("######");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("######");

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
        driver.findElement(By.id("ocean_NBEGate_cash")).click();
        js.executeScript("window.scrollTo(0,507)");
        driver.findElement(By.xpath("//div[@id=\'checkout-payment-method-load\']/div/div/div[5]/div[2]/div[3]/div/button")).click();

        driver.close();
    }
    @org.testng.annotations.Test
    public void nBEinstallment() {
        driver.get("https://www.raneen.com/en");
        driver.manage().window().setSize(new Dimension(1440, 783));
        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.name("login[username]")).click();
        driver.findElement(By.name("login[username]")).sendKeys("######");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("######");

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
        js.executeScript("window.scrollTo(0,165)");
        driver.findElement(By.cssSelector(".payment-method:nth-child(7) > .payment-method-title > .label")).click();
        driver.findElement(By.cssSelector(".\\_active > .payment-method-content > .actions-toolbar span")).click();
//        driver.switchTo().frame(2);
//        driver.findElement(By.linkText("Cancel")).click();
        driver.close();
    }
    @org.testng.annotations.Test
    public void premiumcard() {
        driver.get("https://www.raneen.com/en");
        driver.manage().window().setSize(new Dimension(1440, 783));
        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.name("login[username]")).click();
        driver.findElement(By.name("login[username]")).sendKeys("######");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("######");

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
        js.executeScript("window.scrollTo(0,101)");
        driver.findElement(By.xpath("//*[@id=\"premium\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[5]/div[2]/div[5]/div/button")).click();
        driver.close();
    }
    @org.testng.annotations.Test
    public void wallet() {
        driver.get("https://www.raneen.com/en");
        driver.manage().window().setSize(new Dimension(1440, 783));
        driver.findElement(By.linkText("Sign In")).click();

        driver.findElement(By.name("login[username]")).click();
        driver.findElement(By.name("login[username]")).sendKeys("######");
        driver.findElement(By.id("am-pass")).click();
        driver.findElement(By.id("am-pass")).sendKeys("######");

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
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("wallet")).click();
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("wallet-phone-input")).click();
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("wallet-phone-input")).sendKeys("*********");
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector(".actions-toolbar:nth-child(7) .action")).click();
        driver.close();
    }
}
