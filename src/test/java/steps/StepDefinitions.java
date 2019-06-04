package steps;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class StepDefinitions {
    public static WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    SearchPage searchPage;
    CategoryPage categoryPage;
    MyWishlistPage myWishlistPage;
    CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        System.setProperty( "webdriver.chrome.driver", "src\\webdrivers\\chromedriver.exe" );
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
        homePage = new HomePage( driver );
        loginPage = new LoginPage( driver );
        myAccountPage = new MyAccountPage( driver );
        searchPage = new SearchPage( driver );
        categoryPage = new CategoryPage( driver );
        myWishlistPage = new MyWishlistPage( driver );
        checkoutPage = new CheckoutPage( driver );
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

    @When("^user enters (.*) as search string in the search field and clicks enter$")
    public void userEntersSearchString(String searchString) {
        homePage.searchInputField.sendKeys( searchString );
        homePage.searchInputField.sendKeys( Keys.ENTER );
    }

    @When("^user opens (.*) as category page$")
    public void userOpensCategoryPage(String categoryTitle) {
        homePage.openCategoryMenuLink( categoryTitle );
    }

    @When( "^user click on the first add_to_cart button at the home page$" )
    public void userClicksAddToCartButtonFromTheHomePage(){
        homePage.clickAddToCartButton();
    }

    @Then( "^confirmation pop-up is displayed$" )
    public void confirmationPopupIsDisplayed(){
        Assert.assertTrue( homePage.getIfCartConfirmationPopUpVisible() );
        String currentConfirmationMessage = homePage.getCartConfirmationMessage();
        Assert.assertEquals(currentConfirmationMessage,"Product successfully added to your shopping cart" );
    }


    // Login page steps
    @Given("^user is on Login Page$")
    public void userIsOnLoginPage() {
        loginPage.openLoginPage();
    }

    @Then("^user is redirected to the Login page$")
    public void loginPageIsOpened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, loginPage.getDefaultLoginPageURL() );
    }

    @When("^user enters (.*) as username and (.*) as password$")
    public void userEntersUsernameAndPassword(String username, String password) {
        loginPage.enterLoginAndPassword( username, password );
    }

    @Then("^(.*) as success message is displayed$")
    public void successMsessageIsDisplayed(String message) {
        Assert.assertEquals( message, loginPage.getSuccessLoginMessage() );
    }

    @Then("^(.*) as login page error message is displayed$")
    public void errorMessageIsDisplayed(String message) {
        Assert.assertEquals( message, loginPage.getErrorLoginMessage() );
    }

    @When( "^user is logged in$"  )
    public void userLogsIn() {
        loginPage.openLoginPage();
        loginPage.enterLoginAndPassword( "zelenayakoshka@yandex.ru", "Qwer1234!" );
    }

    // MyAccount Page steps

    @When("^user is on My Account page$")
    public void userIsOnMyAccountPage() {
        myAccountPage.openMyAccountPage();
    }

    @Then("^user is redirected to the account page$")
    public void accountPageIsOpened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, myAccountPage.getDefaultMyAccountPageURL() );
    }

    @When( "^user clicks my_wishlists link$" )
    public void userClicksMyWishlistsLink() {
        myAccountPage.clickMyWishliststLink();
    }

    // Search Page steps
    @Then("^user is redirected to the search result page$")
    public void userIsRedirectedToTheSearchResultPage() {
        Assert.assertEquals( driver.getTitle(), "Search - My Store" );
    }

    @Then("^(.*) is displayed at the top$")
    public void searchStringDisplayedAtTheSearchPage(String searchString) {
        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue( pageTitle.contains( searchString.toUpperCase() ) );
    }

    @Then("^(.*) search results are loaded$")
    public void searchResultsAreLoaded(String searchResult) {
        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue( pageTitle.contains( searchResult ) );
    }

    @Then("^(.*) as search item is displayed in the results$")
    public void searchItemIsDisplayed(String testSearchItem) {
        String[] allProductsTitles = searchPage.getAllProductContainersTitles();
        Assert.assertTrue( Arrays.asList( allProductsTitles ).contains( testSearchItem ) );
    }

    @Then("^(.*) as search page error message is displayed$")
    public void zeroResultsErrorMessageIsDisplayed(String zeroResultsErrorMessage) {
        String currentErrorMessage = searchPage.getErrorMessageText();
        Assert.assertEquals( currentErrorMessage, zeroResultsErrorMessage );
    }

    // Category Page steps
    @When("^user opens (.*) as subcategory page$")
    public void userOpensSubcategoryPage(String subcategoryTitle) {
        categoryPage.openSubcategoryLink( subcategoryTitle );
    }

    @Then("^user is able to see (.*) as item in the catalogue$")
    public void catalogueItemIsDisplayed(String testCatalogueItem) {
        String[] allCatalogueItems = categoryPage.getAllCatalogueItems();
        Assert.assertTrue( Arrays.asList( allCatalogueItems ).contains( testCatalogueItem ) );
    }

    @Then("^user is able to see (.*) as subcategory")
    public void  userIsAbleToSeeSubcategory (String subcategory){
        Assert.assertTrue( categoryPage.getIfSubCategoryDropDownVisible(subcategory) );
     }

    @When("user is at the Women category page")
    public void userIsOnWomenCategoryPage() {
        categoryPage.openWomenCategoryPage();
    }

    @Then("^user selects (.*) as size")
    public void  userSelectsSize(String userSize){
        categoryPage.clickSizeCheckbox(userSize);
    }

    @Then("^user opens the first item at the category page")
    public void  userOpensFirstItemAtThePage(){
        categoryPage.openFirtsItem();
    }

    @Then("^(.*) size is available for purchase")
    public void sizeIsAvailableForPurchase(String sizeLabel){
        Assert.assertTrue(categoryPage.sizeAvailableForPurchase(sizeLabel));
    }

    @Then("^user hovers over the (.*) menu item")
    public void  userHoversOverTheCategoryMenuItem (String category){
        categoryPage.hoverOverCategory(category);
    }

    @Given("^user is on item page$")
    public void userIsOnItemPage() {
        driver.get( "http://automationpractice.com/index.php?id_product=1&controller=product" );
    }
    @When( "^user clicks add_to_cart button at the item page$" )
    public void userClicksAddToCartButtonFromTheItemPage() {
        categoryPage.clickAddToCartButton();
    }

    @When( "^user clicks proceed_to_checkout at the pop-up$" )
    public void userClicksProceedToCheckoutAtThePopUp() {
        categoryPage.clickProceedToCheckoutPopUpButton();
    }

    @When( "^user clicks proceed_to_checkout$" )
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
        Assert.assertEquals( currentUrl, myWishlistPage.getDefaultMyWishlistPageURL() );
    }

    @When("^user clicks add_to_wlishlist link$")
    public void userClicksAddToWlishlistLink() {
        myWishlistPage.addToWishlistButtonClick();
    }

    @Then("^wishlist confirmation pop-up is displayed$")
    public void wishlistConfirmationPopupDisplayed() {
        String confirmationPopUpText = "Added to your wishlist.";
        Assert.assertEquals( confirmationPopUpText ,myWishlistPage.getPopUpMessage() );
    }

    @When("^user clicks wishlist title$")
    public void userClicksWlishlistTitile() {
        myWishlistPage.expandMyWhishlistLinkClick();
    }

    @Then("^previously added item is displayed in the list$")
    public void itemIsDisplayedInTheList() {
        Assert.assertTrue(myWishlistPage.productImageDisplayed());
    }

    @When("^user clicks remove button$")
    public void userClicksRemoveButton() {
        myWishlistPage.removeSignClick();
    }

    @When("^user refreshes page$")
    public void userRefreshesPage() {
        driver.navigate().refresh();
    }

    // TODO figure out why it doesn't work
    @Then("^item deleted from the list$")
    public void itemDeletedFromTheList() throws Exception{
       Thread.sleep(7000);
       Assert.assertFalse(myWishlistPage.productImageDisplayed());}


    // Checkout Page  Steps

    @Given("^user is on 1st checkout page$")
    public void userIsOn1CheckoutPage() {
       driver.get(checkoutPage.getDefaultCheckoutPageURL());
    }

    @Then("^user is redirected to the 1st checkout page$")
    public void userIsRedirectedToThe1CheckoutPage() {
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "SUMMARY" ) );
    }

    @Then("^user is redirected to the 2nd checkout page$")
    public void userIsRedirectedToThe2CheckoutPage() {
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "AUTHENTICATION" ) );
    }

    @Then("^user is redirected to the 3rd checkout page$")
    public void userIsRedirectedToThe3CheckoutPage() {
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "ADDRESS" ) );
    }

    @Then("^user is redirected to the 4th checkout page$")
    public void userIsRedirectedToThe4CheckoutPage() {
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "SHIPPING" ) );
    }

    @Then("^user agrees the terms of service$")
    public void userAgreesTheTermsOfService() {
       checkoutPage.agreeTheTermOfService();
    }


    @Then("^user is redirected to the 5th checkout page$")
    public void userIsRedirectedToThe5CheckoutPage() {
        String h = checkoutPage.getPageHeader();
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "PAYMENT" ) );
    }

    @Then("^user selects payment by wire$")
    public void userSelectsPaymentByWire() {
        checkoutPage.clickPaymentByWire();
    }

    @Then( "^user clicks confirmation button$" )
    public void userClicksConfirmationButton() {
        categoryPage.clickProceedToCheckoutButton();
    }

    @Then( "^confirmation message is displayed$" )
    public void confirmationMessageIsDisplayed() {
        String message = "Your order on My Store is complete.";
        Assert.assertEquals(message, checkoutPage.getConfirmationMessage());
    }

    @Then("^item is displayed at the checkout page$")
    public void itemIsDisplayedAtTheCheckoutPage() {
        Assert.assertTrue(checkoutPage.cartProductDisplayed());
    }

    @Then("^user loggs in from the checkout page$")
    public void userLoggsInFromTheCheckoutPage() {
        checkoutPage.enterLoginAndPassword( "zelenayakoshka@yandex.ru", "Qwer1234!" );
    }


}
