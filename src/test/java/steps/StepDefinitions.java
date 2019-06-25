package steps;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cucumber.api.DataTable;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.SearchPage;
import pageobjects.CategoryPage;
import pageobjects.MyWishlistPage;
import pageobjects.CheckoutPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class StepDefinitions {
    private static WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;
    private SearchPage searchPage;
    private CategoryPage categoryPage;
    private MyWishlistPage myWishlistPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        searchPage = new SearchPage(driver);
        categoryPage = new CategoryPage(driver);
        myWishlistPage = new MyWishlistPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Home Page steps
    @Given("^user is on Home Page$")
    public void userIsOnHomePage() {
        homePage.openHomePage();
    }

    @When("^user opens the Login Page link$")
    public void userOpensTheLoginPageLink() {
        homePage.clickSignInLink();
    }

    @When("^user enters search string in the search field$")
    public void userEntersSearchString(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String searchString = dataMap.get("search string");
        homePage.searchInputField.sendKeys(searchString);
    }

    @And("^user clicks Enter$")
    public void userClicksEnter() {
        homePage.searchInputField.sendKeys(Keys.ENTER);
    }

    @When("^user opens category page$")
    public void userOpensCategoryPage(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String categoryTitle = dataMap.get("category");
        homePage.openCategoryMenuLink(categoryTitle);
    }

    @When("^user click on the first add_to_cart button at the home page$")
    public void userClicksAddToCartButtonFromTheHomePage(){
        homePage.clickAddToCartButton();
    }

    @Then("^confirmation pop-up is displayed$")
    public void confirmationPopupIsDisplayed(){
        Assert.assertTrue(homePage.getIfCartConfirmationPopUpVisible());
        String currentConfirmationMessage = homePage.getCartConfirmationMessage();
        Assert.assertEquals(currentConfirmationMessage,"Product successfully added to your shopping cart");
    }


    // Login page steps
    @Given("^user is on Login Page$")
    public void userIsOnLoginPage() {
        loginPage.openLoginPage();
    }

    @Then("^user is redirected to the Login page$")
    public void loginPageIsOpened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, loginPage.getDefaultLoginPageURL());
    }

    @And("^user enters credentials$")
    public void userEntersCredentials(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String username = dataMap.get("username");
        String password = dataMap.get("password");
        loginPage.enterLoginAndPassword(username, password);
    }

    @Then("^success message is displayed$")
    public void successMsessageIsDisplayed(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String message = dataMap.get("success message");
        Assert.assertEquals(message, loginPage.getSuccessLoginMessage());
    }

    @Then("^error message is displayed$")
    public void errorMessageIsDisplayed(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String message = dataMap.get("error message");
        Assert.assertEquals(message, loginPage.getErrorLoginMessage());
        loginPage.clearInputFields();
    }

    @When("^user is logged in$")
    public void userLogsIn() {
        loginPage.openLoginPage();
        loginPage.enterLoginAndPassword("zelenayakoshka@yandex.ru", "Qwer1234!");
    }

    // MyAccount Page steps

    @When("^user is on My Account page$")
    public void userIsOnMyAccountPage() {
        myAccountPage.openMyAccountPage();
    }

    @Then("^user is redirected to the account page$")
    public void accountPageIsOpened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, myAccountPage.getDefaultMyAccountPageURL());
    }

    @When("^user clicks my_wishlists link$")
    public void userClicksMyWishlistsLink() {
        myAccountPage.clickMyWishliststLink();
    }

    // Search Page steps
    @Then("^user is redirected to the search result page$")
    public void userIsRedirectedToTheSearchResultPage() {
        Assert.assertEquals(driver.getTitle(), "Search - My Store");
    }

    @Then("^search results are displayed at the search page$")
    public void searchResultsAreDisplayedAtTheSearchPage(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String searchString = dataMap.get("search string");
        String searchResult = dataMap.get("search results");
        String testSearchItem = dataMap.get("test search item");

        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains(searchString.toUpperCase()));
        Assert.assertTrue(pageTitle.contains(searchResult));

        String[] allProductsTitles = searchPage.getAllProductContainersTitles();
        Assert.assertTrue(Arrays.asList(allProductsTitles).contains(testSearchItem));
    }

    @Then("^zero results are displayed at the search page$")
    public void zeroSearchResultsAreDisplayedAtTheSearchPage(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String searchResult = dataMap.get("zero search result");
        String zeroResultsErrorMessage = dataMap.get("zero results error message");
        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains(searchResult));

        String currentErrorMessage = searchPage.getErrorMessageText();
        Assert.assertEquals(currentErrorMessage, zeroResultsErrorMessage);
    }

    // Category Page steps
    @When("^user opens subcategory page$")
    public void userOpensSubcategoryPage(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String subcategoryTitle = dataMap.get("subcategory");
        categoryPage.openSubcategoryLink(subcategoryTitle);
    }

    @Then("^user is able to see test item in the catalogue$")
    public void catalogueItemIsDisplayed(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String testCatalogueItem = dataMap.get("test_catalogue_item");
        String[] allCatalogueItems = categoryPage.getAllCatalogueItems();
        Assert.assertTrue(Arrays.asList(allCatalogueItems).contains(testCatalogueItem));
    }

    @Then("^user is able to see subcategory$")
    public void  userIsAbleToSeeSubcategory (DataTable dataTable){
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String subcategory = dataMap.get("subcategory");
        Assert.assertTrue(categoryPage.getIfSubCategoryDropDownVisible(subcategory));
     }

    @When("^user is at the Women category page$")
    public void userIsOnWomenCategoryPage() {
        categoryPage.openWomenCategoryPage();
    }

    @And("^user selects size$")
    public void  userSelectsSize(DataTable dataTable){
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String userSize = dataMap.get("size");
        categoryPage.clickSizeCheckbox(userSize);
    }

    @And("^user opens the first item at the category page$")
    public void  userOpensFirstItemAtThePage(){
        categoryPage.openFirtsItem();
    }

    @Then("^size is available for purchase$")
    public void sizeIsAvailableForPurchase(DataTable dataTable){
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String sizeLabel = dataMap.get("size");
        Assert.assertTrue(categoryPage.sizeAvailableForPurchase(sizeLabel));
    }

    @Then("^user hovers over the category menu item$")
    public void  userHoversOverTheCategoryMenuItem (DataTable dataTable){
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        String category = dataMap.get("category");
        categoryPage.hoverOverCategory(category);
    }

    @Given("^user is on item page$")
    public void userIsOnItemPage() {
        driver.get("http://automationpractice.com/index.php?id_product=1&controller=product");
    }

    @Given("^user clicks add to cart button at the item page$")
    public void userClicksAddToCartButtonAtTheItemPage() {
        categoryPage.clickAddToCartButton();
    }

    @Given("^user adds an item in the cart$")
    public void userAddsAnItemInTheCart() {
        userIsOnItemPage();
        categoryPage.clickAddToCartButton();
    }

    @When("^user clicks proceed to checkout at the pop-up$")
    public void userClicksProceedToCheckoutAtThePopUp() {
        categoryPage.clickProceedToCheckoutPopUpButton();
    }

    @When("^user clicks proceed to checkout$")
    public void userClicksProceedToCheckout() {
        categoryPage.clickProceedToCheckoutButton();
    }

    //My Wishlist Page steps

    @Given("^user is on my_wishlist page$")
    public void userIsOnMyWishlistPage() {
        myWishlistPage.openMyWishlistPageURL();
    }

    @Then("^user is redirected to the my_wishlist page$")
    public void myWishlistPageIsOpened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, myWishlistPage.getDefaultMyWishlistPageURL());
    }

    @When("^user clicks add_to_wlishlist link$")
    public void userClicksAddToWlishlistLink() {
        myWishlistPage.addToWishlistButtonClick();
    }

    @Then("^wishlist confirmation pop-up is displayed$")
    public void wishlistConfirmationPopupDisplayed() {
        String confirmationPopUpText = "Added to your wishlist.";
        Assert.assertEquals(confirmationPopUpText ,myWishlistPage.getPopUpMessage());
    }

    @When("^user clicks wishlist title$")
    public void userClicksWlishlistTitile() {
        myWishlistPage.expandMyWhishlistLinkClick();
    }

    @Given("^user had added item to the wishlist$")
    public void userHadAddedItemToTheWishlist() {
        userIsOnItemPage();
        userClicksAddToWlishlistLink();
    }

    @Then("^previously added item is displayed in the list$")
    public void itemIsDisplayedInTheList() {
        Assert.assertTrue(myWishlistPage.productImageDisplayed());
    }

    @And("^user clicks remove button$")
    public void userClicksRemoveButton() {
        myWishlistPage.removeSignClick();
    }

    @When("^user refreshes page$")
    public void userRefreshesPage() {
        driver.navigate().refresh();
    }

    @Then("^item deleted from the list$")
    public void itemDeletedFromTheList() throws Exception{
       Thread.sleep(7000);
       Assert.assertFalse(myWishlistPage.productImageDisplayed());}


    // Checkout Page Steps

    @Given("^user is at the 1st checkout page$")
    public void userIsOn1CheckoutPage() {
        userIsOnItemPage();
        categoryPage.clickAddToCartButton();
        categoryPage.clickProceedToCheckoutPopUpButton();
    }

    @Then("^user is redirected to the 1st checkout page$")
    public void userIsRedirectedToThe1CheckoutPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, checkoutPage.getDefaultCheckoutPageURL());
        Assert.assertTrue(checkoutPage.getPageHeader().contains("SUMMARY"));
    }

    @Given("^user is at the 2nd checkout page$")
    public void userIsOn2CheckoutPage() {
        userIsOnItemPage();
        categoryPage.clickAddToCartButton();
        categoryPage.clickProceedToCheckoutPopUpButton();
        categoryPage.clickProceedToCheckoutButton();
    }

    @Then("^user is redirected to the 2nd checkout page$")
    public void userIsRedirectedToThe2CheckoutPage() {
        Assert.assertTrue(checkoutPage.getPageHeader().contains("AUTHENTICATION"));
    }

    @Given("^user is at the 3rd checkout page$")
    public void userIsOn3CheckoutPage() {
        userIsOnItemPage();
        categoryPage.clickAddToCartButton();
        categoryPage.clickProceedToCheckoutPopUpButton();
        categoryPage.clickProceedToCheckoutButton();
        checkoutPage.enterLoginAndPassword("zelenayakoshka@yandex.ru", "Qwer1234!");
        categoryPage.clickProceedToCheckoutButton();
    }

    @Then("^user is redirected to the 3rd checkout page$")
    public void userIsRedirectedToThe3CheckoutPage() {
        Assert.assertTrue(checkoutPage.getPageHeader().contains("ADDRESS"));
    }

    @Given("^user is at the 4th checkout page$")
    public void userIsOn4CheckoutPage() {
        userIsOnItemPage();
        categoryPage.clickAddToCartButton();
        categoryPage.clickProceedToCheckoutPopUpButton();
        categoryPage.clickProceedToCheckoutButton();
        checkoutPage.enterLoginAndPassword("zelenayakoshka@yandex.ru", "Qwer1234!");
        categoryPage.clickProceedToCheckoutButton();
    }

    @Then("^user is redirected to the 4th checkout page$")
    public void userIsRedirectedToThe4CheckoutPage() {
        Assert.assertTrue(checkoutPage.getPageHeader().contains("SHIPPING"));
    }

    @Then("^user agrees the terms of service$")
    public void userAgreesTheTermsOfService() {
       checkoutPage.agreeTheTermOfService();
    }

    @When("^user logs in from the checkout page$")
    public void userLogsInFromTheCheckoutPage() {
        checkoutPage.enterLoginAndPassword("zelenayakoshka@yandex.ru", "Qwer1234!");
    }

    @Then("^user is redirected to the 5th checkout page$")
    public void userIsRedirectedToThe5CheckoutPage() {
        Assert.assertTrue(checkoutPage.getPageHeader().contains("PAYMENT"));
    }

    @Given("^user clicks through all checkout pages$")
    public void userClicksThroughAllCheckoutPages() {
        userIsOnItemPage();
        categoryPage.clickAddToCartButton();
        categoryPage.clickProceedToCheckoutPopUpButton();
        categoryPage.clickProceedToCheckoutButton();
        checkoutPage.enterLoginAndPassword("zelenayakoshka@yandex.ru", "Qwer1234!");
        categoryPage.clickProceedToCheckoutButton();
        checkoutPage.agreeTheTermOfService();
        categoryPage.clickProceedToCheckoutButton();
        checkoutPage.clickPaymentByWire();
        categoryPage.clickProceedToCheckoutButton();
    }

    @Then("^purchase is completed$")
    public void purchaseIsCompleted() {
        String message = "Your order on My Store is complete.";
        Assert.assertEquals(message, checkoutPage.getConfirmationMessage());
    }
}
