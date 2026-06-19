package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {
	public static WebDriver driver;
	public 	Logger logger;
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master","DataDriven"})
	@Parameters({"OS","browser"})
	public void setup(String os,String br) throws IOException
	{
		
		//loading cofg.properties file
		
		logger=LogManager.getLogger(this.getClass());
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
	              DesiredCapabilities capabilities=new DesiredCapabilities();
	              //OS
	              switch(os.toLowerCase())
	              {
	              case "windows":
	            	  capabilities.setPlatform(Platform.WIN11); break;
	              case "mac":
	            	  capabilities.setPlatform(Platform.MAC);break;
	              default:
	            	  System.out.println("No matching OS"); return;
	              }
	              //Browser
	              switch(br.toLowerCase())
	              {
	              case "chrome":
	            	  capabilities.setBrowserName("chrome"); break;
	              case "edge":
	            	  capabilities.setBrowserName("MicrosoftEdge");break;
	              default:
	            	  System.out.println("No matching browser");return;
	              }
	              driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome":driver=new ChromeDriver(); break;
				case "edge":driver=new EdgeDriver(); break;
				case "firefox":driver=new FirefoxDriver();break;
				default:System.out.println("invalid browser");return;
			}
		}
		//driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}
	@AfterClass(groups={"Sanity","Regression","Master","DataDriven"})
	public void tairdown()
	{
		driver.quit();
	}
	 public String randomstring()
	    {
	    	String generatedstring=RandomStringUtils.randomAlphabetic(5);
	    	return generatedstring;
	    }
	    public String randomnumber()
	    {
	    	String generatedstring=RandomStringUtils.randomNumeric(10);
	    	return generatedstring;
	    }
	    
	    public String capureScreen(String tname)
	    {
	    	String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			TakesScreenshot takescreenshot=(TakesScreenshot)driver;
			File sourceFile=takescreenshot.getScreenshotAs(OutputType.FILE);
	    	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +" "+timestamp+ ".png";
	    	File targetfile=new File(targetFilePath);
	    	sourceFile.renameTo(targetfile);
	    	return targetFilePath;
	    }
}
