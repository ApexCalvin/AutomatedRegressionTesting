package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import test_suites.App;

public class myLogger {
	
	public static void main(String[] args) {
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = "Catan_Metro3";
        String filePath = "C:\\Users\\ApexC\\Desktop\\" + fileName + ".txt";
        String currentTest = "eight";
        generateReport(filePath, currentTest);
    }
	
	public static void createTestResultsTextFile() {
		
		String timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String fileName = timeStamp + ".txt";
		String testResults_FolderPath = App.properties.getProperty("TEST_RESULTS_PATH");		
		
		File file = new File(testResults_FolderPath, fileName);
		
		try {
			if(file.createNewFile()) {
				App.testResults_filePath = file.getAbsoluteFile().toString();
				System.out.println("File created: " + App.testResults_filePath);
			} else {
				System.out.println("File already exists: " + file.getAbsoluteFile());
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static void generateReport(String filePath, String currentTest) {
        try {
            ArrayList<String> completedTests = readFromFile(filePath);
            completedTests.add(currentTest);
            writeToFile(filePath, completedTests);
        } catch (Exception ignored) {

        }
    }

    public static ArrayList<String> readFromFile(String filePath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        ArrayList<String> completedTests = new ArrayList<String>();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            completedTests.add(line);
        }
        return completedTests;
    }

    public static void writeToFile(String filePath, ArrayList<String> completedTests) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (String s : completedTests) {
            fw.write(s+ "\n");
        }
        fw.close();
    }
}
