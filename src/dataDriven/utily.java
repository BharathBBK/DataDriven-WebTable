package dataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class utily {

	static Workbook book;
    static Sheet sheet;
	@Test
    public static Object[][] getTestData() { 
	
	FileInputStream fil = null;
	
	try {
		fil = new FileInputStream("D:\\LoginData.xlsx");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	    try {
			book = WorkbookFactory.create(fil);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    
	    sheet = book.getSheetAt(0);
	    Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	    System.out.println(data.length);
	    
	    for (int i = 0;i<sheet.getLastRowNum();i++) {
	    	for (int j =0;j<sheet.getRow(0).getLastCellNum();j++) {
	    		
	    		String ret = sheet.getRow(i).getCell(j).getCellType().toString();
	    		//System.out.println(ret);
	    		if (ret.equals("STRING")) {
	    			data[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
	    			System.out.println(data[i][j]);
	    		
	    	}else {
	    		data[i][j]=sheet.getRow(i+1).getCell(j).getNumericCellValue();
	    		System.out.println(data[i][j]);
	    		}
	    	}
	    	
	    }
	    return data;
	}

}

