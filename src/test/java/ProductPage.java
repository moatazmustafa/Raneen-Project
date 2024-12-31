import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver driver;
    private JavascriptExecutor js;

    // Locators
    private By kidsAndToysLink = By.linkText("Kids & Toys");
    private By ToysLink = By.linkText("Toys");
    private By sorterDropdown = By.id("sorter");
    private By productNameLink = By.linkText("0123 AAA Test");
    private By addToCartButton = By.id("product-addtocart-button");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // Methods
    public void clickKidsAndToys() {
        driver.findElement(kidsAndToysLink).click();
    }
    public void clickToys() {
        driver.findElement(ToysLink).click();
    }

    public void selectSorterOption(String optionText) {
        WebElement dropdown = driver.findElement(sorterDropdown);
        dropdown.findElement(By.xpath("//option[. = '" + optionText + "']")).click();
    }

    public void selectProduct() {
        driver.findElement(productNameLink).click();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void scrollTo(int x, int y) {
        js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
    }
}
