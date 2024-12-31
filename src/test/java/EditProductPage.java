import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditProductPage {
    private WebDriver driver;

    // Locators
    private By enableSwitch = By.cssSelector(".admin__field:nth-child(2) .admin__actions-switch-label");
    private By saveButton = By.cssSelector("#save-button > span");

    // Constructor
    public EditProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enableProduct() {
        driver.findElement(enableSwitch).click();
    }

    public void saveProduct() {
        driver.findElement(saveButton).click();
    }
}
