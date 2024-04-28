package SeleniumFrameworkDesign.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent
{
	WebDriver driver;

	public OrderPage(WebDriver driver2) {
		super(driver2);
		this.driver=driver2;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	public Boolean VerifyOrderDisplay(String productname)
	{
	
		Boolean match=   productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productname));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkout.click();
		return new CheckoutPage(driver);
		
	}

	

}
