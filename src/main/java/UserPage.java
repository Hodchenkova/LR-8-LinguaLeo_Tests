import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {
    public UserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;


    @FindBy(className = "iconmenu-journal")
    public WebElement myProgressMenu;
    @FindBy(css = "[class='b-dict-link b-header__dict']" )
    public WebElement dictionary;
    @FindBy(xpath = "//*[@placeholder='Найти']")
    public WebElement searchField;
    @FindBy(css = "button[type='submit'][class='btn find-word']")
    public WebElement addButton;
    @FindBy (css = "a.transword__text.t-ellps")
    public WebElement chooseDog;

    public void myProgressMenu(){
        myProgressMenu.click();
    }

    public void dictionary(){
        dictionary.click();
    }

    public void SearchDog(String dog){
        searchField.sendKeys(dog);
    }
    public void addWord(){
    addButton.click();
    }
    public void chooseDog(){
        chooseDog.click();
    }
}
