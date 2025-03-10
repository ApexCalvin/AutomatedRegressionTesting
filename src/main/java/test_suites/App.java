package test_suites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import tools.xlsxReader;

public class App {

	public static Properties properties = new Properties();
	
	public static ArrayList<HashMap<String, String>> allTestCases;
	
	public static String testResults_filePath;
	
	public static void loadTestCasesFromExternalExcelSheet() {
		
		try {
			// loading the properties into the app to be called later for variables
			properties.load(new FileInputStream("runtime.properties"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		allTestCases = xlsxReader.importDataFromSpreadsheet();
		
	}
}
