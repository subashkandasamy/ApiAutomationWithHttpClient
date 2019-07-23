package LobsterApi.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excelutils {
	
	public static String TESTDATA_PATH = System.getProperty("user.dir") + "/src/main/java/LobsterApi/TestData/TestData.xlsx";

	static XSSFWorkbook book;
	static XSSFSheet sheet;

	/**
	 * @param sheetName
	 * @param DatavarietyID
	 * @return
	 */
	public static Object[][] getTestData(String sheetName, String DatavarietyID) {

		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_PATH);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			book = new XSSFWorkbook(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int M = sheet.getRow(0).getLastCellNum();
		int kl = sheet.getLastRowNum();
		int N = 0;
		int xy = 0;

		for (int x = 0; x < sheet.getLastRowNum(); x++) {

			if (sheet.getRow(x + 1).getCell(M - 1).toString() != null
					&& !sheet.getRow(x + 1).getCell(M - 1).toString().isEmpty()) {

				if ((sheet.getRow(x + 1).getCell(M - 1).toString()).equals(DatavarietyID)) {

					N = N + 1;

				}

			}

		}

		Object[][] data = new Object[N][M];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {

			if (sheet.getRow(i + 1).getCell(M - 1).toString() != null
					&& !sheet.getRow(i + 1).getCell(M - 1).toString().isEmpty()) {

				if ((sheet.getRow(i + 1).getCell(M - 1).toString()).equals(DatavarietyID)) {

					for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {

						data[xy][k] = sheet.getRow(i + 1).getCell(k).toString();

					}
					xy = xy + 1;

				}

			}
		}
		
		return data;

	}

}
