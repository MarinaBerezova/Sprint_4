package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс сплывающего окна при создании заказа
public class OrderModalWindow {
    private WebDriver driver;

    // Кнопка Подтвердить заказ
    private By modalConfirmButton = By.xpath(".//div[starts-with(@class,'Order_Buttons')]//button[text()='Да']");

    // Конструктор класса сплывающего окна при создании заказа
    public OrderModalWindow(WebDriver driver){
        this.driver = driver;
    }

    // Метод ждет загрузки всплывающего окна при создании заказа
    public void waitForLoadModalWindow(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[starts-with(@class,'Order_Modal')]")));
    }
    // Метод нажимает кнопку подтверждения заказа
    public void clickModalConfirmButton (){
        waitForLoadModalWindow();
        driver.findElement(modalConfirmButton).click();
    }
    // Метод проверяет текст всплывающего окна при создании заказа
    public boolean isOrderCreationSuccess (){
        boolean result = driver.findElement(By.xpath(".//div[contains(text(),'Заказ оформлен')]")).isDisplayed();
        return result;
    }
    // Метод возвращает текст всплывающего окна при создании заказа
    public String getOrderSuccessText (){
        String message = driver.findElement(By.xpath(".//div[contains(@class,'Order_Text')]")).getText();
        return message;
    }
}
