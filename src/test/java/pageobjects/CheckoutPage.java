package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutPage {

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private String checkoutPageURL = "http://automationpractice.com/index.php?controller=order";

    @FindBy(className = "cart_product")
    private WebElement cartProduct;

    @FindBy(className = "page-heading")
    private WebElement pageHeader;

    @FindBy(id = "cgv")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "passwd")
    private WebElement passwordInputField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginButton;

    @FindBy(css = ".payment_module .bankwire")
    private WebElement paymentByWire;

    @FindBy(css = ".cheque-indent .dark")
    private WebElement confirmationMessage;


    public String getDefaultCheckoutPageURL(){
        return checkoutPageURL;
    }

    public boolean cartProductDisplayed(){
        return cartProduct.isDisplayed();
    }

    public String getPageHeader(){
        return this.pageHeader.getText();
    }

    public void agreeTheTermOfService(){
        termsAndConditionsCheckbox.click();
    }

    public void enterLoginAndPassword(String username, String password){
        this.emailInputField.sendKeys(username);
        this.passwordInputField.sendKeys(password);
        this.submitLoginButton.click();
    }

    public void clickPaymentByWire(){
        this.paymentByWire.click();
    }

    public String getConfirmationMessage(){
        return this.confirmationMessage.getText();
    }

}
