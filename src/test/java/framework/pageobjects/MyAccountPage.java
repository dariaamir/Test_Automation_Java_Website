package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class MyAccountPage {
    private WebDriver driver;

    private String myAccountPageURL = "http://automationpractice.com/index.php?controller=my-account";

    public String getDefaultMyAccountPageURL(){
        return this.myAccountPageURL;
    }

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}