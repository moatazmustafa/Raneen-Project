package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebCategoryPage extends BasePage {

    // Locators for the category pagejhbjhbjhj
    private By firstProduct = By.cssSelector("div.products-grid div.product-item:nth-child(1)"); // First product in the grid
    private By secondProduct = By.cssSelector("div.products-grid div.product-item:nth-child(2)"); // Second product
    private By thirdProduct = By.cssSelector("div.products-grid div.product-item:nth-child(3)"); // Third product
    private By productTitle = By.cssSelector(".product-item .product-item-name"); // Product title
    private By productPrice = By.cssSelector(".product-item .price"); // Product price
    private By gridViewButton = By.cssSelector(".view-mode .grid"); // Grid view toggle button
    private By listViewButton = By.cssSelector(".view-mode .list"); // List view toggle button
    private By filterByCategory = By.cssSelector(".filter-options .filter-option-title"); // Filter by category
    private By sortByDropdown = By.cssSelector(".toolbar-sorter select"); // Sort by dropdown
    private By loadMoreButton = By.cssSelector(".toolbar .action.loadmore"); // Load more button (if available)

    // Constructor
    public WebCategoryPage(WebDriver driver) {
        super(driver);
    }

    // Methods to interact with elements

    public void clickFirstProduct() {
        Utility.clickingOnElement(driver, firstProduct);
    }

    public void clickSecondProduct() {
        Utility.clickingOnElement(driver, secondProduct);
    }

    public void clickThirdProduct() {
        Utility.clickingOnElement(driver, thirdProduct);
    }

    public String getFirstProductTitle() {
        return Utility.getText(driver, productTitle);
    }

    public String getFirstProductPrice() {
        return Utility.getText(driver, productPrice);
    }

    public void switchToGridView() {
        Utility.clickingOnElement(driver, gridViewButton);
    }

    public void switchToListView() {
        Utility.clickingOnElement(driver, listViewButton);
    }

    public void openFilterByCategory() {
        Utility.clickingOnElement(driver, filterByCategory);
    }

    public void selectSortOption(String optionText) {
        Utility.selectingFromDropDown(driver, sortByDropdown, optionText);
    }

    public void clickLoadMoreButton() {
        Utility.clickingOnElement(driver, loadMoreButton);
    }
}
