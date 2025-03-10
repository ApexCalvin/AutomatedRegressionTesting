package test_suites;

import java.util.ArrayList;
import java.util.HashMap;

import modules.LIB_Wires_MultiDebitWire;
import modules.LIB_Wires_SingleWire;
import modules.LIB_Wires_SingleWire_Interface;
import tools.myLogger;

public class TestSuite_Wires {

	public static void main(String[] args) {
		
		// start app, read cases from excel sheet, store in allTestCases
		App.loadTestCasesFromExternalExcelSheet();
		
		myLogger.createTestResultsTextFile();
		
		// run wire module of test cases associated with input variables
		wires_ModuleFilter(App.allTestCases);
	}

	public static void wires_ModuleFilter(ArrayList<HashMap<String, String>> allTestCases) {
		boolean testSelectedFlag = false;
		
		// iterate through each test case to be validated and ran
		for (HashMap<String, String> testCase : allTestCases) {
			
			// filter based on if the test should be executed and that it's also in the correct module
			if (testCase.get("Execution").equals("Y") && testCase.get("Module").equals("Single Wire")) {
				testSelectedFlag = true;
				LIB_Wires_SingleWire.run(testCase);
			}
			if (testCase.get("Execution").equals("Y") && testCase.get("Module").equals("Multi-Debit Wire")) {
				testSelectedFlag = true;
				LIB_Wires_MultiDebitWire.run(testCase);
			}
			if (testCase.get("Execution").equals("Y") && testCase.get("Module").contains("Interface")) {
				testSelectedFlag = true;
				LIB_Wires_SingleWire_Interface.run(testCase);
			}
		}
		
		// validates that tests exist
		if (!testSelectedFlag)
			System.out.println("[ERROR] No test cases have been selected for Wires Test Suite.");
	}

}
