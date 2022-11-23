import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseUrlTest {

    // Выберите браузер
    // 1. Chrome браузер:
    protected static WebDriver driver = new ChromeDriver();
    // 2. Firefox браузер:
    //protected static WebDriver driver = new FirefoxDriver();

    // URL тестового приложения
    protected static String samokatTestInstance = "https://qa-scooter.praktikum-services.ru/";
}
