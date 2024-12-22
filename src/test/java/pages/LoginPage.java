package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By signInLink;
    private By emailField;
    private By passwordField;
    private By loginButton;

    public LoginPage(WebDriver driver, By signInLink, By emailField, By passwordField, By loginButton) {
        super(driver);
        this.signInLink = signInLink;
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.loginButton = loginButton;
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
