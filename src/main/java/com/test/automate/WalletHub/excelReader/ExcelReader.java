package com.test.automate.WalletHub.excelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	
	
	
	public ExcelReader(String path)
	{
		this.path = path;
		
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
			
		public String[][] getDataFromSheet(String sheetName,String ExcelName)
		{
			String[][] dataset = null;
			try
			{
				XSSFSheet sheet = workbook.getSheet(sheetName);
				int totRow = sheet.getLastRowNum() + 1;
				int totCol = sheet.getRow(0).getLastCellNum();
			
				dataset = new String[totRow-1][totCol];
			
				for(int i=1; i<totRow;i++)
				{
					XSSFRow row = sheet.getRow(i);
					for(int j =0; j<totCol;j++)
					{
						XSSFCell cell = row.getCell(j);
				
							if(cell.getCellType()==cell.CELL_TYPE_STRING)
								{
									dataset[i-1][j] = cell.getStringCellValue();
								}
				
							else if(cell.getCellType()== cell.CELL_TYPE_NUMERIC)
								{
									dataset[i-1][j] = String.valueOf(cell.getNumericCellValue());
								}
				
					} 
			
				}
			
			    return dataset;
			}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			return dataset;
		
		}
		
		
		public String getCellData(String sheetName,String ColName, int rownum)
		{
			String CellData ="";
			int Colnum=-1;
			sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);
			for( int i=0; i<row.getLastCellNum();i++)
			{
				if(row.getCell(i).toString().equalsIgnoreCase(ColName))
				{
					Colnum = i;
					break;
				}
			}
		 
			 	if(Colnum ==-1)
			 	{
			 		return "";
			 	}
			 	else 
			 	{
				 	row = sheet.getRow(rownum-1);
				 	XSSFCell cell = row.getCell(Colnum);
			    
				 		if(cell.getCellType()==cell.CELL_TYPE_STRING)
				 			{
				 				return( cell.getStringCellValue());
				 			}
				
				 		else if(cell.getCellType()== cell.CELL_TYPE_NUMERIC)
				 			{
				 				return (String.valueOf(cell.getNumericCellValue()));
				 			}
				 		else
				 			{
				 				return (String.valueOf(cell.getBooleanCellValue()));
							}   
			    			
			 }
			
			 
		}
	
}
