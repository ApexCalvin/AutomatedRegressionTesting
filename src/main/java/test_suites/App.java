package test_suites;

import java.util.ArrayList;
import java.util.HashMap;

import tools.xlsxReader;

public class App {

	public static ArrayList<HashMap<String, String>> allTestCases;
	
	public static void startUp() {
		allTestCases = xlsxReader.importDataFromSpreadsheet();
	}

}
