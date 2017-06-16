import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class Xpath {
    private WebDriver driver;
    static Wait<WebDriver> wait;
    private String baseUrl, baseUrl1;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    WebElement element;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/tesar/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://www.asos.com/";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void testNike() throws Exception {
        driver.get(baseUrl + "/?ic=1");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("EmailAddress")).clear();
        driver.findElement(By.id("EmailAddress")).sendKeys("ytazhkeyev@gmail.com");
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("Qwerty123");
        driver.findElement(By.id("signin")).click();
        driver.get(baseUrl + "/men/");
        driver.findElement(By.cssSelector("a.selected > span")).click();
        driver.findElement(By.cssSelector("div.site-body")).click();
        driver.findElement(By.linkText("Shoes, Boots & Trainers")).click();
        driver.findElement(By.xpath("//section[@id='productlist-results']/aside/div[4]/div/div/ul/li[55]/a/span")).click();

    }


}
