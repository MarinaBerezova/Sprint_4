import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePageSamokat;
import pageobject.OrderFormStep1;
import pageobject.OrderFormStep2;
import pageobject.OrderModalWindow;
import static org.junit.Assert.assertTrue;

public class CreateOrderUsingBottomButton extends BaseUrlTest {
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
    // через кнопку "Заказать" в нижней части домашней страницы,
    // с заполнением всех полей.
    @Test
    public void CreateOrderUsingBottomButton_WithAllFields() {
        // Переход на домашнюю страницу тестового приложения
        driver.get(samokatTestInstance);
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        // Нажать кнопку Принять куки
        objHomePage.clickAcceptCookieButton();
        // Скроллить до кнопки "Заказать" в нижней части страницы
        objHomePage.scrollToOrderBottomButton();
        // Нажать кнопку "Заказать" в нижней части страницы
        objHomePage.clickOrderBottomButton();

        // Ждать загрузки первой формы ввода данных
        OrderFormStep1 objOrderFormStep1 = new OrderFormStep1(driver);
        objOrderFormStep1.waitForLoadOrderForm();
        // Ввести данные во все поля первой формы:
        objOrderFormStep1.setName("Пётр");
        objOrderFormStep1.setSurname("Петров");
        objOrderFormStep1.setAddress("Москва, ул.Ленина 1-111");
        objOrderFormStep1.selectMetroStation("Площадь Ильича");
        objOrderFormStep1.setPhoneNumber("+79057654321");
        // Нажать кнопку Далее
        objOrderFormStep1.clickNextButton();


        // Ждать загрузки второй формы ввода данных
        OrderFormStep2 objOrderFormStep2 = new OrderFormStep2(driver);
        objOrderFormStep2.waitForLoadOrderForm();
        // Ввести данные во все поля второй формы:
        objOrderFormStep2.selectDeliveryDate(objOrderFormStep2.getDateAWeekLater());
        objOrderFormStep2.selectRentPeriod("трое суток");
        objOrderFormStep2.selectScooterColour("чёрный жемчуг");
        objOrderFormStep2.setCommentToCourier("Позвоните за 2 часа");
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
