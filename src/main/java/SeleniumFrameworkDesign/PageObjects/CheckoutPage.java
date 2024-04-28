package SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	public CheckoutPage(WebDriver driver2) {
		super(driver2);
		this.driver=driver2;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectcountry;
	
	@FindBy(css=".btnn")
	WebElement submit;
	
	@FindBy(xpath="//button[contains(@class,ta-item)][2]")
	WebElement country;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectountry(String countryname)
	{
		Actions act = new Actions(driver);
		act.sendKeys(selectcountry, countryname).build().perform();
		WaitForElementToAppear(results);
		country.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		
		submit.click();
		return new ConfirmationPage(driver);
		
	}
		

}
