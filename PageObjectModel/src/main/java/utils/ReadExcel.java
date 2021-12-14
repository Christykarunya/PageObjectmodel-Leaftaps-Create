package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readData(String fileName) throws IOException {
		//setup the path for WorkBook .xlsx
		XSSFWorkbook wb= new XSSFWorkbook("./data/"+fileName+".xlsx");
		//to get into WorkSheet
		XSSFSheet ws= wb.getSheet("Sheet1");
		//to get into the  row
		int rowCount = ws.getLastRowNum();
		//to get into the cell
		int cellCount=ws.getRow(0).getLastCellNum();
		//to read data from the cell
		String[][] data=new String[rowCount][cellCount];
		for (int i=1;i<= rowCount; i++) {
			for(int j=0;j<cellCount;j++) {
				String text=ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(text);
				
				data[i-1][j]=text;		
			}
		}
		//close the WorkBook
		wb.close();
		return data;
	}

}
