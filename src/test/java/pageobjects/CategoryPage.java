package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.util.List;


public class CategoryPage {
    private WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "login")
    public WebElement signInLink;

    @FindBy(className = "search_query")
    public WebElement searchInputField;

    @FindBy(css = ".subcategory-name[title=\"Tops\"]")
    private WebElement subcategoryTopslink;

    @FindBy(css = ".subcategory-name[title=\"Casual Dresses\"]")
    private WebElement subcategoryCasualDresseslink;

    @FindBy(css = "title=\"Blouses\"")
    private WebElement subcategoryBlouses;

    @FindBy(xpath = "title=\"Evening Dresses\"")
    private WebElement subcategoryEveningDresses;

    public void openSubbategoryLink(String subcategoryTitle){
        WebElement subcategory = null;
        switch (subcategoryTitle) {
            case "Tops":
                subcategory = subcategoryTopslink;
            case "Casual Dresses":
                subcategory = subcategoryCasualDresseslink;
        }
        subcategory.click();
    }

    @FindBy(css = "[title=\"Women\"]")
    private WebElement categoryWomen;

    public void hoverOverCategory(String categoryTitle){
        WebElement category = null;
        switch (categoryTitle) {
            case "Woman":
                category = categoryWomen;
        }
        Actions action = new Actions(driver);
        action.moveToElement(category).build().perform();
    }


    public boolean getIfSubCategoryDropDownVisible(String subcategoryTitle){
        WebElement subcategory = null;
        switch (subcategoryTitle) {
            case "Blouses":
                subcategory = categoryWomen;
            case "Evening Dresses":
                subcategory = categoryWomen;
        }
        return subcategory.isDisplayed();
    }
    @FindBy(css = ".product-container .product-name")
    private List<WebElement> allProductContainres;

    public String[] getAllCatalogueItems(){
        int listSize = allProductContainres.size();
        String[] allCatalogueItems = new String[listSize];
        for (int i = 0; i< listSize; i++){
            allCatalogueItems[i] = allProductContainres.get( i ).getAttribute("title");
        }
        return allCatalogueItems;
    }

    @FindBy(name = "Submit")
    private WebElement AddToCartButton;

    public void clickAddToCartButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        this.AddToCartButton.click();
    }

    @FindBy(linkText = "Proceed to checkout")
    private WebElement proceedToCheckoutButton;

    public void clickProceedToCheckoutButton(){
        this.proceedToCheckoutButton.click();
    }
}
