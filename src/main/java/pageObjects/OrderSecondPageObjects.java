package pageObjects;
// Автотесты для спринта 4
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderSecondPageObjects {

    private WebDriver driver;

    public OrderSecondPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-arrow");
    private final By rentalPeriodOption = By.xpath("//div[text()='сутки']");
    private final By colorCheckbox = By.id("black");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("(//button[text()='Заказать'])[2]");

    public void fillSecondOrderForm(String date, String comment) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement dateElement = driver.findElement(dateField);
        dateElement.sendKeys(date);
        dateElement.click();
        driver.findElement(rentalPeriodField).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption)).click();
        WebElement color = driver.findElement(colorCheckbox);
        if (!color.isSelected()) {
            color.click();
        }
        driver.findElement(commentField).sendKeys(comment);
    }

    public OrderConfirmationModal clickOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
        return new OrderConfirmationModal(driver);
    }

    public OrderSuccessModal placeOrder() {
        OrderConfirmationModal confirmationModal = clickOrderButton();
        return confirmationModal.clickYesButton();
    }
}