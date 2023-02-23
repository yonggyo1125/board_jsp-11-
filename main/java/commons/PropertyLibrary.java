package commons;

import java.util.ResourceBundle;

public class PropertyLibrary {
		
	public static String get(String key) {
		
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String value = bundle.getString(key);
		
		return value;
	}
}
