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
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://lingualeo.com/ru#welcome");

    }

    public static boolean checkIfElementExists(String selector) {
        try {
            driver.findElement(By.xpath(selector));
            return true;
        } catch (Exception e) {
            return false;
        }
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
        Assert.assertEquals(driver.findElement(By.cssSelector("body > div.simple-dialog.simple-dialog_is_shaking > div.simple-dialog__popup > div > div.simple-dialog__content > div > div.uauth-f__slider > div > div.uauth-f__login.uauth-form__email-auth_state_expanded.uauth-form__email-auth_state_error > div > div.uauth-email > div > p")).getText(),"Пароль/email введены неверно");
    }

    @Test (priority = 3, dependsOnMethods = {"successfullLoginToLinguaLeo"})
    public void navigateToMyProgressMenu(){
        if (checkIfElementExists("body > div:nth-child(33) > div > div.simple-dialog__popup > div > div.simple-dialog__header > span")){
            userPage.CloseAllert();
        }else userPage.myProgressMenu();
        Assert.assertEquals(driver.findElement(By.cssSelector("#content > div.journal-wp > div > div:nth-child(3) > div > div.journal-bl__head > div")).getText(),"Сытость Лео");

    }
    @Test (priority = 4, dependsOnMethods = {"successfullLoginToLinguaLeo"})
    public void addNewWordToDictionary(){
        if (checkIfElementExists("body > div:nth-child(33) > div > div.simple-dialog__popup > div > div.simple-dialog__header > span")){
            userPage.CloseAllert();
        }
        else userPage.dictionary();
        userPage.SearchDog("dog");  // c этого момента не работает, не находит кнопку "Добавить"
        userPage.addWord();
        userPage.chooseDog();
        userPage.searchField.clear();
        userPage.SearchDog("dog");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.item-word-translate > b")).getText(),"dog");
        Assert.assertEquals(driver.findElement(By.cssSelector("span.translates.t-ellps")).getText(),"собака");

    }

    @AfterClass
    public void driverClose(){
        driver.quit();
    }
}

