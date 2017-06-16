import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;

public class DownloadFiles {

    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/tesar/chromedriver");
        String downloadFilepath = "/home/tesar/Documents/ELTE";

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, 30);

        boolean result;
        try {

            driver.get("https://www.dropbox.com/");
            signIn();

            result = finalCheck();

        } catch(Exception e) {
            e.printStackTrace();
            result = false;
        } /*finally {
            driver.close();
        }*/

        System.out.println("Test " + (result? "passed." : "failed."));
        if (!result) {
            System.exit(1);
        }
    }

    private static void signIn()
    {

        // type login, password and sign in
        driver.findElement(By.linkText("Sign in")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("db-modal-box")));


        driver.findElement(By.cssSelector("input.text-input-input")).sendKeys("ytazhkeyev@gmail.com");
        driver.findElement(By.cssSelector("input.password-input")).sendKeys("Qwerty123");
        driver.findElement(By.cssSelector("button.login-button")).click();

        System.out.println("Signed in...");

    }

    private static void downloadFiles()
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("files"))
        );


        driver.findElement(By.xpath("//button[@aria-label='Select all']")).click();
        driver.findElement(By.cssSelector("button.primary-action-menu__button")).click();
        driver.findElement(By.cssSelector("button.checkbox-button")).click();


    }


    private static boolean finalCheck() {

        File dir = new File("/home/tesar/Documents/ELTE");
        File[] dirContentsBefore = dir.listFiles();

        downloadFiles();

        File[] dirContentsAfter = dir.listFiles();

        return dirContentsBefore.length < dirContentsAfter.length;
    }

}