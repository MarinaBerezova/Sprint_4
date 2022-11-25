import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.HomePageSamokat;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckHomeFAQ extends BaseUrlTest {

    private WebDriver driver;

    private final String accordionHeadingId;
    private final String expectedFaqQuestionText;
    private final String expectedFaqAnswerText;

    public CheckHomeFAQ(String accordionHeadingId, String expectedFaqQuestionText, String expectedFaqAnswerText) {
        this.accordionHeadingId = accordionHeadingId;
        this.expectedFaqQuestionText = expectedFaqQuestionText;
        this.expectedFaqAnswerText = expectedFaqAnswerText;
    }

    @Parameterized.Parameters
    public static Object[][] getAccordionText() {
        return new Object[][]{
                {"accordion__heading-0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    // Браузер можно поменять в классе BaseUrlTest.
    // Очистить куки
    public void clearCookies() {
            driver = BaseUrlTest.driver;
            driver.get(samokatTestInstance);
            driver.manage().deleteAllCookies();
    }

    @Test
    public void checkHomeFAQ() {
        // Переход на домашнюю страницу тестового приложения
        driver.get(samokatTestInstance);
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        // Нажать кнопку Принять куки
        objHomePage.clickAcceptCookieButton();
        // Скроллить до раздела "Вопросы о важном"
        objHomePage.scrollToFAQ();
        // Нажать на стрелочку элемента
        objHomePage.expandFaqItem(accordionHeadingId);
        // Проверить текст вопроса
        assertEquals("Ошибка в тексте вопроса", expectedFaqQuestionText, objHomePage.getFaqQuestion(accordionHeadingId));
        // Проверить текст ответа
        assertEquals("Ошибка в тексте ответа", expectedFaqAnswerText, objHomePage.getFaqAnswer(accordionHeadingId));
    }
    @After
    public void teardown() {
        // Закрыть браузер
        if(accordionHeadingId == "accordion__heading-7") {
            driver.quit();
        }
    }
}