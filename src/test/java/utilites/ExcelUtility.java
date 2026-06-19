package utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.v147.filesystem.model.File;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	public int getRowCount(String sheetname) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	public int getCellCount(String sheetname,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	public String getCellData(String sheetname,int rownum,int cellnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		DataFormatter format=new DataFormatter();
		String data;
		try
		{
			data=format.formatCellValue(cell);//returns formatted value of a cell as a string 
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
   public void setCellData(String sheetname,int rownum,int cellnum,String data) throws IOException
   {
	   
	   fi=new FileInputStream(path);
	   workbook=new XSSFWorkbook(fi);
	   sheet=workbook.getSheet(sheetname);
	   row=sheet.getRow(rownum);
	   cell=row.createCell(cellnum);
	   cell.setCellValue(data);
	   fo=new FileOutputStream(path);
	   workbook.write(fo);
	   workbook.close();
	   fi.close();
	   fo.close();
	   
   }
}
