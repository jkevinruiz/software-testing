package steps;

import cucumber.api.java.en.Given;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

public class CreateAccount {
	WebDriver driver;
	WebElement element;
	
	@Given("^I open firefox browser$")
	public void i_open_firefox_browser() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "firefox/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Given("^I go to JPetStore registration page$")
	public void i_go_to_JPetStore_registration_page() throws Throwable {
		driver.get("http://35.192.126.9:8080/jpetstore/actions/Account.action?newAccountForm=");
	}
	
	@When("^I enter the user information \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void i_enter_the_user_information(String arg1, String arg2, String arg3) throws Throwable {
		driver.findElement(By.name("username")).sendKeys(arg1);
		driver.findElement(By.name("password")).sendKeys(arg2);
		driver.findElement(By.name("repeatedPassword")).sendKeys(arg3);
	}

	@When("^account information \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void account_information(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9) throws Throwable {
		driver.findElement(By.name("account.firstName")).sendKeys(arg1);
		driver.findElement(By.name("account.lastName")).sendKeys(arg2);
		driver.findElement(By.name("account.email")).sendKeys(arg3);
		driver.findElement(By.name("account.phone")).sendKeys(arg4);
		driver.findElement(By.name("account.address1")).sendKeys(arg5);
		driver.findElement(By.name("account.city")).sendKeys(arg6);
		driver.findElement(By.name("account.state")).sendKeys(arg7);
		driver.findElement(By.name("account.zip")).sendKeys(arg8);
		driver.findElement(By.name("account.country")).sendKeys(arg9);
	}

	@When("^I click on save account information$")
	public void i_click_on_save_account_information() throws Throwable {
		driver.findElement(By.name("newAccount")).click();
	}

	@Then("^I should be able to login with the new account using the new \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_login_with_the_new_account_using_the_new_and(String arg1, String arg2) throws Throwable {
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
	
	@Then("^I should get an error message$")
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
