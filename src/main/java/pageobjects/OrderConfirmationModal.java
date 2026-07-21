package pageobjects;
// Автотесты для спринта 4
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationModal {

    private WebDriver driver;
    private final By yesButton = By.xpath("//button[text()='Да']");

    public OrderConfirmationModal(WebDriver driver) {
        this.driver = driver;
    }

    public OrderSuccessModal clickYesButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
        return new OrderSuccessModal(driver);
    }
}