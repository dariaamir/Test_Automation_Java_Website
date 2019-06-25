package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;


public class CategoryPage {
    private WebDriver driver;


    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        }

    private String womenCategoryPageURL = "http://automationpractice.com/index.php?id_category=3&controller=category";

    @FindBy(className = "login")
    public WebElement signInLink;

    @FindBy(className = "search_query")
    public WebElement searchInputField;

    @FindBy(css = ".subcategory-image [title='Tops']")
    private WebElement subcategoryTopslink;

    @FindBy(css = ".subcategory-image [title='Casual Dresses']")
    private WebElement subcategoryCasualDresseslink;

    @FindBy(css = "title=\"Blouses\"")
    private WebElement subcategoryBlouses;

    @FindBy(xpath = "title=\"Evening Dresses\"")
    private WebElement subcategoryEveningDresses;

    @FindBy(css = "[title=\"Women\"]")
    private WebElement categoryWomen;

    @FindBy(css = ".product-container .product-name")
    private List<WebElement> allProductContainres;

    @FindBy(css = ".checkbox[name=layered_id_attribute_group_1]")
    private WebElement sizeCheckboxS;

    @FindBy(css = ".checkbox[name=layered_id_attribute_group_2]")
    private WebElement sizeCheckboxM;

    @FindBy(css = ".checkbox[name=layered_id_attribute_group_3]")
    private WebElement sizeCheckboxL;

    @FindBy(className = "product_img_link")
    private WebElement firstItemAtThePage;

    @FindBy(css = "select[id='group_1'] option")
    private List <WebElement> sizeSelectionDropDown;

    @FindBy(name = "Submit")
    private WebElement AddToCartButton;

    @FindBy(css = "[title='Proceed to checkout']")
    private WebElement proceedToCheckoutPopUpButton;

    @FindBy(css = ".cart_navigation .button")
    private WebElement proceedToCheckoutButton;


    public void openSubcategoryLink(String subcategoryTitle){
        WebElement subcategory = null;
        switch (subcategoryTitle) {
            case "Tops":
                subcategory = subcategoryTopslink;
                break;
            case "Casual Dresses":
                subcategory = subcategoryCasualDresseslink;
                break;
        }
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(subcategory));
        subcategory.click();
    }

    public void hoverOverCategory(String categoryTitle){
        WebElement category = null;
        switch (categoryTitle) {
            case "Woman":
                category = categoryWomen;
                break;
        }
        Actions action = new Actions(driver);
        action.moveToElement(category).build().perform();
    }

    public boolean getIfSubCategoryDropDownVisible(String subcategoryTitle){
        WebElement subcategory = null;
        switch (subcategoryTitle) {
            case "Blouses":
                subcategory = categoryWomen;
                break;
            case "Evening Dresses":
                subcategory = categoryWomen;
                break;
        }
        return subcategory.isDisplayed();
    }

    public String[] getAllCatalogueItems(){
        int listSize = allProductContainres.size();
        String[] allCatalogueItems = new String[listSize];
        for (int i = 0; i< listSize; i++){
            allCatalogueItems[i] = allProductContainres.get(i).getAttribute("title");
        }
        return allCatalogueItems;
    }

    public void openWomenCategoryPage(){
        driver.get(womenCategoryPageURL);
    }

    public void clickSizeCheckbox(String sizeLabelString){
        WebElement sizeLabel = null;
        switch (sizeLabelString) {
            case "S":
                sizeLabel = sizeCheckboxS;
                break;
            case "M":
                sizeLabel = sizeCheckboxM;
                break;
            case "L":
                sizeLabel = sizeCheckboxL;
                break;
        }
        sizeLabel.click();
    }

    public void openFirtsItem(){
               this.firstItemAtThePage.click();
    }

    public boolean sizeAvailableForPurchase(String sizeLabel){
        List<String> e = new ArrayList<>();
        for (WebElement el: sizeSelectionDropDown){
            e.add(el.getText());
        }
        return e.contains(sizeLabel);
    }

    public void clickAddToCartButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(this.AddToCartButton));
        this.AddToCartButton.click();
    }

    public void clickProceedToCheckoutPopUpButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(this.proceedToCheckoutPopUpButton));
        this.proceedToCheckoutPopUpButton.click();
    }

    public void clickProceedToCheckoutButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(this.proceedToCheckoutButton));
        this.proceedToCheckoutButton.click();
    }
}
