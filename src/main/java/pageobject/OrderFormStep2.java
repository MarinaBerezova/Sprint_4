package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;

// Класс второй формы ввода данных для создания заказа
public class OrderFormStep2 {

    private WebDriver driver;

    // Поле выбора даты доставки
    private By deliveryDate = By.xpath(".//div[starts-with(@class,'Order_Form')]//input[@*='* Когда привезти самокат']");

    // Поле выбора срока аренды
    private By rentPeriodSelection = By.xpath(".//div[starts-with(@class,'Order_Form')]//div[text()='* Срок аренды']");

    // Чекбокс для выбора цвета самоката "чёрный жемчуг"
    private By scooterBlackColourCheckbox = By.id("black");
    // Чекбокс для выбора цвета самоката "серая безысходность"
    private By scooterGreyColourCheckbox = By.id("grey");

    // Поле ввода комментария для курьера
    private By commentToCourierInput = By.xpath(".//div[starts-with(@class,'Order_Form')]//input[@*='Комментарий для курьера']");

    // Кнопка Заказать на форме ввода данных для создания заказа
    private By orderFormSubmitButton = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[text()='Заказать']");

    // Конструктор класса второй формы ввода данных для создания заказа
    public OrderFormStep2(WebDriver driver){
        this.driver = driver;
    }

    // Метод ждет загрузки формы ввода данных для создания заказа
    public void waitForLoadOrderForm(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[starts-with(@class,'Order_Form')]")));
    }
    // Метод выбора даты доставки
    public void selectDeliveryDate (String date){
        driver.findElement(deliveryDate).sendKeys(date);
        By locator = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");
        driver.findElement(locator).click();
    }
    // Метод выбора срока аренды
    public void selectRentPeriod (String rentPeriod){
        driver.findElement(rentPeriodSelection).click();
        String locator = String.format (".//div[@class='Dropdown-option' and text()='%s']", rentPeriod);
        driver.findElement(By.xpath(locator)).click();
    }
    // Метод выбора цвета самоката
    public void selectScooterColour (String colour){
        if (colour == "чёрный жемчуг") {
            driver.findElement(scooterBlackColourCheckbox).click();
        } else if (colour == "серая безысходность"){
            driver.findElement(scooterGreyColourCheckbox).click();
        }
    }
    // Метод ввода комментария для курьера
    public void setCommentToCourier (String commentToCourier){
        driver.findElement(commentToCourierInput).sendKeys(commentToCourier);
    }
    // Метод нажимает кнопку Заказать на форме ввода данных для создания заказа
    public void clickSubmitButton (){
        driver.findElement(orderFormSubmitButton).click();
    }
    // Метод определяет завтрашнюю дату
    public String getTomorrowDate (){
        LocalDate date = LocalDate.now();
        date = date.plusDays(1);
        System.out.println("Заказываю самокат на завтра:" + date);
        return String.valueOf(date);
    }
    // Метод определяет дату через неделю от сегодняшней
    public String getDateAWeekLater (){
        LocalDate date = LocalDate.now();
        date = date.plusWeeks(1);
        System.out.println("Заказываю самокат на следующую неделю:" + date);
        return String.valueOf(date);
    }
}