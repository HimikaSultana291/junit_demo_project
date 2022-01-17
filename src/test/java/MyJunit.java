import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class MyJunit {
        WebDriver driver;
        @Before
    public void Setup(){
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions firefoxOptions=new FirefoxOptions();
        firefoxOptions.addArguments("--headed");
        driver=new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void getTitle(){
        driver.get("https://demoqa.com");
        System.out.println(driver.getTitle());
        WebElement imgElement=driver.findElement(By.className("banner-image"));
        boolean status = imgElement.isDisplayed();
        Assert.assertTrue(status);
    }
    @Test
    public void fillupForm(){
            driver.get("https://demoqa.com/text-box");
            driver.findElement(By.id("userName")).sendKeys("Himika Sultana ");
            driver.findElement(By.id("userEmail")).sendKeys("Himi@gmail.com");
            driver.findElement(By.id("submit")).click();
    }
    @Test
    public void doubleClickButton(){
            driver.get("https://demoqa.com/buttons");
            WebElement doubleClickBtnElement= driver.findElement(By.id("doubleClickBtn"));
            WebElement rightClickBtnElement=driver.findElement(By.id("rightClickBtn"));
            Actions actions= new Actions(driver);
            actions.doubleClick(doubleClickBtnElement).perform();
            actions.contextClick(rightClickBtnElement).perform();
            String text1 = driver.findElement(By.id("doubleClickMessage")).getText();
            String text2 = driver.findElement(By.id("rightClickMessage")).getText();
            Assert.assertTrue(text1.contains("You have done a double click"));
            Assert.assertTrue(text1.contains("You have done a right click"));
    }
    @Test
    public void clickMultipleButton(){
            driver.get("https://demoqa.com/buttons");
            List<WebElement> buttonElement= driver.findElements(By.tagName("button"));
            Actions actions = new Actions(driver);
            actions.doubleClick(buttonElement.get(1)).perform();
            actions.contextClick(buttonElement.get(2)).perform();
            actions.click(buttonElement.get(3)).perform();
    }
    @Test
    public void handleAlert(){
            driver.get("https://demoqa.com/alerts");
            driver.findElement(By.id("alertButton")).click();
            driver.switchTo().alert().accept();
            driver.findElement(By.id("confirmButton")).click();
            driver.switchTo().alert().dismiss();
    }
    @Test
    public void selectDate(){
            driver.get("https://demoqa.com/date-picker");
            driver.findElement(By.id("datePickerMonthYearInput")).clear();
            driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("05/03/2020");
            driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);
    }
    @Test
    public void setDropdown(){
            driver.get("https://demoqa.com/select-menu");
            Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
            select.selectByValue("2");
    }
    @After
    public void closeBrowser(){
         //   driver.close();
         //   driver.quit();
    }
}
