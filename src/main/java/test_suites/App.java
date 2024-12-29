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
	
	public static void startUp() {
		
		try {
			properties.load(new FileInputStream("runtime.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		allTestCases = xlsxReader.importDataFromSpreadsheet();
		
	}
}
