package gluecode;
import java.util.concurrent.TimeUnit;


import framework.pageobjects.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chormedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @When("^user navigates to Login Page$")
    public void user_navigates_to_Login_Page() throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.signInLink.click();
    }

    @When("^user enters username and password$")
    public void user_enters_username_and_password() throws Throwable {
        driver.findElement(By.id("email")).sendKeys("blog.cucumber@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Cucumber@blog");
        driver.findElement(By.id("SubmitLogin")).click();
    }
    @When("^user enters wrong email")
    public void user_enters_wrong_email() throws Throwable {
        driver.findElement(By.id("email")).sendKeys("12334qwer");
        driver.findElement(By.id("passwd")).sendKeys("Cucumber@blog");
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @When("^user enters wrong password")
    public void user_enters_wrong_password() throws Throwable {
        driver.findElement(By.id("email")).sendKeys("blog.cucumber@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("wrong.cucumber@blog");
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Then("^success message is displayed$")
    public void success_message_is_displayed() throws Throwable {
        String exp_message = "Welcome to your account. Here you can manage all of your personal information and orders.";
        String actual = driver.findElement(By.cssSelector(".info-account")).getText();
        Assert.assertEquals(exp_message, actual);
    }
    @Then("^wrong email message is displayed$")
    public void wrong_email_message_is_displayed() throws Throwable {
        String exp_message = "Invalid email address.";
        String actual = driver.findElement(By.cssSelector(".alert-danger ol")).getText();
        Assert.assertEquals(exp_message, actual);
    }

    @Then("^wrong password message is displayed$")
    public void wrong_password_message_is_displayed() throws Throwable {
        String exp_message = "Authentication failed.";
        String actual = driver.findElement(By.cssSelector(".alert-danger ol")).getText();
        Assert.assertEquals(exp_message, actual);
    }
}
