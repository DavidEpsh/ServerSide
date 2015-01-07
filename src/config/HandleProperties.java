package config;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HandleProperties {
	private static final String FILE_NAME = "resources/Properties.xml";
	
	public static ServerProperties readProperties() {
		XMLDecoder decoder = null;
		
		try {
			decoder = new XMLDecoder(new FileInputStream(FILE_NAME));
			ServerProperties properties = (ServerProperties)decoder.readObject();
			return properties;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			decoder.close();
		}
		return null;
	}
}
