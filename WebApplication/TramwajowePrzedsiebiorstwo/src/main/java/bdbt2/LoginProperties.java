package bdbt2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class LoginProperties {
	
	public static void write(Properties prop, String key, String value, String permissions) {
        try (OutputStream output = new FileOutputStream("src/main/resources/dane.properties")) {

        	value= value+ " "+permissions;
            // set the properties value
            prop.setProperty(key, value);


            // save properties to project root folder
            prop.store(output, null);


        } catch (IOException io) {
            io.printStackTrace();
        }
	}
	
	
	public static Properties load() {
        Properties prop = new Properties();
		 try (InputStream input = new FileInputStream("src/main/resources/dane.properties")) {

	            // load a properties file
	            prop.load(input);
	   
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }finally{return prop;}
	}
	

}
