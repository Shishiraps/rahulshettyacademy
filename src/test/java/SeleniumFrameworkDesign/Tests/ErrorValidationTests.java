package SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import SeleniumFrameworkDesign.TestComponents.BaseTest;


public class ErrorValidationTests extends BaseTest{

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		
		String productname="ZARA COAT 3";
		landingpage.loginapplication("shishi@example.com", "Sishira!");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		System.out.println("verified incorrect credentials");
		
	}
	
@Test
	
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		
		String productname="ZARA COAT 3";
		ProductCatalogue productcatalogue=landingpage.loginapplication("shishi@example.com", "Shishira1!");
		List<WebElement> products=productcatalogue.returnProducts();
		productcatalogue.addProductToCart(productname);
		CartPage cartpage=productcatalogue.goToCart();
		Boolean match=cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		System.out.println("Verified wrong product ZARA COAT 33");
		
	}
}
			

	
	


