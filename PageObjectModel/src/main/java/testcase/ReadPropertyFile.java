package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	public static void main(String[] args) throws IOException {
		
		// Class --> Properties
		Properties prop = new Properties();
		
		// Load the given property file
		File file = new File("src/main/resources/en.properties");
		FileInputStream fs = new FileInputStream(file);
		prop.load(fs);
		
		// Read the property
		String val = prop.getProperty("CreateLead_CreateLeadButton");
		System.out.println(val);

	}

}
