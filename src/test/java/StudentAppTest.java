import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.page_object.Notifications;
import lv.acodemy.utils.LocalDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;

@Slf4j
public class StudentAppTest {
    Faker fakeData = new Faker();
    MainPage mainPage = new MainPage();
    AddStudentPage addStudentPage = new AddStudentPage();
    WebDriverWait wait = new WebDriverWait(LocalDriverManager.getInstance(),
            ofSeconds(getConfiguration().getLong("wait.time")));
    Notifications notifications = new Notifications(wait);

    @Test
    public void createStudentTest() {
        LocalDriverManager.getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url"));
        LocalDriverManager.getInstance().get(getConfiguration().getString("app.url"));
        mainPage.addStudent();

        // Find NAME element
        addStudentPage.setNameField(fakeData.name().fullName());

        // Find EMAIL element
        addStudentPage.setEmailField(fakeData.internet().emailAddress());

        // Find GENDER element
        addStudentPage.setGender("female");

        // Find SUBMIT button
        addStudentPage.submitStudent();

        Assertions.assertThat(notifications.getNotificationSuccessMsg()).isEqualTo("Student successfully added");

        System.out.println();
    }

    @AfterMethod
    public void tearDown() {
        LocalDriverManager.closeDriver();
    }
}
