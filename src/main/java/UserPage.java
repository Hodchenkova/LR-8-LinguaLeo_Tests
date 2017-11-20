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
    @FindBy(css = "a.b-dict-link.b-header__dict" )
    public WebElement dictionary;
    @FindBy(xpath = "//*[@id=\"glossaryPage\"]/div[3]/div[2]/div[1]/div[2]/div/div/form/input")
    public WebElement searchField;
    @FindBy(xpath = "//*[@id=\"glossaryPage\"]/div[3]/div[2]/div[1]/div[2]/div/div/form/button")
    public WebElement addButton;
    @FindBy (css = "body > div:nth-child(33) > div > div.simple-dialog__popup > div > div.simple-dialog__header > span")
    public WebElement closeAllert;
    @FindBy (css = "div.transmenu__content > div:nth-of-type(2) > a.transword__text.t-ellps")
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
    public void CloseAllert(){
       closeAllert.click();
    }
    public void chooseDog(){
        chooseDog.click();
    }
}
