package utility;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static XSSFWorkbook workBook;
	static XSSFSheet sheet;
	static String projectPath = System.getProperty("user.dir");
	static String userName = null;
	static String password = null;

	public static void getRowCount() {
		try {
			workBook = new XSSFWorkbook(projectPath + "\\data_driven\\data.xlsx");
			sheet = workBook.getSheet("Sheet1");
			int rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println(rowCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	public static String getUserName() {
		try {
			workBook = new XSSFWorkbook(projectPath + "\\data_driven\\data.xlsx");
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			userName = sheet.getRow(1).getCell(0).getStringCellValue();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return userName;
	}

	public static String getPassword() {
		try {
			workBook = new XSSFWorkbook(projectPath + "\\data_driven\\data.xlsx");
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			password = sheet.getRow(1).getCell(1).getStringCellValue();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return password;
	}
}
