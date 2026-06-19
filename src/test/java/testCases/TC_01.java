package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.registrationPage;

public class TC_01 extends BaseClass {
	
	@Test(groups={"Sanity","Regression","Master"})
	void registration()
	{
		logger.info("*** Staring the TC01*****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("*** clicked on my acoount*****");
		hp.clickRegister();
		logger.info("*** clicked on register*****");
		registrationPage rg=new registrationPage(driver);
		rg.firstname("koushi");
		rg.lastname("sai");
		rg.email(randomstring()+"@gmali.com");
		rg.phone(randomnumber());
		String pass=randomstring();
		rg.password(pass);
		rg.confpass(pass);
		rg.accept();
		rg.sub();
		String mes=rg.getmessage();
	   
		if(mes.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("debug logs");
			Assert.fail("Account creation failed");
			
		}
		}
		catch(Exception e)	
		{
		    Assert.fail();
		}
		logger.info("****test case completed*****");
	}
	   
}
