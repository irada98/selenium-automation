import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;

@Slf4j
public class SauceDemoTest {
    ChromeDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver,
            ofSeconds(getConfiguration().getLong("wait.time")));

    @Test
    public void createSauceDemoTest1() {
        driver.manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url2"));
        driver.get(getConfiguration().getString("app.url2"));

        // Find user-name
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("standard_user");

        // Find password
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("secret_sauce");

        // Login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Finding PRODUCTS text element on the page
        WebElement productLabel = driver.findElement(By.className("product_label"));
        wait.until(ExpectedConditions.textToBePresentInElement(productLabel, "Products"));
        Assertions.assertThat(productLabel.getText()).isEqualTo("Products");

        // LOG OUT test function
        driver.findElement(By.className("bm-burger-button")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        System.out.println();

    }

    @Test
    public void createSauceDemoTest2() {
        driver.manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url2"));
        driver.get(getConfiguration().getString("app.url2"));

        // Find user-name
        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("name");

        // Find password
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("password");

        // Login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Error message
        WebElement errorMessage = driver.findElement(By.className("login-box"));
        wait.until(ExpectedConditions.
                textToBePresentInElement(errorMessage, "Epic sadface: Username and password do not match any user in this service"));
        Assertions.assertThat(errorMessage.
                getText()).isEqualTo("Epic sadface: Username and password do not match any user in this service");

        System.out.println();

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
