package gluecode;
import java.util.concurrent.TimeUnit;


import framework.pageobjects.HomePage;
import framework.pageobjects.LoginPage;
import framework.pageobjects.SearchPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import org.junit.Assert;

public class Test {
    public static WebDriver driver;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^user is on homepage$")
    public void user_is_on_homepage() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chormedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @When("^user navigates to Login Page$")
    public void user_navigates_to_Login_Page() throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.signInLink.click();
    }

    @When("^user enters (.*) as username and (.*) as password")
    public void user_enters_username_and_password(String username, String password) throws Throwable {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLoginAndPassword(username, password);
    }

    @Then("^(.*) as success message is displayed$")
    public void success_message_is_displayed(String message) throws Throwable {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(message, loginPage.getSuccessLoginMessage());
    }

    @Then("^(.*) as error message is displayed$")
    public void error_message_is_displayed(String message) throws Throwable {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(message, loginPage.getErrorLoginMessage());
    }

    @And("^user is redirected to the account page$")
    public void pass_this() throws Throwable {
    }

    @When("^user enters (.*) as search_string in the search field$")
    public void user_enters_esarch_string(String search_string) throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.searchInputField.sendKeys(search_string);
    }

    @When("^user clicks enter$")
    public void user_clicks_enter() throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.searchInputField.sendKeys(Keys.ENTER);
    }

    @Then("^user is redirected to the search result page$")
    public void user_is_redirected_to_the_search_result_page() throws Throwable {
        Assert.assertEquals(driver.getTitle(), "Search - My Store");
    }

    @Then("^(.*) is displayed at the top$")
    public void search_string_displayed_at_the_search_page(String search_string) throws Throwable {
        SearchPage searchPage = new SearchPage(driver);
        String pageTitle = searchPage.pageTitle.getText();
        Assert.assertTrue(pageTitle.contains(search_string.toUpperCase()));
    }
}