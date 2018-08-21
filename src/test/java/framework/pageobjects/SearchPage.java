package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchPage {
    private WebDriver driver;

    @FindBy(className = "page-heading")
    public WebElement pageTitle;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}