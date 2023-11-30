import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.*;
import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;
import static lv.acodemy.utils.LocalDriverManager.getInstance;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StudentAppTest {
    Faker fakeData = new Faker();
    MainPage mainPage = new MainPage();
    AddStudentPage addStudentPage = new AddStudentPage();
    WebDriverWait wait = new WebDriverWait(getInstance(),
            ofSeconds(getConfiguration().getLong("wait.time")));
    Notifications notifications = new Notifications(wait);
    ErrorMessage errorMessage = new ErrorMessage(wait);
    DeleteEditPage deleteEditPage = new DeleteEditPage(wait);

    @Test
    public void createStudentTest() {
        getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url"));
        getInstance().get(getConfiguration().getString("app.url"));
        mainPage.addStudent();
        // Find NAME element
        addStudentPage.setNameField(fakeData.name().fullName());
        // Find EMAIL element
        addStudentPage.setEmailField(fakeData.internet().emailAddress());
        // Find GENDER element
        addStudentPage.setGender("female");
        // Find SUBMIT button
        addStudentPage.submitStudent();
        // Check the popup notification
        assertThat(notifications.getNotificationSuccessMsg()).isEqualTo("Student successfully added");

        System.out.println();
    }

    @Test(enabled = false)
    public void studentTestErrorChecks() {
        getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url"));
        getInstance().get(getConfiguration().getString("app.url"));
        mainPage.addStudent();
        addStudentPage.submitStudent();
        // Error NAME messages check
        assertThat(errorMessage.getErrorNameMsg()).isEqualTo("Please enter student name");
        // Error EMAIL message check
        assertThat(errorMessage.getErrorEmailMsg()).isEqualTo("Please enter student email");
        // Error GENDER message check
        assertThat(errorMessage.getErrorGenderMsg()).isEqualTo("Please select a gender");

        System.out.println();

    }

    @Test(enabled = false)
    public void deleteStudent() {
        getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url"));
        getInstance().get(getConfiguration().getString("app.url"));

//        // DELETE certain user
//        deleteEditPage.findFirstUserDelete();
//        assertThat(deleteEditPage.findYesButtonInPopupMsg()).isEqualTo("Are you sure to delete John Doe222\n" +
//                "  NoYes");
//        deleteEditPage.clickYesButton();
        // unsuccessful try

        System.out.println();
    }

    @Test(enabled = false)
    public void editStudent() {
        getInstance().manage()
                .timeouts()
                .implicitlyWait(ofSeconds(getConfiguration().getLong("wait.time")));
        logger.info("Will open now: " + getConfiguration().getString("app.url"));
        getInstance().get(getConfiguration().getString("app.url"));
        // EDIT the user
        deleteEditPage.findFirstUserEdit();
        addStudentPage.clearNameField();
        addStudentPage.setNameField(fakeData.name().fullName());
        addStudentPage.submitStudent();

        System.out.println();

    }


    @AfterMethod
    public void tearDown() {
        LocalDriverManager.closeDriver();
    }
}
