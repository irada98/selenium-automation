import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class SafariDriverTest {
    SafariDriver driver = new SafariDriver();

    @Test
    public void safariDriverTest() {
        driver.get("https://google.lv");
        WebElement acceptButton = driver.findElement(By.xpath("//div[text()='Accept all']//parent::button"));
        acceptButton.click();
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Riga");
        searchField.sendKeys(Keys.ENTER);

        driver.close();
        driver.quit();
    }
}
