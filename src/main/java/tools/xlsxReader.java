package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsxReader {
	
	public static DataFormatter df = new DataFormatter();

	public static ArrayList<HashMap<String, String>> importDataFromSpreadsheet() {

		String spreadsheetFilePath = loadAndGetProperty("SPREADSHEET_FILE_PATH");

		ArrayList<HashMap<String, String>> mapped_Tests = null;

		try (FileInputStream fis = new FileInputStream(new File(spreadsheetFilePath));
				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			mapped_Tests = getSheetValues(workbook, "Tests");
			ArrayList<HashMap<String, String>> mapped_BusinessLineLogin = getSheetValues(workbook, "BusinessLineLogins");
			ArrayList<HashMap<String, String>> mapped_Wires_DataSets = getSheetValues(workbook, "Wires_DataSets");
			ArrayList<HashMap<String, String>> mapped_Transactions_DataSets = getSheetValues(workbook, "Transactions_DataSets");

			for (HashMap<String, String> testcase : mapped_Tests) {
				for (HashMap<String, String> login : mapped_BusinessLineLogin) {

					if (testcase.get("BusinessLine").equals(login.get("BusinessLine"))) {
						testcase.putAll(login);
					}
				}

				for (HashMap<String, String> dataset : mapped_Wires_DataSets) {
					if (testcase.get("DataSet").equals(dataset.get("DataSet")) && testcase.get("TestSuite").equals("Wires")) {
						testcase.putAll(dataset);
					}
				}

				for (HashMap<String, String> dataset : mapped_Transactions_DataSets) {
					if (testcase.get("DataSet").equals(dataset.get("DataSet")) && testcase.get("TestSuite").equals("Transactions")) {
						testcase.putAll(dataset);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapped_Tests;
	}

	public static ArrayList<HashMap<String, String>> getSheetValues(Workbook workbook, String sheetName) {

		Sheet sheet = workbook.getSheet(sheetName);

		ArrayList<String> columnNames = null;
		HashMap<String, String> mapped_columnName_rowValue = null;

		ArrayList<HashMap<String, String>> mapped_sheet = new ArrayList<HashMap<String, String>>();

		for (Row row : sheet) {
			if (row.equals(sheet.getRow(0))) {
				columnNames = getColumnNames(row);
			} else {
				mapped_columnName_rowValue = getCombinedColumnNamesAndValues(columnNames, row);
				mapped_sheet.add(mapped_columnName_rowValue);
			}
		}

		return mapped_sheet;
	}

	public static HashMap<String, String> getCombinedColumnNamesAndValues(ArrayList<String> columnNames, Row row) {

		HashMap<String, String> varsPerTestCase = new HashMap<String, String>();

		int i = 0;

		for (Cell cell : row) {
			varsPerTestCase.put(columnNames.get(i), df.formatCellValue(cell));
			i++;
		}

		return varsPerTestCase;
	}

	public static ArrayList<String> getColumnNames(Row firstRow) {

		ArrayList<String> columnNames = new ArrayList<String>();

		for (Cell cell : firstRow)
			columnNames.add(df.formatCellValue(cell));

		return columnNames;
	}

	public static String loadAndGetProperty(String var) {
		Properties properties = new Properties();
		
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