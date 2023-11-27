package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoObject {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By burgerMenu = By.className("bm-burger-button");
    private final By logoutButton = By.id("logout_sidebar_link");
    private final By productsLabel = By.className("product_label");
    private final By loginBox = By.className("login-box");
    ChromeDriver driver = LocalDriverManager.getInstance();
    WebDriverWait waiter;
    public SauceDemoObject(WebDriverWait waiter) {
        this.waiter = waiter;
    }

    public WebElement getProductLabelLocator() {
        return driver.findElement(productsLabel);
    }

    public WebElement getLoginBoxLocator() {
        return driver.findElement(loginBox);
    }

    public void setUsernameField(String input) {
        driver.findElement(username).sendKeys(input);
    }

    public void setPasswordField(String input) {
        driver.findElement(password).sendKeys(input);
    }

    public void loginButton() {
        driver.findElement(loginButton).click();
    }

    public void burgerMenuButton() {
        driver.findElement(burgerMenu).click();
    }

    public void logoutButton() {
        driver.findElement(logoutButton).click();
    }

    public String findProductsLabel() {
        waiter.until(ExpectedConditions.textToBePresentInElement(getProductLabelLocator(), "Products"));
        return getProductLabelLocator().getText();
    }

    public String findLoginBox() {
        waiter.until(ExpectedConditions.textToBePresentInElement(
                getLoginBoxLocator(), "Epic sadface: Username and password do not match any user in this service"));
        return getLoginBoxLocator().getText();
    }
}
