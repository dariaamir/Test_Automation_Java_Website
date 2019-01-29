package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
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

    public void openSubbategoryLink(String subcategoryTitle){
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

    public String womenCategoryPageURL = "http://automationpractice.com/index.php?id_category=3&controller=category";

    public void openWomenCategoryPage(){
        driver.get( womenCategoryPageURL );
    }

    @FindBy(css = ".checkbox[name=layered_id_attribute_group_1]")
    private WebElement sizeCheckboxS;

    @FindBy(css = ".checkbox[name=layered_id_attribute_group_2]")
    private WebElement sizeCheckboxM;

    @FindBy(css = ".checkbox[name=layered_id_attribute_group_3]")
    private WebElement sizeCheckboxL;

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

    @FindBy(className = "product_img_link")
    private WebElement firstItemAtThePage;

    public void openFirtsItem(){
               this.firstItemAtThePage.click();
    }

    @FindBy(css = "select[id='group_1'] option")
    private List <WebElement> sizeSelectionDropDown;


    public boolean sizeAvailableForPurchase(String sizeLabel){
        List<String> e = new ArrayList<>();
        for (WebElement el: sizeSelectionDropDown){
            e.add(el.getText());
        }
        if (e.contains(sizeLabel))return true;
        else return false;
    }

    @FindBy(name = "Submit")
    private WebElement AddToCartButton;

    public void clickAddToCartButton(){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(AddToCartButton));
        this.AddToCartButton.click();
    }

    @FindBy(linkText = "Proceed to checkout")
    private WebElement proceedToCheckoutButton;

    public void clickProceedToCheckoutButton(){
        this.proceedToCheckoutButton.click();
    }
}
