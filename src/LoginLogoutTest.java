import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class LoginLogoutTest {
    private WebDriver driver;
    static Wait<WebDriver> wait;
    private String baseUrl, username, password;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    WebElement element;
    Properties prop = new Properties();
    InputStream input = null;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/tesar/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://www.asos.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            username = prop.getProperty("username");
            password = prop.getProperty("password");


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void validLogin() throws Exception {
        driver.get(baseUrl + "/?ic=1");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("EmailAddress")).clear();
        driver.findElement(By.id("EmailAddress")).sendKeys(username);
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("signin")).click();
        // element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.selected > span")));
        driver.get("http://www.asos.com/men/");
        driver.findElement(By.cssSelector("a.selected > span")).click();
        //driver.findElement(By.xpath("(//a[contains(text(),'Nike')])[2]")).click();
       // driver.findElement(By.xpath("//section[@id='productlist-results']/aside/div/div/div/ul/li[13]/a/span")).click();
        //driver.findElement(By.xpath("//section[@id='productlist-results']/aside/div[5]/div/div/ul/li[2]/a/span")).click();
        //driver.findElement(By.xpath("//section[@id='productlist-results']/aside/div/div/div/ul/li[18]/a/span")).click();
        //driver.findElement(By.linkText("regular images")).click();
        //new Select(driver.findElement(By.id("sortBy"))).selectByVisibleText("What's new");
        //driver.findElement(By.cssSelector("img.product-img")).click();
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.id("_ctl0_ContentBody_btnEditDetails")).click();
        new Select(driver.findElement(By.id("BirthDay"))).selectByVisibleText("7");
        driver.findElement(By.id("submit-edit")).click();
        driver.findElement(By.id("_ctl0_ContentBody_ctlSecureNav_lnkSignOut")).click();
    }
    @Test
    public void invalidLogin(){
        driver.get(baseUrl + "/?ic=1");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("EmailAddress")).clear();
        driver.findElement(By.id("EmailAddress")).sendKeys("tazhkeyev@gmail.com");
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("Qwerty");
        driver.findElement(By.id("signin")).click();

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