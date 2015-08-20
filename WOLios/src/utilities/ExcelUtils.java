package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {


	public static final List<UserDetails> USER_DETAILS=new ArrayList<UserDetails>();

	public static void initializeExcelFile() throws Exception{

		initializeTestCaseData();
		initializeUserData();

	}

	private static void  initializeUserData() throws Exception {

		//read excel file and set data to USER_DETAILS list
		FileInputStream fstream = null;
		FileOutputStream ostream = null;
		XSSFCell cell1 = null;
		XSSFCell cell2 = null;
		File fileName = new File("//Users//deepti.pandey//Documents//Old-data//Selenium//TestData//WOLTestcases_12thAug.xlsx");

		try{

			fstream = new FileInputStream(fileName);
			XSSFWorkbook myWorkBook = new XSSFWorkbook (fstream);
			XSSFSheet mySheet = myWorkBook.getSheet("TestUsers");
			Iterator<Row> rowIterator = mySheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				if (row.getRowNum()==0) {
					continue;  //just skip the rows if row number is 0
				}
				Iterator<Cell> cellIterator = row.cellIterator();

				int cellCounter=0;
				UserDetails userDetails =new UserDetails();
				while (cellIterator.hasNext()) {
					//properties.clear();
					cell1 = (XSSFCell) cellIterator.next();
					String data = cell1.getRichStringCellValue().toString();

					switch (cellCounter) {
					case 0:
						userDetails.setUserName(data);
						break;
					case 1:
						userDetails.setPassword(data);
						break;
					case 2:
						userDetails.setRoleName(data);
						break;
					}

				}// inner while
				USER_DETAILS.add(userDetails);
				
			}// outer while

		} catch (FileNotFoundException ex){
			ex.printStackTrace();
			// Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				// If you don't need the stream open after the constructor
				// else, remove that block but don't forget to close the
				// stream after you are done with it
				fstream.close();
			} catch (IOException ex) {
				//   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
			}
		} 
		
	}








private  static void initializeTestCaseData(){

	//read excel file related to test case  and initlize into different class which will be like userdetail

}

public void writeExcelFile(){
	
}


	private  final static String getDateTime()
    {
        DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss");
        df.setTimeZone(TimeZone.getTimeZone("IST"));
        return df.format(new Date());
    }

	
}
