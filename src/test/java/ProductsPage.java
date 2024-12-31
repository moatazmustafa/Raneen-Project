import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    private WebDriver driver;

    // Locators
    private By searchField = By.id("fulltext");
    private By searchButton = By.cssSelector(".data-grid-search-control-wrap:nth-child(5) > .action-submit");
    private By productEditButton = By.cssSelector("td:nth-child(8) > .data-grid-cell-content");
    private By editLink = By.linkText("Edit");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void searchForProduct(String productName) {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void openProductForEditing() {
        driver.findElement(productEditButton).click();
        driver.findElement(editLink).click();
    }
}
