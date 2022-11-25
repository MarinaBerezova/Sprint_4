import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.HomePageSamokat;
import pageobject.OrderFormStep1;
import pageobject.OrderFormStep2;
import pageobject.OrderModalWindow;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateOrderPositiveFlow extends BaseUrlTest {

    private WebDriver driver;
    private final int testId;
    private final String useOrderCreationButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentPeriod;
    private final String scooterColour;
    private final String commentToCourier;


    public CreateOrderPositiveFlow(int testId, String useOrderCreationButton, String name, String surname, String address, String metro, String phoneNumber, String deliveryDate, String rentPeriod, String scooterColour, String commentToCourier) {
        this.testId=testId;
        this.useOrderCreationButton=useOrderCreationButton;
        this.name=name;
        this.surname=surname;
        this.address=address;
        this.metro=metro;
        this.phoneNumber=phoneNumber;
        this.deliveryDate=deliveryDate;
        this.rentPeriod=rentPeriod;
        this.scooterColour=scooterColour;
        this.commentToCourier=commentToCourier;
    }
    @Parameterized.Parameters
    public static Object[][] getOrderCreationTestData() {
        return new Object[][]{
                {1, "Use upper Order button", "Иван", "Иванов", "", "Балтийская", "89051234567", "доставка завтра", "сутки", "", ""},
                {2, "Use bottom Order button", "Мария", "Сидорова", "", "Площадь Ильича", "89111234567", "доставка завтра", "двое суток", "", ""},
                {3, "Use upper Order button", "Пётр", "Петров", "Москва, ул.Ленина 1-111", "Петровско-Разумовская", "+79057654321", "доставка через неделю", "трое суток", "чёрный жемчуг", "Позвоните за 2 часа"},
                {4, "Use bottom Order button", "Ян", "Ли", "ФИНЭК", "ЗИЛ", "89051234567", "доставка завтра", "сутки", "", "-"},
                {5, "Use upper Order button", "Сергей Иванович", "ОченьДлиннаяФамилияяяяяяяяяяяяяяяяяяяяяяяяяяя", "Лермонтовский проспект, д.7,стр.7-А,этаж 2, кв.77", "Бульвар Дмитрия Донского", "+790512345678", "доставка через неделю", "шестеро суток", "серая безысходность", "Домофон: 77, оплата Mir Pay, очень длинный комментарийййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййййй!"},
        };
    }

    // Тест проверяет флоу позитивного сценария создания заказа,
    // через верхнюю и нижнюю кнопки "Заказать" на домашней странице
    @Before
    // Браузер можно поменять в классе BaseUrlTest.
    // Очистить куки
    public void clearCookies() {
        driver = BaseUrlTest.driver;
        driver.get(samokatTestInstance);
        driver.manage().deleteAllCookies();
    }

    @Test
    public void createNewOrderPositiveFlow() {
        // Переход на домашнюю страницу тестового приложения
        driver.get(samokatTestInstance);
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        // Нажать кнопку Принять куки
        objHomePage.clickAcceptCookieButton();
        // Нажать кнопку "Заказать"
        objHomePage.clickCreateOrderButton(useOrderCreationButton);

        // Заполнить поля первой формы:
        OrderFormStep1 objOrderFormStep1 = new OrderFormStep1(driver);
        objOrderFormStep1.setOrderFormStep1(name,surname,address,metro,phoneNumber);

        // Заполнить поля второй формы:
        OrderFormStep2 objOrderFormStep2 = new OrderFormStep2(driver);
        objOrderFormStep2.setOrderFormStep2(deliveryDate, rentPeriod, scooterColour, commentToCourier);

        // Подтвердить создание заказа во всплывающем окне
        OrderModalWindow objModalWindow = new OrderModalWindow(driver);
        objModalWindow.clickModalConfirmButton();

        // Проверить, что всплывающее окно содержит сообщение об успешном создании заказа и вывести номер заказа на экран
        assertTrue(objModalWindow.isOrderCreationSuccess());
        System.out.println(objModalWindow.getOrderSuccessText());
    }
    @After
    public void teardown() {
        // Закрыть браузер
        if(testId == 5){
            driver.quit();
        }
    }
}