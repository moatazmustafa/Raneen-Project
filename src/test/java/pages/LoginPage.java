package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends pages.BasePage {
    private By signInLink = By.linkText("Sign In");
    private By emailField = By.id("email");
    private By passwordField = By.id("am-pass");
    private By loginButton = By.id("customer-form-login-popup-send2");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        driver.findElement(signInLink).click();
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}