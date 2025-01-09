package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePage {

    // Locators for the login page
    private By logInButton = By.cssSelector("#ammenu-header-container > div.panel.wrapper > div > ul > li.link.authorization-link > a");
    private By emailField = By.id("email");
    private By passwordField = By.id("am-pass");
    private By loginButton = By.id("customer-form-login-popup-send2");
    private By googleLoginButton = By.cssSelector("#amsl-login-content > div.amsl-social-wrapper > div > div > div:nth-child(1) > a ");
    private By facebookLoginButton = By.cssSelector("#amsl-login-content > div.amsl-social-wrapper > div > div > div:nth-child(2) > a");

    // Constructor
    public LogInPage(WebDriver driver) {
        super(driver);
    }

    // Method to enter email
    public void enterEmail(String email) {
        Utility.findWebElement(driver, emailField).sendKeys("moatazmustafa123@gmail.com");
    }

    // Method to enter password
    public void enterPassword(String password) {
        Utility.findWebElement(driver, passwordField).sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        Utility.findWebElement(driver, loginButton).click();
    }

    // Method to click Google Login
    public void clickGoogleLoginButton() {
        Utility.findWebElement(driver, googleLoginButton).click();
    }

    // Method to click Facebook Login
    public void clickFacebookLoginButton() {
        Utility.findWebElement(driver, facebookLoginButton).click();
    }

    // Method to perform login
    public void performLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}
