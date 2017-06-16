import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;


public class CookieManipulation {

    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/tesar/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, 30);
        driver.get("http://www.asos.com");


        boolean result;
        try {

            boolean added = addCheck();
            boolean deleted = deleteCheck();

            result = finalCheck(added,deleted);
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

    private static Set getCookies()
    {
        return driver.manage().getCookies();
    }

    private static void addCookie()
    {
        Cookie name = new Cookie("testCookie", "123456789123");
        driver.manage().addCookie(name);
    }

    private static boolean addCheck() {

        addCookie();
        return driver.manage().getCookieNamed("testCookie") != null;
    }

    private static boolean deleteCheck() {

        driver.manage().deleteCookieNamed("testCookie");
        return driver.manage().getCookieNamed("testCookie") == null;
    }

    private static boolean finalCheck(boolean added, boolean deleted) {

        return added && deleted;
    }
}