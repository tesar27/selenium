import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {
    @Test
    public void DragDropTest(){
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/home/tesar/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver( options );
        driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
        Actions act=new Actions(driver);
        WebElement drag=driver.findElement(By.xpath(".//*[@id='draggable']"));
        WebElement drop=driver.findElement(By.xpath(".//*[@id='droppable']"));
        act.dragAndDrop(drag, drop).build().perform();
        driver.close();
    }
}