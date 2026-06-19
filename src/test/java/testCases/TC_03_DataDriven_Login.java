package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilites.DataProviders;

public class TC_03_DataDriven_Login extends BaseClass
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void login(String user,String Pass,String Res)
	{
		
	logger.info("******Staring Test Case=3*****");
	try
	{
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clicklogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.email(user);
	lp.password(Pass);
	lp.login();
	
	MyAccountPage ac=new MyAccountPage(driver);
	boolean result=ac.myaccount();
	
	if(Res.equalsIgnoreCase("Valid"))
	{
		if(result==true)
		{
			ac.logOut();
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	if(Res.equalsIgnoreCase("InValid"))
	{
		if(result==true)
		{
			ac.logOut();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("******Fineshed Test Case=3*****");
	}
	

}
