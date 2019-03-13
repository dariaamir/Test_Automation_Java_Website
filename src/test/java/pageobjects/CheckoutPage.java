package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutPage {

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getDefaultCheckoutPageURL(){
        String checkoutPageURL = "http://automationpractice.com/index.php?controller=order";
        return checkoutPageURL;
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

    @FindBy(id = "cgv")
    private WebElement termsAndConditionsCheckbox;

    public void agreeTheTermOfService(){
        termsAndConditionsCheckbox.click();
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

    @FindBy(css = ".payment_module .bankwire")
    private WebElement paymentByWire;

    public void clickPaymentByWire(){
        this.paymentByWire.click();
    }

    @FindBy(css = ".cheque-indent .dark")
    private WebElement confirmationMessage;

    public String getConfirmationMessage(){
        return this.confirmationMessage.getText();
    }

}
