package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MyWishlistPage {
    private WebDriver driver;

    public MyWishlistPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private String MyWishlistPageURL = "http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist";

    public String getDefaultMyWishlistPageURL(){
        return this.MyWishlistPageURL;
    }

    public void openMyWishlistPageURL(){
        driver.get( MyWishlistPageURL );
    }

    @FindBy(id = "wishlist_button")
    private WebElement addToWishlistButton;

    public void addToWishlistButtonClick(){
        this.addToWishlistButton.click();
    }

    @FindBy(className = "fancybox-error")
    private WebElement popUp;

    public String getPopUpMessage(){
        return this.popUp.getText();
    }

    @FindBy(linkText = "My wishlist")
    private WebElement expandMyWhishlistLink;

    public void expandMyWhishlistLinkClick(){
        this.expandMyWhishlistLink.click();
    }

    @FindBy(className = "product_image")
    private WebElement productImage;

    public boolean productImageDisplayed(){
        return productImage.isDisplayed();
    }

    @FindBy(className = "icon-remove-sign")
    private WebElement removeSign;

    public void removeSignClick(){
        this.removeSign.click();
    }

}