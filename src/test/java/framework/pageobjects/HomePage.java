package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    private WebDriver driver;

    @FindBy(className = "login")
    public WebElement signInLink;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}