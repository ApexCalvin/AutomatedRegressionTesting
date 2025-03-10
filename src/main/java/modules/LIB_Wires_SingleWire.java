package modules;

import java.util.HashMap;

import pages.Page_SeleniumDemoUI;
import test_suites.App;
import tools.SeleniumSimplier;
import tools.myLogger;

public class LIB_Wires_SingleWire {

	public static void run(HashMap<String, String> testCase) {

		String start_msg = "Starting Test: " + testCase.get("Description");
		System.out.println(start_msg);
		myLogger.generateReport(App.testResults_filePath, start_msg);
		
		double amount = Double.valueOf(testCase.get("Amount").replace(",", ""));
		String businessLine = testCase.get("BusinessLine");

		System.out.println("This test is business line " + businessLine + " with the amount of $" + amount + ".");

		if (testCase.get("Attachment").equals("Y")) {
			System.out.println("A document has been attached.");
		}

		automatedUI_demo();

		String end_msg = "Ending Test: " + testCase.get("Description");
		myLogger.generateReport(App.testResults_filePath, end_msg);
		System.out.println(end_msg);
	}

	public static void automatedUI_demo() {

		SeleniumSimplier.launch(App.properties.getProperty("WEBSITE_1"));

		SeleniumSimplier.type_byId(Page_SeleniumDemoUI.textBox, "Hello World");
		
		SeleniumSimplier.type_byName(Page_SeleniumDemoUI.passwordBox, "Hello World");
		
		SeleniumSimplier.type_byName(Page_SeleniumDemoUI.textArea, "Hello World");

		// validate disabled input

		// validate read-only input

		SeleniumSimplier.select_fromDropDown_byName(Page_SeleniumDemoUI.dropDownSelect, "Two");
		
		try {
			SeleniumSimplier.select_fromDataList_byName(Page_SeleniumDemoUI.dropDownDataList, "Seattle");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String attachmentPath = System.getProperty("user.dir") + "\\attachment_test.txt";
		SeleniumSimplier.uploadfile_byCSS(Page_SeleniumDemoUI.uploadFile, attachmentPath);
		
		SeleniumSimplier.click_byId(Page_SeleniumDemoUI.checkBox);
		
		SeleniumSimplier.click_byId(Page_SeleniumDemoUI.radioButton);

		// color picker
		//SeleniumSimplier.
		
		// date picker
		//SeleniumSimplier.select_date(null, null);
		//select_currentDate(SeleniumWebForm.datePicker);
        //select_futureDate(SeleniumWebForm.datePicker, "24 Mar 2024");
		
		SeleniumSimplier.slideRange_byName_usingJavascript(Page_SeleniumDemoUI.rangeSlider, 3);
		
		System.out.println("-");
		
		//SeleniumSimplier.click_byClassName(Page_SeleniumDemoUI.submitButton);

        try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        SeleniumSimplier.closeBrowser();
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
}
