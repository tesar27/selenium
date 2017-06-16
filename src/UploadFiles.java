import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class UploadFiles {
    private WebDriver driver;
    static Wait<WebDriver> wait;
    private String baseUrl1, baseUrl2, baseUrl3, uploadUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();


    Properties prop = new Properties();
    InputStream input = null;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/tesar/chromedriver");
        driver = new ChromeDriver();
        uploadUrl = "https://www.file-upload.com/?op=upload";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            baseUrl1 = prop.getProperty("site1");
            baseUrl2 = prop.getProperty("site2");
            baseUrl3 = prop.getProperty("site3");

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
    //Go to static pages, which are taken from a file
    @Test
    public void loadPages() throws Exception {
        driver.get(baseUrl1 + "/?ic=1");
        driver.get(baseUrl2);
        driver.get(baseUrl3);

    }

    @Test
    public void testFileUpload() throws Exception {
        driver.get(uploadUrl);
        driver.findElement(By.id("file_0")).clear();
        driver.findElement(By.id("file_0")).sendKeys("/home/tesar/Documents/ELTE/2 semester/Haskell/Questions.txt");
        driver.findElement(By.name("upload")).click();
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