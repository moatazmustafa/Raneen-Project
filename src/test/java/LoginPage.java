import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By signInLink = By.linkText("Sign In");
    private By usernameField = By.name("login[username]");
    private By passwordField = By.id("am-pass");
    private By showPasswordCheckbox = By.id("customer_form_login_popup_showPassword");
    private By loginButton = By.id("customer-form-login-popup-send2");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void clickSignIn() {
        driver.findElement(signInLink).click();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void showPassword() {
        driver.findElement(showPasswordCheckbox).click();
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
