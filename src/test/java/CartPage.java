import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By cartIcon = By.cssSelector(".counter:nth-child(2)");
    private By checkoutButton = By.id("top-cart-btn-checkout");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
