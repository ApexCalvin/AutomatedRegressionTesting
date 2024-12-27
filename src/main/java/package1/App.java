package package1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.*; //Sheet, row, cell, STRING, NUMERIC, BOOLEAN
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {

	static Properties properties = new Properties();
	static ArrayList<HashMap<String, String>> allTests;
	private static DataFormatter dt = new DataFormatter();
	
	public static void importTestData() {
		allTests = getAllTestsFromSpreadsheet();
	}

	private static ArrayList<HashMap<String, String>> getAllTestsFromSpreadsheet() {

		String filePath = loadAndGetProperty("FILE_PATH");

		ArrayList<String> columnNames = null;

		HashMap<String, String> varsPerTestCase;

		ArrayList<HashMap<String, String>> allTests = new ArrayList<HashMap<String, String>>();

		try (FileInputStream fis = new FileInputStream(new File(filePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			Row firstRow = sheet.getRow(0);
			
			for (Row row : sheet) {
				if (row.equals(firstRow)) {
					columnNames = getColumnNames(row);
				} else {
					varsPerTestCase = getVarsPerTestCase(columnNames, row);
					allTests.add(varsPerTestCase);
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}

		return allTests;
	}

	private static HashMap<String, String> getVarsPerTestCase(ArrayList<String> columnNames, Row row) {

		HashMap<String, String> varsPerTestCase = new HashMap<String, String>();

		int i = 0;

		for (Cell cell : row) {
			varsPerTestCase.put(columnNames.get(i), dt.formatCellValue(cell));
			i++;
		}

		return varsPerTestCase;
	}

	private static ArrayList<String> getColumnNames(Row firstRow) {

		ArrayList<String> columnNames = new ArrayList<String>();

		for (Cell cell : firstRow)
			columnNames.add(dt.formatCellValue(cell));

		return columnNames;
	}

	public static String loadAndGetProperty(String var) {

		try {
			properties.load(new FileInputStream("runtime.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return properties.getProperty(var);
	}

}
