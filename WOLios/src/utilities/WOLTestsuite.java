package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WOLTestsuite  {
    
	 HashMap<String, String> properties = new HashMap<String, String>();
	 Map<String, Object[]> testresultdata;
	  
	 public  static List<Testdata> dataLoad() throws Exception {
		  
		 WOLTestsuite wolTestsuite = new WOLTestsuite();
		 return wolTestsuite.readWOLExcel("//Users//deepti.pandey//Documents//Old-data//Selenium//TestData//WOLTestcases_12thAug.xlsx");
		  
	  }
	
	 public   List<Testdata> readWOLExcel(String fileName) throws Exception{   
    FileInputStream fstream = null;
    FileOutputStream ostream = null;
    XSSFCell cell1 = null;
    XSSFCell cell2 = null;
    List<Testdata> testDatas = new ArrayList<Testdata>();
     
    try{
        
    	 //File myFile = new File("//Users//deepti.pandey//Documents//Old-data//Selenium//TestData//WOLTestcases_12thAug.xlsx");
         fstream = new FileInputStream(fileName);
         DataInputStream in = new DataInputStream(fstream);
         BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
         XSSFWorkbook myWorkBook = new XSSFWorkbook (fstream);
         XSSFSheet mySheet = myWorkBook.getSheet("TestUsers");
        
         Iterator<Row> rowIterator = mySheet.iterator();
             
         while (rowIterator.hasNext()) {
        	 Row row = rowIterator.next();
        	 
        	 if (row.getRowNum()==0) {
        		 continue;  //just skip the rows if row number is 0
        	 }
             Iterator<Cell> cellIterator = row.cellIterator();
             while (cellIterator.hasNext()) {
                 properties.clear();
                 cell1 = (XSSFCell) cellIterator.next();
                 String key = cell1.getRichStringCellValue().toString();

                 Testdata testData = new Testdata();
                 if (!cellIterator.hasNext()) {

                     System.out.println("No Such Element");
                     String value = "";
                     properties.put(key, value);
                     testData.setUserId(key);
                     testData.setPassword(value);
                 } else {

                     cell2 = (XSSFCell) cellIterator.next();
                  //   System.out.println("Cell Two ... "                             + cell2.getRichStringCellValue());
                     String value = cell2.getRichStringCellValue()
                             .toString();
                     properties.put(key, value);
                     testData.setUserId(key);
                     testData.setPassword(value);
                     System.out.println(key + value);
                 }
                 testDatas.add(testData);
                 System.out.println("The properties are " + properties);

             }// inner while
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
   	 return testDatas;
   }
 }

/*  Row r = mySheet.getRow(2); // 10-1
if (r == null) {
   // First cell in the row, create
   r = mySheet.createRow(9);
}

Cell c = r.getCell(8); // 4-1
if (c == null) {
    // New cell
    c = r.createCell(3, Cell.CELL_TYPE_STRING);
}
c.setCellValue("Pass");

*/

/*ostream = new FileOutputStream(myFile);
myWorkBook.write(ostream);
ostream.close();*/
