package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    WebDriver driver = LocalDriverManager.getInstance();

    private final By addStudentButton = By.id("addStudentButton");

    public void addStudent() {
        driver.findElement(addStudentButton).click();
    }

}
