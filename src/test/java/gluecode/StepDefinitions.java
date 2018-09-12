package gluecode;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.ru.Если;
import framework.pageobjects.HomePage;
import framework.pageobjects.LoginPage;
import framework.pageobjects.MyAccountPage;
import framework.pageobjects.SearchPage;
import framework.pageobjects.CategoryPage;
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

    @Before
    public void setUp() {
        System.setProperty( "webdriver.chrome.driver", "src\\webdrivers\\chromedriver.exe" );
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^user is on Home Page$")
    public void user_is_on_home_page() {
        HomePage homePage = new HomePage( driver );
        homePage.openHomePage();
    }

    @Given("^user is on Login Page$")
    public void user_is_on_login_page() {
        LoginPage loginPage = new LoginPage( driver );
        loginPage.openLoginPage();
    }

    @When("^user opens the Login Page link$")
    public void user_opens_the_Login_Page_link() {
        HomePage homePage = new HomePage( driver );
        homePage.clickSignInLink();
    }

    @Then("^user is redirected to the Login page$")
    public void login_page_is_opened() {
        LoginPage loginPage = new LoginPage( driver );
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, loginPage.getDefaultLoginPageURL() );
    }

    @When("^user enters (.*) as username and (.*) as password$")
    public void user_enters_username_and_password(String username, String password) {
        LoginPage loginPage = new LoginPage( driver );
        loginPage.enterLoginAndPassword( username, password );
    }

    @Then("^(.*) as success message is displayed$")
    public void success_message_is_displayed(String message) {
        LoginPage loginPage = new LoginPage( driver );
        Assert.assertEquals( message, loginPage.getSuccessLoginMessage() );
    }

    @Then("^(.*) as login page error message is displayed$")
    public void error_message_is_displayed(String message) {
        LoginPage loginPage = new LoginPage( driver );
        Assert.assertEquals( message, loginPage.getErrorLoginMessage() );
    }

    @Then("^user is redirected to the account page$")
    public void account_page_is_opened() {
        MyAccountPage myAccountPage = new MyAccountPage( driver );
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals( currentUrl, myAccountPage.getDefaultMyAccountPageURL() );
    }

    @When("^user enters (.*) as search_string in the search field and clicks enter$")
    public void user_enters_esarch_string(String search_string) {
        HomePage homePage = new HomePage( driver );
        homePage.searchInputField.sendKeys( search_string );
        homePage.searchInputField.sendKeys( Keys.ENTER );
    }

    @Then("^user is redirected to the search result page$")
    public void user_is_redirected_to_the_search_result_page() {
        Assert.assertEquals( driver.getTitle(), "Search - My Store" );
    }

    @Then("^(.*) is displayed at the top$")
    public void search_string_displayed_at_the_search_page(String search_string) {
        SearchPage searchPage = new SearchPage( driver );
        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue( pageTitle.contains( search_string.toUpperCase() ) );
    }

    @Then("^(.*) search results are loaded$")
    public void search_results_are_loaded(String search_result) {
        SearchPage searchPage = new SearchPage( driver );
        String pageTitle = searchPage.getPageTitle();
        Assert.assertTrue( pageTitle.contains( search_result ) );
    }

    @Then("^(.*) as search item is displayed in the results$")
    public void search_item_is_displayed(String test_search_item) {
        SearchPage searchPage = new SearchPage( driver );
        String[] allProductsTitles = searchPage.getAllProductContainersTitles();
        Assert.assertTrue( Arrays.asList( allProductsTitles ).contains( test_search_item ) );
    }

    @Then("^(.*) as search page error message is displayed$")
    public void zero_results_error_message_is_displayed(String zero_results_error_message) {
        SearchPage searchPage = new SearchPage( driver );
        String current_error_message = searchPage.geterrorMessageText();
        Assert.assertEquals( current_error_message, zero_results_error_message );
    }

    @When("^user opens (.*) as category page$")
    public void user_opens_category_page(String categoryTitle) {
        HomePage homePage = new HomePage( driver );
        homePage.openCategoryMenuLink( categoryTitle );
    }

    @When("^user opens (.*) as subcategory page$")
    public void user_opens_subcategory_page(String subcategoryTitle) {
        CategoryPage categoryPage = new CategoryPage( driver );
        categoryPage.openSubbategoryLink( subcategoryTitle );
    }

    @Then("^user is able to see (.*) as item in the catalogue")
    public void catalogue_item_is_displayed(String test_catalogue_item) {
        CategoryPage categoryPage = new CategoryPage( driver );
        String[] allCatalogueItems = categoryPage.getAllCatalogueItems();
        Assert.assertTrue( Arrays.asList( allCatalogueItems ).contains( test_catalogue_item ) );
    }

    @When( "^user is logged in$"  )
    public void user_logs_in() {
        LoginPage loginPage = new LoginPage( driver );
        loginPage.openLoginPage();
        loginPage.enterLoginAndPassword( "blog.cucumber@gmail.com", "Cucumber@blog" );
    }

    @When( "^user click on the first add_to_cart button$" )
    public void user_clicks_add_to_cart_button_from_the_home_page(){
        HomePage homePage = new HomePage(driver);
        homePage.clickAddToCartButton();
    }

    @Then( "^confirmation pop-up is displayed$" )
    public void confirmation_popup_is_displayed(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue( homePage.getIfCartConfirmationPopUpVisible() );
        String currentConfirmationMessage = homePage.getCartConfirmationMessage();
        Assert.assertEquals(currentConfirmationMessage,"Product successfully added to your shopping cart" );
    }
}