package pageObjects;
// Автотесты для спринта 4
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageObjects {

    private WebDriver driver;

    public OrderPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы для 1-ой страницы
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Методы для выбора метро
    public void selectMetroStation(String stationName) {
        WebElement metroInput = driver.findElement(metroStationField);
        metroInput.click();
        metroInput.sendKeys(stationName);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement station = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(), '" + stationName + "')]")
        ));
        station.click();
    }

    // Заполнение формы
    public void fillOrderForm(String name, String surname, String address,
                              String metroStation, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        selectMetroStation(metroStation);
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    // Клик по кнопке Далее
    public OrderSecondPageObjects clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        button.click();

        // Возвращаем объект второй страницы
        return new OrderSecondPageObjects(driver);
    }
}