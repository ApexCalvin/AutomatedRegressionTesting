package package1;

import java.util.ArrayList;
import java.util.HashMap;

public class TestSuite_Wires {

	public static void main(String[] args) {
		wiresModuleFilter(App.importTestData());
	}

	public static void wiresModuleFilter(ArrayList<HashMap<String, String>> allTestCases) {
		boolean testSelectedFlag = false;
		for (HashMap<String, String> testCase : allTestCases) {
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
		if (!testSelectedFlag)
			System.out.println("[ERROR] No test cases have been selected for Wires Test Suite.");
	}

}
