package pageobjects;

import cucumber.api.java.cs.Ale;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage {
    public static WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String homePageURL = "http://automationpractice.com/index.php";

    public void openHomePage(){
        driver.get( homePageURL );
    }

    @FindBy(className = "login")
    private WebElement signInLink;

    public void clickSignInLink(){
        this.signInLink.click();
    }

    @FindBy(className = "search_query")
    public WebElement searchInputField;

    @FindBy(css = ".sf-with-ul[title=\"Women\"]")
    private WebElement categoryWomenMenuItem;

    @FindBy(css = ".sf-with-ul[title=\"Dresses\"]")
    private WebElement categoryDressesMenuItem;

    public void openCategoryMenuLink(String categoryTitle){
        switch (categoryTitle) {
            case "Women":
                categoryWomenMenuItem.click();
            case "Dresses":
                categoryDressesMenuItem.click();
        }
    }

    @FindBy(className = "ajax_add_to_cart_button")
    private WebElement addToCartButton;

    public void clickAddToCartButton(){
        this.addToCartButton.click();
    }

    @FindBy(id = "Product")
    private WebElement popUp;

    @FindBy(name = "Submit")
    private WebElement popUpAddToCartButton;

    public void clickPopUpAddToCartButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        this.popUpAddToCartButton.click();
    }

    @FindBy(className = "icon-eye-open")
    private WebElement eyeButton;

    public void clickEyeButton(){
        this.eyeButton.click();
    }

    @FindBy(id = "layer_cart")
    private WebElement cartConfirmationPopUp;

    public boolean getIfCartConfirmationPopUpVisible(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        this.cartConfirmationPopUp = wait.until(ExpectedConditions.visibilityOf(this.cartConfirmationPopUp));
        return this.cartConfirmationPopUp.isDisplayed();
    }

    @FindBy(css = ".layer_cart_product h2")
    private WebElement cartConfirmationMessage;

    public String getCartConfirmationMessage(){
        return this.cartConfirmationMessage.getText();
    }

}