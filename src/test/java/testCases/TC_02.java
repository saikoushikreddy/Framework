package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_02 extends BaseClass {
    @Test(groups= {"Sanity","Master"})
	public void login_verify()
	{
		try
		{
		logger.info("******Staring Test Case=2*****");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.email(p.getProperty("email"));
		lp.password(p.getProperty("password"));
		lp.login();
		
		MyAccountPage ac=new MyAccountPage(driver);
		boolean result=ac.myaccount();
		Assert.assertEquals(result, true,"LoginFailed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******fineshed Test Case=2*****");
		
	}
}
