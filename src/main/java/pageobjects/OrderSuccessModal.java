package pageobjects;
// Автотесты для спринта 4
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderSuccessModal {

    private WebDriver driver;
    private final By successHeader = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    public OrderSuccessModal(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderSuccessDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}