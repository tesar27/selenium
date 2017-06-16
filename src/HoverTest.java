import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class HoverTest {
    private WebDriver driver;
    static Wait<WebDriver> wait;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    WebElement element;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/tesar/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://people.inf.elte.hu/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void hoverTest() {
        Actions action = new Actions(driver);
        driver.get(baseUrl + "/tazhkeyev/");
        WebElement we = driver.findElement(By.linkText("ABOUT"));
        action.moveToElement(we).moveToElement(driver.findElement(By.linkText("PORTFOLIO")));

    }
}