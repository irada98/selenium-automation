package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Notifications {

    WebDriver driver = LocalDriverManager.getInstance();

    WebDriverWait waiter;

    public Notifications(WebDriverWait waiter) {
        this.waiter = waiter;
    }

    private final By notificationMsg = By.className("ant-notification-notice-message");

    public WebElement getNotificationLocator() {
        return driver.findElement(notificationMsg);
    }

    public String getNotificationSuccessMsg() {
        waiter.until(textToBePresentInElement(getNotificationLocator(), "Student successfully added"));
        return getNotificationLocator().getText();
    }
}
