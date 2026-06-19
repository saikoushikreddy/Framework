package utilites;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter spartreporter;
	public ExtentReports extent;
	public ExtentTest test;
	String RepName;
	
	public void onStart(ITestContext context)
	{
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date d=new Date();
		String currentdatetimestamp=df.format(d);*/
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		RepName="Test-Report-" +timestamp+ ".html";
		spartreporter=new ExtentSparkReporter(".\\reports\\"+RepName);
		spartreporter.config().setDocumentTitle("opencart automation report");
		spartreporter.config().setReportName("opencart functional testing");
		spartreporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(spartreporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA	");
        String os=context.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("OperatingSystem", os);
        String browser=context.getCurrentXmlTest().getParameter("browser");
        List<String> groups=context.getCurrentXmlTest().getIncludedGroups();
        if(!groups.isEmpty())
        {
        	extent.setSystemInfo("Groups", groups.toString());
        }
        
	}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+" got successfully executed");
	}
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		try
		{
			String imgPath=new BaseClass().capureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch (Exception e) {
	        e.printStackTrace();
	}
	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context)
	{
		extent.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+RepName;
		File extentreport=new File(pathOfExtentReport);
		try
		{
			Desktop.getDesktop().browse(extentreport.toURI());
		}
		catch(IOException e)
		{
			
		}
	}
		
}
