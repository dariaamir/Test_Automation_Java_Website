package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void openSubbategoryLink(String subcategoryTitle){
        switch (subcategoryTitle) {
            case "Tops":
                subcategoryTopslink.click();
            case "Casual Dresses":
                subcategoryCasualDresseslink.click();
        }
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
}
