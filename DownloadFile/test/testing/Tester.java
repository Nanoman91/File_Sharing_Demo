package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Tester {

	public static void main(String[] args) {
//        String filePath = "D:\\Docs\\Local_Web_Share";
        
//        File downloadFile = new File(filePath);
//        long len = downloadFile.length();
//        System.out.println("File Len : " + len);
        
//        String[] pathArr = filePath.split("/");
        
        String newFilePath = "";
//        for (int i = 0; i < pathArr.length - 1; i++) {
//            newFilePath += pathArr[i] + "/";
//        }
//        System.out.println(newFilePath);
        
//        String f = filePath.replaceAll("\\\\", "/");
//        System.out.println(f);
        
        
    	Properties prop = new Properties();
    	InputStream input = null;
    	
    	try {

    		input = Tester.class.getResourceAsStream("share.properties");

    		// load a properties file
    		prop.load(input);

    		// get the property value and print it out
    		System.out.println(prop.getProperty("SHARE_DIR"));
  

    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}
}
