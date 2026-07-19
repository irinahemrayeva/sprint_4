import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SuccessOrderTest1 {

    private WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String comment;

    public SuccessOrderTest1(String name, String surname, String address,
                             String metroStation, String phone, String date, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Петров", "ул. Ленина 1", "Красносельская", "89001112233", "08.05.2026", ""},
                {"Мария", "Иванова", "ул. Пушкина 5", "Черкизовская", "89004445566", "10.05.2026", "Домофон не работает"}
        });
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testOrderWithTopButton() {
        MainPageObjects mainPage = new MainPageObjects(driver);
        mainPage.openYandexSamokatPage();
        mainPage.closeCookieBanner();
        mainPage.clickTopOrderButton();

        OrderPageObjects orderPage = new OrderPageObjects(driver);
        orderPage.fillOrderForm(name, surname, address, metroStation, phone);

        OrderSecondPageObjects orderSecondPage = orderPage.clickNextButton();
        orderSecondPage.fillSecondOrderForm(date, comment);

        OrderSuccessModal successModal = orderSecondPage.placeOrder();

        assertTrue("Заказ не оформлен", successModal.isOrderSuccessDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
        }
}