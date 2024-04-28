package SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.OrderPage;

public class AbstractComponent<goToOrderPage> {
		WebDriver driver;
	
	public AbstractComponent(WebDriver driver2) {
		this.driver=driver2;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(css="[routerlink*='cart']")        
	WebElement cartitem;
	
	@FindBy(css="[routerlink*='/dashboard/myorders']") 
	WebElement orderHeader;
	
	
			

	public void WaitForElementToAppear(By findby)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void WaitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	
	
	public void WaitForElementToDisappear(WebElement element) throws InterruptedException
	{
		Thread.sleep(1000);
		//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	
	public CartPage goToCart()
	{
		cartitem.click();
		return new CartPage(driver);
		
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
		
	}

}
