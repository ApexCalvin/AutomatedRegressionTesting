package test_suites;

import java.util.ArrayList;
import java.util.HashMap;

import modules.LIB_Transactions_ACH;
import modules.LIB_Transactions_Cash;
import modules.LIB_Transactions_Check;
import modules.LIB_Transactions_Transfer;

public class TestSuite_Transactions {

	public static void main(String[] args) {
		App.loadTestCasesFromExternalExcelSheet();
		transactions_ModuleFilter(App.allTestCases);
	}

	public static void transactions_ModuleFilter(ArrayList<HashMap<String, String>> allTestCases) {
		boolean testSelectedFlag = false;
		for (HashMap<String, String> testCase : allTestCases) {
			if (testCase.get("Execution").equals("Y") && testCase.get("Module").equals("ACH")) {
				testSelectedFlag = true;
				LIB_Transactions_ACH.run(testCase);
			}
			if (testCase.get("Execution").equals("Y") && testCase.get("Module").equals("Cash")) {
				testSelectedFlag = true;
				LIB_Transactions_Cash.run(testCase);
			}
			if (testCase.get("Execution").equals("Y") && testCase.get("Module").equals("Check")) {
				testSelectedFlag = true;
				LIB_Transactions_Check.run(testCase);
			}
			if (testCase.get("Execution").equals("Y") && testCase.get("Module").equals("Transfer")) {
				testSelectedFlag = true;
				LIB_Transactions_Transfer.run(testCase);
			}
		}
		if (!testSelectedFlag)
			System.out.println("[ERROR] No test cases have been selected for Transactions Test Suite.");
	}

}
