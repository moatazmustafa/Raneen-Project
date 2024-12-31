import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    // Locators
    private By catalogMenu = By.cssSelector("#menu-magento-catalog-catalog > a > span");
    private By productsSubMenu = By.cssSelector(".item-catalog-products span");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void openCatalogMenu() {
        driver.findElement(catalogMenu).click();
    }

    public void clickProductsSubMenu() {
        driver.findElement(productsSubMenu).click();
    }
}
