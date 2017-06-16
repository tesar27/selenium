import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class BasicElements {
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
    public void testRadioButton() throws Exception {
        driver.get(baseUrl + "/tazhkeyev/");
        driver.findElement(By.name("gender")).click();
    }

    @Test
    public void testDropDown() throws Exception {
        driver.get(baseUrl + "/tazhkeyev/");
        new Select(driver.findElement(By.id("options"))).selectByVisibleText("Feedback");
    }

    @Test
    public void backButtonTest(){
        driver.get(baseUrl + "/tazhkeyev/");
        driver.navigate().to("http://people.inf.elte.hu/tazhkeyev/partials/myCountry.html");
        driver.navigate().back();

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}