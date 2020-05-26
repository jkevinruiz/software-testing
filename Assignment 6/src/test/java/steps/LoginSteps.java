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

public class LoginSteps {
	WebDriver driver;
	WebElement element;
	
	@Given("^I open Firefox browser$")
	public void i_open_Firefox_browser() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "firefox/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Given("^I go to JPetStore page$")
	public void i_go_to_JPetStore_page() throws Throwable {
		driver.get("http://35.192.126.9:8080/jpetstore/");
	}
	
	@Given("^I select Enter the Store$")
	public void i_select_Enter_the_Store() throws Throwable {
		driver.findElement(By.linkText("Enter the Store")).click();
	}

	@Given("^I select Sign in$")
	public void i_select_Sign_in() throws Throwable {
		driver.findElement(By.linkText("Sign In")).click();
	}

	@When("^I enter user info \"([^\"]*)\" and \"([^\"]*)\" combination$")
	public void i_enter_user_info_and_combination(String arg1, String arg2) throws Throwable {
		driver.findElement(By.name("username")).sendKeys(arg1);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(arg2);
		driver.findElement(By.name("signon")).click();
	}

	@Then("^I should be able to login successfully$")
	public void i_should_be_able_to_login_successfully() throws Throwable {
		try {
			element = driver.findElement(By.linkText("Sign Out"));
			assertTrue(true);
		} catch (Exception e) {
			fail();
		} finally {
			driver.quit();
		}
	}
	
	@Then("^I should get an error message for invalid username or password$")
	public void i_should_get_an_error_message_for_invalid_username_or_password() throws Throwable {
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
