package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String loginPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    public String getDefaultLoginPageURL(){
        return this.loginPageURL;
    }

    public void openLoginPage(){
        driver.get( loginPageURL );
    }

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "passwd")
    private WebElement passwordInputField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginButton;

    @FindBy(className = "info-account")
    private WebElement loginSuccessMessageText;

    @FindBy(css = ".alert-danger ol")
    private WebElement loginErrorMessageText;

    public void enterLoginAndPassword(String username, String password){
        this.emailInputField.sendKeys(username);
        this.passwordInputField.sendKeys(password);
        this.submitLoginButton.click();
    }

    public String getSuccessLoginMessage(){
        return this.loginSuccessMessageText.getText();
    }

    public String getErrorLoginMessage(){
        return this.loginErrorMessageText.getText();
    }
}