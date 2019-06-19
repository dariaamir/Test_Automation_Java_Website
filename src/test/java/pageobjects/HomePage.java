package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private String homePageURL = "http://automationpractice.com/index.php";

    @FindBy(className = "search_query")
    public WebElement searchInputField;

    @FindBy(className = "login")
    private WebElement signInLink;

    @FindBy(xpath = "//ul[contains(@class, \"menu-content\")]/li/a[text() = \"Women\"]")
    private WebElement categoryWomenMenuItem;

    @FindBy(xpath = "//ul[contains(@class, \"menu-content\")]/li/a[text() = \"Dresses\"]")
    private WebElement categoryDressesMenuItem;

    @FindBy(className = "ajax_add_to_cart_button")
    private WebElement addToCartButton;

    @FindBy(id = "Product")
    private WebElement popUp;

    @FindBy(className = "icon-eye-open")
    private WebElement eyeButton;

    @FindBy(id = "layer_cart")
    private WebElement cartConfirmationPopUp;

    @FindBy(css = ".layer_cart_product h2")
    private WebElement cartConfirmationMessage;

    public void openHomePage(){
        driver.get( homePageURL );
    }

    public void clickSignInLink(){
        this.signInLink.click();
    }

    public void openCategoryMenuLink(String categoryTitle){
        WebElement categoryItem = null;
        switch (categoryTitle) {
            case "Women":
                categoryItem = categoryWomenMenuItem;
                break;
            case "Dresses":
                categoryItem = categoryDressesMenuItem;
                break;
        }
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(categoryItem));
        categoryItem.click();
    }

    public void clickAddToCartButton(){
        this.addToCartButton.click();
    }

    public void clickEyeButton(){
        this.eyeButton.click();
    }

    public boolean getIfCartConfirmationPopUpVisible(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        this.cartConfirmationPopUp = wait.until(ExpectedConditions.visibilityOf(this.cartConfirmationPopUp));
        return this.cartConfirmationPopUp.isDisplayed();
    }

    public String getCartConfirmationMessage(){
        return this.cartConfirmationMessage.getText();
    }

}