import lv.acodemy.page_object.sauce_pages.InventoryPage;
import lv.acodemy.page_object.sauce_pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;
import static lv.acodemy.utils.LocalDriverManager.closeDriver;
import static lv.acodemy.utils.LocalDriverManager.getInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class SauceDemoTestTwo {

    WebDriver driver = getInstance();
    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test(description = "Test successful login")
    public void testLogin() {
        driver.get(getConfiguration().getString("sauce.demo.url"));
        loginPage.authorize(getConfiguration().getString("sauce.username"), getConfiguration().getString("sauce.password"));
        wait.until(visibilityOfAllElements(inventoryPage.getInventoryItems()));
        assertThat(inventoryPage.getInventoryItems()).hasSize(6);


    }

    @AfterMethod
    public void tearDown() {
        closeDriver();
    }
}
