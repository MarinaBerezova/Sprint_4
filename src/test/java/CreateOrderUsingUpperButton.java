import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePageSamokat;
import pageobject.OrderFormStep1;
import pageobject.OrderFormStep2;
import pageobject.OrderModalWindow;
import static org.junit.Assert.assertTrue;

public class CreateOrderUsingUpperButton extends BaseUrlTest {

    private WebDriver driver;

    @Before
    // Браузер можно поменять в классе BaseUrlTest.
    // Очистить куки
    public void clearCookies() {
        driver = BaseUrlTest.driver;
        driver.get(samokatTestInstance);
        driver.manage().deleteAllCookies();
    }
    // Тест проверяет флоу позитивного сценария создания заказа,
    // через кнопку "Заказать" в верхней части (хидере) домашней страницы,
    // с заполнением только обязательных полей.
    @Test
    public void CreateOrderUsingUpperButton_WithMandatoryFields() {
        // Переход на домашнюю страницу тестового приложения
        driver.get(samokatTestInstance);
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        // Нажать кнопку Принять куки
        objHomePage.clickAcceptCookieButton();
        // Нажать кнопку "Заказать" в верхней части (хидере) страницы
        objHomePage.clickOrderHeaderButton();

        // Ждать загрузки первой формы ввода данных
        OrderFormStep1 objOrderFormStep1 = new OrderFormStep1(driver);
        objOrderFormStep1.waitForLoadOrderForm();
        // Ввести данные в обязательные поля первой формы:
        objOrderFormStep1.setName("Иван");
        objOrderFormStep1.setSurname("Иванов");
        objOrderFormStep1.selectMetroStation("Балтийская");
        objOrderFormStep1.setPhoneNumber("89051234567");
        // Нажать кнопку Далее
        objOrderFormStep1.clickNextButton();

        // Ждать загрузки второй формы ввода данных
        OrderFormStep2 objOrderFormStep2 = new OrderFormStep2(driver);
        objOrderFormStep2.waitForLoadOrderForm();
        // Ввести данные в обязательные поля второй формы:
        objOrderFormStep2.selectDeliveryDate(objOrderFormStep2.getTomorrowDate());
        objOrderFormStep2.selectRentPeriod("сутки");
        // Нажать кнопку Заказать
        objOrderFormStep2.clickSubmitButton();

        //Ждать загрузки всплывающего окна
        OrderModalWindow objModalWindow = new OrderModalWindow(driver);
        objModalWindow.waitForLoadModalWindow();
        // Подтвердить создание заказа
        objModalWindow.clickModalConfirmButton();

        // Проверить, что всплывающее окно содержит сообщение об успешном создании заказа
        assertTrue(objModalWindow.isOrderCreationSuccess());

        //Вывести на экран сообщение с номером заказа
        System.out.println(objModalWindow.getOrderSuccessText());
    }
    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}