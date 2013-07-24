package no.plasmid.movingguy;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	
	/**
	 * The static instance of the Configuration class
	 */
	private static Configuration instance = new Configuration();
	
	/**
	 * @return the instance of the Configuration class
	 */
	public static Configuration getInstance() {
		return instance;
	}
	
	/**
	 * Map for holding the configuration parameters
	 */
	private Map<String, Object> configurationParameters;
	
	/**
	 * Private constructor
	 */
	private Configuration() {
		configurationParameters = new HashMap<String, Object>();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(String name) {
		return (T)configurationParameters.get(name);
	}
		
	public void setValue(String name, Object value) {
		configurationParameters.put(name, value);
	}
	
	//Display configuration
	public static final int WINDOW_WIDTH		= 900;
	public static final int WINDOW_HEIGTH		= 900;

	//Mouse configuration
	public static final int MAX_CLICK_TIME				= 150;	//The maximum time a mouse button can be held before a click becomes a drag
	public static final int DOUBLE_CLICK_INTERVALL_TIME	= 250;	//The maximum time between two mouse clicks that shall be interpreted as a double click
}
