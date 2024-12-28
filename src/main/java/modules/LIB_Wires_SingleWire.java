package modules;

import java.util.HashMap;

public class LIB_Wires_SingleWire {
	
	public static void run(HashMap<String, String> testCase) {

		System.out.println("Starting Test: " + testCase.get("Description"));

		double amount = Double.valueOf(testCase.get("Amount").replace(",", ""));
		String businessLine = testCase.get("BusinessLine");

		System.out.println("This test is business line " + businessLine + " with the amount of $" + amount + ".");

		if(testCase.get("Attachment").equals("Y")) {
			System.out.println("A document has been attached.");
		}
		
		System.out.println("Ending Test: " + testCase.get("Description") + "\n");
	}
}
