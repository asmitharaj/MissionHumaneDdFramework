package dataDrivenTestingOldFiles;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GetExcelData extends ReadExcelSheet{
	
	public XSSFSheet sheet;
	public static String filePath = "src/main/resources/MissionHumaneData.xlsx";
	public static Object[][] loginData;
	
	public Object[][]  getData(int columnCount) {
		sheet = DataSheet(filePath);
		int rowCount = sheet.getLastRowNum();
		
		loginData = new Object[rowCount][columnCount];
		for (int rCnt=1; rCnt<=rowCount;rCnt++){
            for (int cCnt=0; cCnt<columnCount;cCnt++){
            	loginData[rCnt-1][cCnt] = getCellData(0, rCnt, cCnt);
            }
        }

        return loginData;
	}
	
	 public String getCellData(int Sheet, int row, int col){
		 	XSSFCell atCell;
	        try {
	        	sheet = wb.getSheetAt(0);
	            XSSFRow atRow = sheet.getRow(row);
	            atCell = atRow.getCell(col);
	        }
            catch (Exception e) {
	            e.printStackTrace();
	            return "row " + row + " or column " + col+ " does not exist in xls";
            }
	        return String.valueOf(atCell.getStringCellValue()).toLowerCase();
	    }


}
