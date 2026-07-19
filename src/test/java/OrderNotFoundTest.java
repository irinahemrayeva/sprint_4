import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.MainPageObjects;
import pageObjects.OrderNotFoundObjects;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class OrderNotFoundTest {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void orderNotFoundDisplayedTest() {
        MainPageObjects mainPageObj = new MainPageObjects(driver);
        mainPageObj.openYandexSamokatPage();
        mainPageObj.clickOrderStatusButton();
        mainPageObj.EnterOrderField("123456");

        OrderNotFoundObjects orderNotFound = mainPageObj.clickGoButton();
        assertTrue(orderNotFound.isNotFoundImageDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}