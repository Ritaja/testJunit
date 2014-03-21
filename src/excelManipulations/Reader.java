/**Reader Interface: template for the file reader class
 * Implemented by ReaderImpl class
 * 
 */
package excelManipulations;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author Shruthi Padma, Ritaja sengupta
 *
 */
public interface Reader {
	
	public int readData();
	public void loadData();
	public int excelSearch( String searchString); //searches for a string and returns the column number
    public String getNavigationType(String component);
}
