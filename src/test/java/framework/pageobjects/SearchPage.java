package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchPage {
    private WebDriver driver;

    @FindBy(className = "page-heading")
    private WebElement pageTitle;

    public String getPageTitle(){
        return this.pageTitle.getText();
    }

    @FindBy(className = "product-name")
    public WebElement productContainer;

    public String getProductContainerTitle(){
        WebElement productContainer = this.productContainer;
        String title = productContainer.getAttribute("title");
        return title;
    }



    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}