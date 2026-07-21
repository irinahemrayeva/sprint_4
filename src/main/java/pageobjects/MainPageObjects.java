package pageobjects;
// Автотесты для спринта 4
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObjects {

    private WebDriver driver;

    public MainPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы
    private final By orderStatusButton = By.className("Header_Link__1TAG7");
    private final By orderField = By.className("Input_Input__1iN_Z");
    private final By goButton = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");

    private final By faqList = By.className("Home_FAQ__3uVm4");
    private final By accordionButtons = By.cssSelector("[data-accordion-component='AccordionItemButton']");
    private final By accordionPanels = By.cssSelector("[data-accordion-component='AccordionItemPanel']");

    private final By topOrderButton = By.xpath("(//button[text()='Заказать'])[1]");
    private final By bottomOrderButton = By.xpath("(//button[text()='Заказать'])[2]");

    // Куки
    private final By cookieButton = By.xpath("//button[text()='да все привыкли']");

    // Методы для звкрытия куки
    public void closeCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            WebElement cookie = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            cookie.click();
        } catch (Exception e) {
            System.out.println("Куки-банер не найден");
        }
    }

    // Методы для статуса заказа
    public void openYandexSamokatPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    public void EnterOrderField(String orderNumber) {
        driver.findElement(orderField).sendKeys(orderNumber);
    }

    public OrderNotFoundObjects clickGoButton() {
        driver.findElement(goButton).click();
        return new OrderNotFoundObjects(driver);
    }

    // Методы для FAQ
    public void scrollTillAccordion() {
        WebElement accordion = driver.findElement(faqList);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordion);
    }

    public void clickAccordionButton(int index) {
        List<WebElement> buttons = driver.findElements(accordionButtons);
        buttons.get(index).click();
    }

    public String getAccordionPanelText(int index) {
        List<WebElement> panels = driver.findElements(accordionPanels);
        return panels.get(index).getText();
    }

    public boolean isAccordionPanelVisible(int index) {
        try {
            List<WebElement> panels = driver.findElements(accordionPanels);
            return panels.get(index).isDisplayed();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void waitForAccordionPanelOpen(int index) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> panels = driver.findElements(accordionPanels);
        wait.until(ExpectedConditions.visibilityOf(panels.get(index)));
    }

    // Методы для кнопок заказать
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void scrollToBottomOrderButton() {
        WebElement button = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
    }

    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }
}