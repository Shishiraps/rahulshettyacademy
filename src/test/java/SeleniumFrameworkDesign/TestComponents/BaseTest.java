package SeleniumFrameworkDesign.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initalizeDriver() throws IOException
	{
	
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//GlobalData.properties");
		prop.load(fis);
		
		String browsername=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//String browsername=prop.getProperty("browser");
		
		if(browsername.contains("firefox"))
		{
			FirefoxOptions options=new FirefoxOptions();
			
			WebDriverManager.firefoxdriver().setup();
			if(browsername.contains("headless"))
			{
				options.addArguments("--headless");
			}
		
		driver= new FirefoxDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			//edge
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	
	public List<HashMap<String,String>> getJsonDataToMap(String filepath) throws IOException
	{
		String jsoncontent=FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initalizeDriver();
		landingpage=new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
		
		@AfterMethod(alwaysRun=true)
		public void tearDown()
		{
			driver.close();
		}
	
		
		public String getScreenshot(String testcasename,WebDriver driver) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
			//or
			//return file;
		}
		
	
}
