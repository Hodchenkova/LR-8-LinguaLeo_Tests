import org.apache.xpath.operations.Equals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LoginTest {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static UserPage userPage;
    @BeforeClass
    public static void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://lingualeo.com/ru#welcome");

    }

    public static void sleep(int time) {
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

        @Test (priority = 1)
        public void successfullLoginToLinguaLeo(){
            loginPage.inputEmail1("hodchenkova.a@gmail.com");
            loginPage.inputPassword1("123456");
            loginPage.clickSubmitButton1();
            String title = driver.getTitle();
            System.out.println(title);
            String expectedTitle = "Мои задания";
            assertEquals(title,expectedTitle);
        }
    @Test (priority = 2)
    public void loginToLinguaLeoWithWrongPassword(){
        loginPage.enterButton();
        loginPage.inputEmail("hodchenkova.a@gmail.com");
        loginPage.inputPassword("1234777");
        loginPage.clickSubmitButton();
        Assert.assertEquals(driver.findElement(By.cssSelector("p.uauth-email__error.t-ellps")).getText(),"Пароль/email введены неверно");
    }

    @Test (priority = 3, dependsOnMethods = {"successfullLoginToLinguaLeo"})
    public void navigateToMyProgressMenu(){
        userPage.myProgressMenu();
        Assert.assertEquals(driver.findElement(By.cssSelector("[class='journal-bl__head-item']")).getText(),"Сытость Лео");

    }
    @Test (priority = 4, dependsOnMethods = {"successfullLoginToLinguaLeo"})
    public void addNewWordToDictionary(){
        userPage.dictionary();
        userPage.SearchDog("dog");
        userPage.addWord();
        userPage.chooseDog();
        userPage.searchField.clear();
        sleep(1);
        userPage.SearchDog("dog");
        sleep(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-show-word-card-popup]")).getText(),"dog  —  собака, собака");

    }

    @AfterClass
    public void driverClose(){
        driver.quit();
    }
}

