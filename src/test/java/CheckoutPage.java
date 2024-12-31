import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;
    private JavascriptExecutor js;

    // Locators
    private By shippingButton = By.xpath("//div[@id='shipping-method-buttons-container']/div/button");
    private By codOption = By.id("cashondelivery");
    private By agreementCheckbox = By.id("agreement_cashondelivery_8");
    private By placeOrderButton = By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // Methods
    public void proceedWithShipping() {
        driver.findElement(shippingButton).click();
    }

    public void selectCOD() {
        driver.findElement(codOption).click();
    }

    public void agreeToTerms() {
        driver.findElement(agreementCheckbox).click();
    }

    public void placeOrder() {
        driver.findElement(placeOrderButton).click();
    }

    public void scrollTo(int x, int y) {
        js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }
}
