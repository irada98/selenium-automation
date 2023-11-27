package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteEditPage {

    private final By tableRow1DeleteButton = By.xpath(
            "//*[@id=\"root\"]/section/section/main/div/div/div/div/div/div[2]/div/table/tbody/tr[2]/td[6]/div/label[1]");
    private final By tableRow1EditButton = By.xpath(
            "//*[@id=\"root\"]/section/section/main/div/div/div/div/div/div[2]/div/table/tbody/tr[2]/td[6]/div/label[2]");
    private final By notificationWindow = By.className("ant-popover-content");
    private final By buttons = By.className("ant-popconfirm-buttons");
    ChromeDriver driver = LocalDriverManager.getInstance();
    WebDriverWait waiter;

    public DeleteEditPage(WebDriverWait waiter) {
        this.waiter = waiter;
    }

    public WebElement setNotificationWindowLocator() {
        return driver.findElement(notificationWindow);
    }

//    public WebElement notificationMsgLocator() {
//        return driver.findElement(notificationWindow);
//    }

    public void findFirstUserDelete() {
        driver.findElement(tableRow1DeleteButton).click();
    }

    public void findFirstUserEdit() {
        driver.findElement(tableRow1EditButton).click();
    }

    public String findYesButtonInPopupMsg() {
        waiter.until(ExpectedConditions.textToBePresentInElement(
                setNotificationWindowLocator(), "Are you sure to delete John Doe222\n" +
                        "  NoYes"));
        return setNotificationWindowLocator().getText();
    }

    public void clickYesButton() {
        driver.findElement(buttons).click();
    }


}
