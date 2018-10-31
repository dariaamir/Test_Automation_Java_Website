package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private String CheckoutPageURL = "http://automationpractice.com/index.php?controller=order";

    public String getDefaultCheckoutPageURL(){
        return this.CheckoutPageURL;
    }

    @FindBy(className = "cart_product")
    private WebElement cartProduct;

    public boolean cartProductDisplayed(){
        return cartProduct.isDisplayed();
    }


}
