package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс первой формы ввода данных для создания заказа
public class OrderFormStep1 {

    private WebDriver driver;

    // Поле ввода имени
    private By nameInput = By.xpath(".//div[starts-with(@class,'Order_Form')]//input[@*='* Имя']");

    // Поле ввода фамилии
    private By surnameInput = By.xpath(".//div[starts-with(@class,'Order_Form')]//input[@*='* Фамилия']");

    // Поле ввода адреса
    private By addressInput = By.xpath(".//div[starts-with(@class,'Order_Form')]//input[@*='* Адрес: куда привезти заказ']");

    // Поле выбора станции метро
    private By metroSelectSearch = By.xpath(".//div[starts-with(@class,'Order_Form')]//input[@*='* Станция метро']");

    // Поле ввода номера телефона
    private By phoneNumberInput = By.xpath(".//div[starts-with(@class,'Order_Form')]//input[@*='* Телефон: на него позвонит курьер']");

    // Кнопка Далее на форме ввода данных для создания заказа
    private By orderFormNextButton = By.xpath(".//button[text()='Далее']");

    // Конструктор класса первой формы ввода данных для создания заказа
    public OrderFormStep1(WebDriver driver){
        this.driver = driver;
    }

    // Метод ждет загрузки формы ввода данных для создания заказа
    public void waitForLoadOrderForm(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[starts-with(@class,'Order_Form')]")));
    }
    // Метод ввода имени для создания заказа
    public void setName (String name){
        driver.findElement(nameInput).sendKeys(name);
    }
    // Метод ввода фамилии для создания заказа
    public void setSurname (String surname){
        driver.findElement(surnameInput).sendKeys(surname);
    }
    // Метод ввода адреса доставки для создания заказа
    public void setAddress (String address){
            driver.findElement(addressInput).sendKeys(address);
    }
    // Метод выбора станции метро для создания заказа
    public void selectMetroStation (String metro){
            driver.findElement(metroSelectSearch).sendKeys(metro);
            By locator = By.xpath(".//div[@class='select-search__select']");
            driver.findElement(locator).click();
    }
    // Метод ввода номера телефона для создания заказа
    public void setPhoneNumber (String phoneNumber){
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }
    // Метод нажимает кнопку Далее на форме ввода данных для создания заказа
    public void clickNextButton (){
        driver.findElement(orderFormNextButton).click();
    }
    // Метод заполняет первую форму создания заказа и нажимает кнопку Далее
    public void setOrderFormStep1 (String name, String surname, String address, String metro, String phoneNumber){
        waitForLoadOrderForm();
        setName(name);
        setSurname(surname);
        setAddress(address);
        selectMetroStation(metro);
        setPhoneNumber(phoneNumber);
        clickNextButton();
    }
}
