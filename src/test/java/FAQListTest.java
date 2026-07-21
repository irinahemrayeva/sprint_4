import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPageObjects;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// Автотесты для спринта 4
@RunWith(Parameterized.class)
public class FAQListTest {

    private WebDriver driver;

    private int sectionIndex;
    private String expectedText;

    public FAQListTest(int sectionIndex, String expectedText) {
        this.sectionIndex = sectionIndex;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."}, // Замени на реальный текст
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."}, // Замени на реальный текст
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void checkAccordionWork(){
       MainPageObjects mainPageObj = new MainPageObjects(driver);

        mainPageObj.openYandexSamokatPage();
        mainPageObj.scrollTillAccordion();
        assertTrue("Панель уже видна до клика", !mainPageObj.isAccordionPanelVisible(sectionIndex));
        mainPageObj.clickAccordionButton(sectionIndex);
        mainPageObj.waitForAccordionPanelOpen(sectionIndex);
        assertTrue("Панель не открылась после клика", mainPageObj.isAccordionPanelVisible(sectionIndex));
        String actualText = mainPageObj.getAccordionPanelText(sectionIndex);
        assertEquals("Текст не совпадает для секции " + sectionIndex, expectedText, actualText);
    }


    @After
    public void tearDown(){
        driver.quit();
    }


}
