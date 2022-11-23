package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Класс домашней страницы Самокат
public class HomePageSamokat {
    private WebDriver driver;

    // Кнопка Принять куки
    private By acceptCookieButton = By.xpath(".//div[starts-with(@class,'App_CookieConsent')]//button[starts-with(@class,'App_CookieButton')]");

    // Кнопка "Заказать" в верхней части (хидере) страницы
    private By OrderHeaderButton = By.xpath(".//div[starts-with(@class,'Header')]/button[text()='Заказать']");

    //Кнопка "Заказать" в нижней части страницы
    private By OrderBottomButton = By.xpath(".//div[contains(@class,'Finish')]/button[text()='Заказать']");

    // Раздел "Вопросы о важном"
    private By Faq = By.xpath(".//div[contains(@class,'FAQ')]");

    // Конструктор класса домашней страницы Самокат
    public HomePageSamokat(WebDriver driver){
        this.driver = driver;
    }

    // Метод нажимает кнопку Принять куки
    public void clickAcceptCookieButton() {
        driver.findElement(acceptCookieButton).click();
    }

    // Метод нажимает кнопку "Заказать" в верхней части (хидере) страницы
    public void clickOrderHeaderButton() {
        driver.findElement(OrderHeaderButton).click();
    }

    // Метод нажимает кнопку "Заказать" в нижней части страницы
    public void clickOrderBottomButton() {
        driver.findElement(OrderBottomButton).click();
    }

    // Метод скроллит домашнюю страницу до раздела "Вопросы о важном"
    public void scrollToFAQ() {
        WebElement element = driver.findElement(Faq);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // Метод скроллит домашнюю страницу до нижней кнопки "Заказать
    public void scrollToOrderBottomButton() {
        WebElement element = driver.findElement(OrderBottomButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // Метод нажимает стрелочку для выбранного элемента в разделе "Вопросы о важном"
    public void expandFaqItem(String accordionHeadingId) {
        String locator = String.format (".//div[@id='%s']", accordionHeadingId);
        driver.findElement(By.xpath(locator)).click();
    }
    // Метод возвращает текст вопроса для выбранного элемента в разделе "Вопросы о важном"
    public String getFaqQuestion(String accordionHeadingId) {
        String locator = String.format (".//div[@id='%s']", accordionHeadingId);
        return driver.findElement(By.xpath(locator)).getText();
    }
    // Метод возвращает текст ответа для выбранного элемента в разделе "Вопросы о важном"
    public String getFaqAnswer(String accordionHeadingId) {
        String locator = String.format (".//div[@class='accordion__panel' and @aria-labelledby='%s']/p", accordionHeadingId);
        return driver.findElement(By.xpath(locator)).getText();
    }
}
