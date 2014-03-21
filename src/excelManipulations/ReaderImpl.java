package excelManipulations;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author nanophotometer, Ritaja sengupta
 *
 */
public class ReaderImpl implements Reader {
	
	private String filename;
	private String filepath;
	private XSSFSheet sheet;
	private FileInputStream file;
	//truth value
	protected int found;
	/**
	 * Default Constructor
	 * @param void
	 * @return void
	 * @throws IOException 
	 */
	public ReaderImpl() throws IOException
	{
		//dummy constructor create
		this.filename = "Reader_sample.xlsx";
		this.filepath = "resources/";
		this.filename = this.filepath + this.filename;
		this.found = 0;
		initialize();
	}
	/**
	 * Initializes the workbook to read from
	 * @param void
	 * @return void
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		// TODO Initialise the workbook to read from
		this.file = new FileInputStream(new File(this.filename));
		 
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        this.sheet = workbook.getSheetAt(0);
		
	}
	public ReaderImpl(String filename) throws IOException
	{
		this.filename = filename;
		this.filepath = "resources/";
		this.filename = this.filepath + this.filename;
		this.found = 0;
		initialize();
	}
	public ReaderImpl(String filename,String filepath)
	{
		//dummy constructor create
	}
	/**
	 * Read data from the file
	 * @Override
	 * @param void
	 * @return truth variable of type int 
	 */
	public int readData() {
		// TODO read data from excel
		
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        this.found = 0;
        while (rowIterator.hasNext()) 
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
             
            while (cellIterator.hasNext()) 
            {
            	Cell cell = cellIterator.next();
            	if(cell.toString().equalsIgnoreCase("login"))
            	{
            		System.out.println("Login found Executing AutoLogin");
            		this.found = 1;
            		break;
            	}
            	
            }
        }
		return this.found;   
		
	}

	/**
	 * Loads data that has been from the file
	 * @Override
	 * @param void
	 * @return void
	 */
	public void loadData() {
		// TODO clean and prepare data to be used
		
	}
	
	/**
	 * Closes the current open file
	 * @Override
	 * @param void
	 * @return void
	 * @throws IOException 
	 */
	public void closeFile() throws IOException
	{
		this.file.close();
	}
	
public int excelSearch(String searchString ){
		/* 
		 * This method searches for a string in an excel and returns the row number 
		 * Takes specific XSSFsheet and string to be searched as input
		 * If the String is found then it returns the row number. Row number of the first occurrence the string is returned 
		 * If the string is not found then -1 is returned
		 * The search is done only for the first column of the excel sheet.
		 * Case - Insensitive
		 * */
	sheet = this.sheet;
		Iterator<Row> rowIterator = sheet.iterator();
		 while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
		         
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		       
		             
		            Cell cell = cellIterator.next();
		             
		            
		                   
		                    
		           
		                   if (cell.getRichStringCellValue().getString().trim().equalsIgnoreCase(searchString)) {
		                    	 
		                        return row.getRowNum();  
		                    }
		        System.out.println("");
		    }
		return -1;
	}

public String getNavigationType( String component){
	sheet = this.sheet;
	int row_no  = excelSearch(component);
	Row row = sheet.getRow(row_no);
	 Cell cell= row.getCell(1);
	 return (cell.getStringCellValue());
	
	
	
}

public ArrayList getParams( String component){
	int no_of_cell = 1;
	sheet = this.sheet;
	ArrayList paramList = new ArrayList();
	int row_no  = excelSearch(component);
	Row row = sheet.getRow(row_no);
	no_of_cell = row.getLastCellNum();//returns total number of cells and counts from 1
	 // call counts from 0. First column is the name. so we skip the name field.
	// Only the parameters is added to the list
	Cell cell;
	for (int i=1; i < no_of_cell ;i++) { 
		cell = row.getCell(i);
		paramList.add(cell.getStringCellValue());
        
    }
	 
	 return paramList;
	
	
	
}
	
	//use setters ad getters to set the filename to read from
}
