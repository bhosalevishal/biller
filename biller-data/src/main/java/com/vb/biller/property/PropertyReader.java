package com.vb.biller.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {

	private Properties properties;
	private InputStream resourceStream;
	
	private String delimiter = ",";

	public PropertyReader(String propFileName) {
		try {
			properties = new Properties();
			resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
			properties.load(resourceStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readPrperty(String propKey) {
		return properties.getProperty(propKey);
	}
	
	public Collection<String> getPropValue(String propKey){
		Collection<String> props = new ArrayList<String>();
		String propValue = readPrperty(propKey);
		
		if(propValue.contains(delimiter)){
			String [] values = propValue.split(delimiter);
			for (String value : values) {
				props.add(value);
				
			}
		} else {
			props.add(propValue);
		}
		
		return props;
	}

}
