package utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	///DataProvider=1
   @DataProvider(name="LoginData")
   public String[][] getdata() throws IOException
   {
	   String path=".\\TestData\\FrameWork.xlsx";//taking xl file testdata
	   ExcelUtility ex=new ExcelUtility(path);
	   int totalrow=ex.getRowCount("sheet1");
	   int totalcols=ex.getCellCount("sheet1", 1);
	   String logindata[][]=new String[totalrow][totalcols];//create two dimension array to store data
	   for(int i=1;i<=totalrow;i++)
	   {
		   for(int j=0;j<totalcols;j++)
		   {
			   logindata[i-1][j]=ex.getCellData("sheet1", i, j);
		   }
	   }
	   return logindata;
   }
}
