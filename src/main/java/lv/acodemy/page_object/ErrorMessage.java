package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ErrorMessage {

    private final By errorMessageName = By.id("name_help");
    private final By errorMessageEmail = By.id("email_help");
    private final By errorMessageGender = By.id("gender_help");
    WebDriver driver = LocalDriverManager.getInstance();
    WebDriverWait waiter;
    public ErrorMessage(WebDriverWait waiter) {
        this.waiter = waiter;
    }

    public WebElement getErrorNameLocator() {
        return driver.findElement(errorMessageName);
    }

    public WebElement getErrorEmailLocator() {
        return driver.findElement(errorMessageEmail);
    }

    public WebElement getErrorGenderLocator() {
        return driver.findElement(errorMessageGender);
    }

    public String getErrorNameMsg() {
        waiter.until(textToBePresentInElement(getErrorNameLocator(), "Please enter student name"));
        return getErrorNameLocator().getText();
    }

    public String getErrorEmailMsg() {
        waiter.until(textToBePresentInElement(getErrorEmailLocator(), "Please enter student email"));
        return getErrorEmailLocator().getText();
    }

    public String getErrorGenderMsg() {
        waiter.until(textToBePresentInElement(getErrorGenderLocator(), "Please select a gender"));
        return getErrorGenderLocator().getText();
    }
}

