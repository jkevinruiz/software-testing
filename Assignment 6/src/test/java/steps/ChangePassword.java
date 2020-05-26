package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

public class ChangePassword {
	WebDriver driver;
	WebElement element;
	
	@Given("^I open FireFox browser$")
	public void i_open_FireFox_browser() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "firefox/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Given("^I go to JPetStore Sign on page$")
	public void i_go_to_JPetStore_signon_page() throws Throwable {
		driver.get("http://35.192.126.9:8080/jpetstore/actions/Account.action?signonForm=");
	}
	
	@When("^I enter user info \"([^\"]*)\" and password \"([^\"]*)\"")
	public void i_enter_user_name_and_password(String arg1, String arg2) throws Throwable {
		driver.findElement(By.name("username")).sendKeys(arg1);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(arg2);
		driver.findElement(By.name("signon")).click();
	}
	
	@When("^I select My Account")
	public void i_select_my_account() throws Throwable{
		driver.findElement(By.linkText("My Account")).click();
	}
	
	@When("^I enter a new password \"([^\"]*)\" and repeated password \"([^\"]*)\"$")
	public void account_information(String arg1, String arg2) throws Throwable {
		driver.findElement(By.name("password")).sendKeys(arg1);
		driver.findElement(By.name("repeatedPassword")).sendKeys(arg2);
	}

	@When("^click on save account information$")
	public void click_on_save_account_information() throws Throwable {
		driver.findElement(By.name("editAccount")).click();
	}

	@Then("^should be able to login with the new account using the new \"([^\"]*)\" and \"([^\"]*)\"$")
	public void should_be_able_to_login_with_the_new_account_using_the_new_and(String arg1, String arg2) throws Throwable {
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.name("username")).sendKeys(arg1);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(arg2);
		driver.findElement(By.name("signon")).click();
		
		try {
			element = driver.findElement(By.linkText("Sign Out"));
			assertTrue(true);
		} catch (Exception e) {
			fail();
		} finally {
			driver.quit();
		}
	}
	
	@Then("^should get an error message$")
	public void i_should_get_an_error_message() throws Throwable {
		try {
			element = driver.findElement(By.className("messages"));
			assertTrue(true);
		} catch (Exception e) {
			fail();
		} finally {
			driver.quit();
		}
	}
}
