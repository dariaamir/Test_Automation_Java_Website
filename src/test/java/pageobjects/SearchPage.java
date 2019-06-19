package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class SearchPage {

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "page-heading")
    private WebElement pageTitle;

    @FindBy(css = ".product-container .product-name")
    private List<WebElement> allProductContainres;

    @FindBy(className = "alert-warning")
    private WebElement errorMessage;

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public String[] getAllProductContainersTitles(){
        int listSize = allProductContainres.size();
        String[] allProductsTitles = new String[listSize];
        for (int i = 0; i< listSize; i++){
            allProductsTitles[i] = allProductContainres.get( i ).getAttribute("title");
        }
        return allProductsTitles;
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }
}