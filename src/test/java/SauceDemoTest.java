import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.SauceDemoObject;
import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;
import static lv.acodemy.utils.LocalDriverManager.getInstance;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SauceDemoTest {

    WebDriver driver = LocalDriverManager.getInstance();
    WebDriverWait wait = new WebDriverWait(driver,
            ofSeconds(getConfiguration().getLong("wait.time")));
    SauceDemoObject sauceDemoObject = new SauceDemoObject(wait);

    @Test(description = "Positive login test")
    public void testLogin() {
        getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url2"));
        getInstance().get(getConfiguration().getString("app.url2"));
        // Find user-name
        sauceDemoObject.setUsernameField("standard_user");
        // Find password
        sauceDemoObject.setPasswordField("secret_sauce");
        // Login button
        sauceDemoObject.loginButton();
        // Finding PRODUCTS text element on the page
        assertThat(sauceDemoObject.findProductsLabel()).isEqualTo("Products");
        // LOG OUT test function
        sauceDemoObject.burgerMenuButton();
        sauceDemoObject.logoutButton();

        System.out.println();

    }

    @Test
    public void testLoginNegative() {
        getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url2"));
        getInstance().get(getConfiguration().getString("app.url2"));
        // Find user-name
        sauceDemoObject.setUsernameField("name");
        // Find password
        sauceDemoObject.setPasswordField("password");
        // Login button
        sauceDemoObject.loginButton();
        // Error message
        assertThat(sauceDemoObject.findLoginBox())
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");

        System.out.println();

    }

    @AfterMethod
    public void tearDown() {
        LocalDriverManager.closeDriver();
    }

}
