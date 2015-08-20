package utilities.functionLibrary;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


	
public class ReadExcel {
	
	public static void main ( String[] args) throws Exception {

		String filePath = "//Users//deepti.pandey//Documents//SeleniumUsers.xls";
		File excel = new File(filePath);
		FileInputStream fs = new FileInputStream (excel); 
		HSSFWorkbook wb =new HSSFWorkbook(fs);
		HSSFSheet ws = wb.getSheet("Credentials") ;
	
		int rowNum = ws.getLastRowNum() +1;
		int colNum = ws.getRow(0).getLastCellNum();
		String[][] data = new String[rowNum][colNum];
		
	
		for (int i=0;i<rowNum;i++){
			
		           HSSFRow row =ws.getRow(i);
		           
		           for (int j=0;j< colNum;j++) {
			         
		        	   HSSFCell cell = row.getCell(j);
			           String value =cellToString(cell);
			           data[i][j]=value;
			           System.out.println("the value is " + value);
		           }
			
		}
	
	//wb.close();	
	}//main
	
	
		
		public static String cellToString( HSSFCell cell) {
			
			int type;
			Object result;
			type = cell.getCellType();
			
			switch (type) {
			
			case 0: // numeric value in Excel
				result = cell.getNumericCellValue();
				break;
				
			case 1:
				result = cell.getStringCellValue();
				break;
				default:
					throw new RuntimeException ("there are no support for this type of cell");
				
			}//switch closed
		
			return result.toString();
		} //celltostring fn closed
				
		
}//class