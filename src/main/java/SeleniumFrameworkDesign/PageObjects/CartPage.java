package SeleniumFrameworkDesign.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;

	public CartPage(WebDriver driver2) {
		super(driver2);
		this.driver=driver2;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	public Boolean VerifyProductDisplay(String productname)
	{
	
		Boolean match=   cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productname));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkout.click();
		return new CheckoutPage(driver);
		
	}

}
