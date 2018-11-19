package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private String CheckoutPage1URL = "http://automationpractice.com/index.php?controller=order";

    public String getDefaultCheckoutPage1URL(){
        return this.CheckoutPage1URL;
    }

    @FindBy(className = "cart_product")
    private WebElement cartProduct;

    public boolean cartProductDisplayed(){
        return cartProduct.isDisplayed();
    }


    @FindBy(className = "page-heading")
    private WebElement pageHeader;

    public String getPageHeader(){
        return this.pageHeader.getText();
    }

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "passwd")
    private WebElement passwordInputField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginButton;

    public void enterLoginAndPassword(String username, String password){
        this.emailInputField.sendKeys(username);
        this.passwordInputField.sendKeys(password);
        this.submitLoginButton.click();
    }

}
