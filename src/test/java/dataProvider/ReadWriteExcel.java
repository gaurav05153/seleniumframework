package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadWriteExcel 
{
	FileInputStream fileInput;
	HSSFWorkbook workBook;
	HSSFSheet sheet;
	HSSFCell cell;
	
	public ReadWriteExcel() throws IOException
	{
		fileInput = new FileInputStream(new File(ConfigFileReader.getTestDataFile()));
		workBook = new HSSFWorkbook(fileInput);		
	}
	
	public HSSFSheet getSheet(String sheetName)
	{
		sheet = workBook.getSheet(sheetName);
		return sheet;
	}
}
