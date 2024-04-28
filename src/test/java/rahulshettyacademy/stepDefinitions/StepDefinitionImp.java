package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.PageObjects.LandingPage;
import SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest
{
	ConfirmationPage confirmationpage;
	ProductCatalogue productcatalogue;
	LandingPage landingpage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage=launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password)
	{
		productcatalogue=landingpage.loginapplication(username,password);
	}
	
	@When("^I add product (.+) to the cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products=productcatalogue.returnProducts();
		productcatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout the (.+) and submit the order$")
	public void checkout_submit_order(String productname)
	{
		CartPage cartpage=productcatalogue.goToCart();
		Boolean match=cartpage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectountry("India");
		confirmationpage=checkoutpage.submitOrder();
	}
	
	@Then ("{string} message is displayed on the confirmation page")
	public void message_displayed_confirmation_page(String string)
	{
		String confirmmessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("^\\\"([^\\\"]*)\\\" message is displayed on the landing page$")
	public void incorrect_message_displayed(String string1) throws Throwable
	{
		Assert.assertEquals(string1, landingpage.getErrorMessage());
		driver.close();
	}
	
	

}
