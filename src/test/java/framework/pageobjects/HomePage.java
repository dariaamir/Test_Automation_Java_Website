package framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    private WebDriver driver;

    public String homePageURL = "http://automationpractice.com/index.php";

    public void openHomePage(){
        driver.get( homePageURL );
    }

    @FindBy(className = "login")
    private WebElement signInLink;

    public void clickSignInLink(){
        this.signInLink.click();
    }

    @FindBy(className = "search_query")
    public WebElement searchInputField;

    @FindBy(css = ".sf-with-ul[title=\"Women\"]")
    private WebElement categoryWomenMenuItem;

    @FindBy(css = ".sf-with-ul[title=\"Dresses\"]")
    private WebElement categoryDressesMenuItem;

    public void openCategoryMenuLink(String categoryTitle){
        switch (categoryTitle) {
            case "Women":
                categoryWomenMenuItem.click();
            case "Dresses":
                categoryDressesMenuItem.click();
        }
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}