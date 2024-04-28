package SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver2) {
		super(driver2);
		this.driver=driver2;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationmessage;
	
	public String getConfirmationMessage()
	{
		return confirmationmessage.getText();
	}

}
