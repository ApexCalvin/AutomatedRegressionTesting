package package1;

import java.util.HashMap;

public class TestSuite_Transactions {

	public static void main(String[] args) {
		App.importTestData();
		// System.out.println("Lets go!" + App.allTests);
		transactionsModuleFilter();
	}

	public static void transactionsModuleFilter() {
		boolean testSelectedFlag = false;
		for (HashMap<String, String> testCase : App.allTests) {
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
