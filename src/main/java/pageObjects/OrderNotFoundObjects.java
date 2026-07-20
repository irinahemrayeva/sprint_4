package pageObjects;
// Автотесты для спринта 4
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderNotFoundObjects {

    private final By imgNotFound= By.cssSelector("img[alt='Not found']");

    private WebDriver driver;

    public OrderNotFoundObjects(WebDriver driver){
        this.driver = driver;
    }

    public boolean isNotFoundImageDisplayed(){
        new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(imgNotFound));
        return driver.findElement(imgNotFound).isDisplayed();
    }

}
