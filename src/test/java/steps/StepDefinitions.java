package steps;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
    public void user_is_on_home_page() {
        homePage.openHomePage();
    }

    @When("^user opens the Login Page link$")
    public void user_opens_the_Login_Page_link() {
        homePage.clickSignInLink();
    }

    @When("^user enters (.*) as search_string in the search field and clicks enter$")
    public void user_enters_esarch_string(String search_string) {
        homePage.searchInputField.sendKeys( search_string );
        homePage.searchInputField.sendKeys( Keys.ENTER );
    }

    @When("^user opens (.*) as category page$")
    public void user_opens_category_page(String categoryTitle) {
        homePage.openCategoryMenuLink( categoryTitle );
    }

    @When( "^user click on the first add_to_cart button at the home page$" )
    public void user_clicks_add_to_cart_button_from_the_home_page(){
        homePage.clickAddToCartButton();
    }

    @Then( "^confirmation pop-up is displayed$" )
    public void confirmation_popup_is_displayed(){
        Assert.assertTrue( homePage.getIfCartConfirmationPopUpVisible() );
        String currentConfirmationMessage = homePage.getCartConfirmationMessage();
        Assert.assertEquals(currentConfirmationMessage,"Product successfully added to your shopping cart" );
    }


    // Login page steps
    @Given("^user is on Login Page$")
    public void user_is_on_login_page() {
        loginPage.openLoginPage();
    }

    @Then("^user is redirected to the Login page$")
    public void login_page_is_opened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, loginPage.getDefaultLoginPageURL() );
    }

    @When("^user enters (.*) as username and (.*) as password$")
    public void user_enters_username_and_password(String username, String password) {
        loginPage.enterLoginAndPassword( username, password );
    }

    @Then("^(.*) as success message is displayed$")
    public void success_message_is_displayed(String message) {
        Assert.assertEquals( message, loginPage.getSuccessLoginMessage() );
    }

    @Then("^(.*) as login page error message is displayed$")
    public void error_message_is_displayed(String message) {
        Assert.assertEquals( message, loginPage.getErrorLoginMessage() );
    }

    @When( "^user is logged in$"  )
    public void user_logs_in() {
        loginPage.openLoginPage();
        loginPage.enterLoginAndPassword( "zelenayakoshka@yandex.ru", "Qwer1234!" );
    }

    // MyAccount Page steps

    @When("^user is on My Account page$")
    public void user_is_on_my_account_page() {
        myAccountPage.openMyAccountPage();
    }

    @Then("^user is redirected to the account page$")
    public void account_page_is_opened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, myAccountPage.getDefaultMyAccountPageURL() );
    }

    @When( "^user clicks my_wishlists link$" )
    public void user_clicks_my_wishlists_link() {
        myAccountPage.clickMyWishliststLink();
    }

    // Search Page steps
    @Then("^user is redirected to the search result page$")
    public void user_is_redirected_to_the_search_result_page() {
        Assert.assertEquals( driver.getTitle(), "Search - My Store" );
    }

    @Then("^(.*) is displayed at the top$")
    public void search_string_displayed_at_the_search_page(String search_string) {
        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue( pageTitle.contains( search_string.toUpperCase() ) );
    }

    @Then("^(.*) search results are loaded$")
    public void search_results_are_loaded(String search_result) {
        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue( pageTitle.contains( search_result ) );
    }

    @Then("^(.*) as search item is displayed in the results$")
    public void search_item_is_displayed(String test_search_item) {
        String[] allProductsTitles = searchPage.getAllProductContainersTitles();
        Assert.assertTrue( Arrays.asList( allProductsTitles ).contains( test_search_item ) );
    }

    @Then("^(.*) as search page error message is displayed$")
    public void zero_results_error_message_is_displayed(String zero_results_error_message) {
        String current_error_message = searchPage.geterrorMessageText();
        Assert.assertEquals( current_error_message, zero_results_error_message );
    }

    // Category Page steps
    @When("^user opens (.*) as subcategory page$")
    public void user_opens_subcategory_page(String subcategoryTitle) {
        categoryPage.openSubbategoryLink( subcategoryTitle );
    }

    @Then("^user is able to see (.*) as item in the catalogue$")
    public void catalogue_item_is_displayed(String test_catalogue_item) {
        String[] allCatalogueItems = categoryPage.getAllCatalogueItems();
        Assert.assertTrue( Arrays.asList( allCatalogueItems ).contains( test_catalogue_item ) );
    }

    @Then("^user is able to see (.*) as subcategory")
    public void  user_is_able_to_see_subcategory (String subcategory){
        Assert.assertTrue( categoryPage.getIfSubCategoryDropDownVisible(subcategory) );
     }

    @When("user is at the Women category page")
    public void user_is_on_women_category_page() {
        categoryPage.openWomenCategoryPage();
    }

    @Then("^user selects (.*) as size")
    public void  user_selects_size(String userSize){
        categoryPage.clickSizeCheckbox(userSize);
    }

    @Then("^user opens the first item at the category page")
    public void  user_opens_first_item_at_the_page(){
        categoryPage.openFirtsItem();
    }

    @Then("^(.*) size is available for purchase")
    public void size_is_available_for_purchase(String sizeLabel){
        Assert.assertTrue(categoryPage.sizeAvailableForPurchase(sizeLabel));
    }

    @Then("^user hovers over the (.*) menu item")
    public void  user_hovers_over_the_category_menu_item (String category){
        categoryPage.hoverOverCategory(category);
    }

    @Given("^user is on item page$")
    public void user_is_on_item_page() {
        driver.get( "http://automationpractice.com/index.php?id_product=1&controller=product" );
    }

    @When( "^user clicks add_to_cart button at the item page$" )
    public void user_clicks_add_to_cart_button_from_the_item_page() {
        categoryPage.clickAddToCartButton();
    }

    @When( "^user clicks proceed_to_checkout$" )
    public void user_clicks_proceed_to_checkout() {
        categoryPage.clickProceedToCheckoutButton();
    }

    //My Wishlist Page steps

    @Given("^user is on my_wishlist page$")
    public void user_is_on_my_wishlist_page() {
        myWishlistPage.openMyWishlistPageURL();
    }

    @Then("^user is redirected to the my_wishlist page$")
    public void my_wishlist_page_is_opened() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, myWishlistPage.getDefaultMyWishlistPageURL() );
    }

    @When("^user clicks add_to_wlishlist link$")
    public void user_clicks_add_to_wlishlist_link() {
        myWishlistPage.addToWishlistButtonClick();
    }

    @Then("^wishlist confirmation pop-up is displayed$")
    public void wishlist_confirmation_popup_displayed() {
        String confirmationPopUpText = "Added to your wishlist.";
        Assert.assertEquals( confirmationPopUpText ,myWishlistPage.getPopUpMessage() );
    }

    @When("^user clicks wishlist title$")
    public void user_clicks_wlishlist_titile() {
        myWishlistPage.expandMyWhishlistLinkClick();
    }

    @Then("^previously added item is displayed in the list$")
    public void item_is_displayed_in_the_list() {
        Assert.assertTrue(myWishlistPage.productImageDisplayed());
    }

    @When("^user clicks remove button$")
    public void user_clicks_remove_button() {
        myWishlistPage.removeSignClick();
    }

    @When("^user refreshes page$")
    public void user_refreshes_page() {
        driver.navigate().refresh();
    }

    @Then("^item deleted from the list$")
    public void item_deleted_from_the_list() throws Exception{
        Assert.assertFalse(myWishlistPage.productImageDisplayed());}


    // Checkout Page  Steps

    @Given("^user is on 1st checkout page$")
    public void user_is_on_1_checkout_page() {
       driver.get(checkoutPage.getDefaultCheckoutPage1URL());
    }

    @Then("^user is redirected to the 1st checkout page$")
    public void user_is_redirected_to_the_1_checkout_page() {
        String h = checkoutPage.getPageHeader();
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "SUMMARY" ) );
    }

    @Then("^user is redirected to the 2nd checkout page$")
    public void user_is_redirected_to_the_2_checkout_page() {
        String h = checkoutPage.getPageHeader();
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "AUTHENTICATION" ) );
    }

    @Then("^user is redirected to the 3rd checkout page$")
    public void user_is_redirected_to_the_3_checkout_page() {
        String h = checkoutPage.getPageHeader();
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "ADDRESS" ) );
    }

    @Then("^user is redirected to the 4th checkout page$")
    public void user_is_redirected_to_the_4_checkout_page() {
        String h = checkoutPage.getPageHeader();
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "SHIPPING" ) );
    }

    @Then("^user is redirected to the 5th checkout page$")
    public void user_is_redirected_to_the_5_checkout_page() {
        String h = checkoutPage.getPageHeader();
        Assert.assertTrue( checkoutPage.getPageHeader().contains( "PAYMENT" ) );
    }

    @Then("^item is displayed at the checkout page$")
    public void item_is_displayed_at_the_checkout_page() {
        Assert.assertTrue(checkoutPage.cartProductDisplayed());
    }

    @Then("^user loggs in from the checkout page$")
    public void user_loggs_in_from_the_checkout_page() {
        checkoutPage.enterLoginAndPassword( "zelenayakoshka@yandex.ru", "Qwer1234!" );
    }


}
