package utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class Util {
	
	public static String getShareDir() {
		System.out.println("Loading Properties.....");
		String shareDir = "";
		Properties prop = new Properties();
		InputStream input = null;
    	try {

        	input = Util.class.getResourceAsStream("share.properties");

    		// load a properties file
    		prop.load(input);

    		shareDir = new File(prop.getProperty("SHARE_DIR")).toString();

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
    	return shareDir;
	}

	public static String getURLWithContextPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}
	
	public static String removeLastSegment(String path) {
		path = path.replaceAll("\\\\", "/");
		String[] pathArr = path.split("/");
		String newPath = "";
		for (int i = 0; i < pathArr.length - 1; i++) {
			newPath += pathArr[i] + "/";
		}
		return new File(newPath).toString();

	}
}
