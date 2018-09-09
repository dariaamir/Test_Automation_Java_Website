package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CategoryPage {
    private WebDriver driver;

    @FindBy(className = "login")
    public WebElement signInLink;

    @FindBy(className = "search_query")
    public WebElement searchInputField;

    @FindBy(css = ".subcategory-name[title=\"Tops\"]")
    private WebElement subcategoryTopslink;

    @FindBy(css = ".subcategory-name[title=\"Casual Dresses\"]")
    private WebElement subcategoryCasualDresseslink;

    public void openSubbategoryLink(String categoryTitle){
        switch (categoryTitle) {
            case "Tops":
                subcategoryTopslink.click();
            case "Casual Dresses":
                subcategoryCasualDresseslink.click();
        }
    }

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }
}
