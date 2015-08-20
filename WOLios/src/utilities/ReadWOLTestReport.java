package utilities;



import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWOLTestReport {
	
	
	  public static void main(String[] args) throws Exception{   
		    FileInputStream fstream = null;
		   
		     try{
		         File myFile = new File("//Users//deepti.pandey//Documents//Old-data//Selenium//TestData//WOLTestcases_12thAug.xlsx");
		         fstream = new FileInputStream(myFile);
		         DataInputStream in = new DataInputStream(fstream);
		         BufferedReader br = new BufferedReader(new InputStreamReader(in));
		        
		         XSSFWorkbook myWorkBook = new XSSFWorkbook (fstream);
		         XSSFSheet mySheet = myWorkBook.getSheet("TestReport");
		        
		         Iterator<Row> rowIterator = mySheet.iterator();
		         while (rowIterator.hasNext()) {
		             Row row = rowIterator.next();

		             // For each row, iterate through each columns
		             Iterator<Cell> cellIterator = row.cellIterator();
		             while (cellIterator.hasNext()) {

		                 Cell cell = cellIterator.next();

		                 switch (cell.getCellType()) {
		                 case Cell.CELL_TYPE_STRING:
		                     System.out.print(cell.getStringCellValue() + "\t");
		                     break;
		                 case Cell.CELL_TYPE_NUMERIC:
		                     System.out.print(cell.getNumericCellValue() + "\t");
		                     break;
		                 case Cell.CELL_TYPE_BOOLEAN:
		                     System.out.print(cell.getBooleanCellValue() + "\t");
		                     break;
		                 case Cell.CELL_TYPE_BLANK:
		                                 System.out.print( "\t");
		                                 break;
		                 default :
		                 }
		             }
		             System.out.println("");
		         }
		         Row r = mySheet.getRow(9); // 10-1
				    if (r == null) {
				       // First cell in the row, create
				       r = mySheet.createRow(9);
				    }

				    Cell c = r.getCell(3); // 4-1
				    if (c == null) {
				        // New cell
				        c = r.createCell(3, Cell.CELL_TYPE_NUMERIC);
				    }
				    c.setCellValue(100);

		   
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


		    


}
