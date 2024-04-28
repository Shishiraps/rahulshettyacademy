package SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
WebDriver driver;

public ProductCatalogue(WebDriver driver)
{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(css=".mb-3")
List<WebElement> products;

@FindBy(css=".ng-animating")
WebElement spinner;

By toastmessage=By.cssSelector("#toast-container");

public List<WebElement> returnProducts()
{
	WaitForElementToAppear(By.cssSelector(".mb-3"));
	return products;
}

public WebElement getProductByName(String productname)
{
	WebElement prod=products.stream().filter(product-> 
	product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
	return prod;
}

public void addProductToCart(String productname) throws InterruptedException
{
	WebElement prod=getProductByName(productname);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	WaitForElementToAppear(toastmessage);
	WaitForElementToDisappear(spinner);
}


}
