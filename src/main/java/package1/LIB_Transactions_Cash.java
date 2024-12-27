package package1;

import java.util.HashMap;

public class LIB_Transactions_Cash {
	
	public static void run(HashMap<String, String> testCase) {

		System.out.println("Starting Test: " + testCase.get("Description"));

		double amount = Double.valueOf(testCase.get("Amount").replace(",", ""));
		String businessLine = testCase.get("BusinessLine");

		System.out.println("This test is business line " + businessLine + " with the amount of $" + amount + ".");

		System.out.println("Ending Test: " + testCase.get("Description") + "\n");
	}
	
}
