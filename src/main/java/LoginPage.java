import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    public LoginPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;
   @FindBy (xpath = "//*[@id='headEnterBtn']")
    public WebElement loginButton;
    @FindBy(xpath = "//form[@id='loginForm']//child::*[@name='email']")
    public WebElement emailField;
    @FindBy(id = "registerFormEmail")
    public WebElement emailField1;
    @FindBy(id = "registerFormPassword")
    public WebElement passwordField1;
    @FindBy(xpath = "//form[@id='loginForm']//child::*[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath= "//*[@id='loginForm']/child::*[contains(text(),'Войти')]")
    public WebElement submitButton;
    @FindBy(id = "registerFormBtn")
    public WebElement submitButton1;
    @FindBy(xpath = "//a[text()='error-message'")
    public WebElement errorMessage;

    public void enterButton(){
        loginButton.click();
    }

    public void inputEmail (String email) {
        emailField.sendKeys(email);
    }
    public void inputPassword (String password){
        passwordField.sendKeys(password);
    }
    public void clickSubmitButton(){
        submitButton.click();
    }
    public void inputEmail1 (String email) {
        emailField1.sendKeys(email);
    }
    public void inputPassword1 (String password){
        passwordField1.sendKeys(password);
    }
    public void clickSubmitButton1(){
        submitButton1.click();
    }




}
