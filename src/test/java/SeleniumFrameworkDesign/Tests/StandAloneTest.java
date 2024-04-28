package SeleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.PageObjects.LandingPage;
import SeleniumFrameworkDesign.PageObjects.OrderPage;
import SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import SeleniumFrameworkDesign.TestComponents.Retry;

//import SeleniumFrameworkDesign.PageObjects.LandingPage;


public class StandAloneTest extends BaseTest{
	
	String productName="ZARA COAT 3";

	@Test(dataProvider="getData", groups={"purchase"},retryAnalyzer=Retry.class)
	
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		//commented
		ProductCatalogue productcatalogue=landingpage.loginapplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productcatalogue.returnProducts();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productcatalogue.goToCart();
		Boolean match=cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectountry("India");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmmessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.print("Order completed");
		
	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue=landingpage.loginapplication("shishi@example.com", "Shishira1!");
		OrderPage orderpage=productcatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
		System.out.println("verified order display");
	   	
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	
	
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.JSON");
	
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)}};
	
	}
	
			

	}


