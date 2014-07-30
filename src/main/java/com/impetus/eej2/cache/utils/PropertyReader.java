package com.impetus.eej2.cache.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.impetus.eej2.cache.exception.EieCacheException;

/**
 * @author hitesh.pawar
 * <p>
 * Loads EIECache application configuration properties. The property configuration is loaded once in the application.
 * Once loaded by any thread, other threads can simply use previously loaded Properties.
 * </p>
 * @version 0.2
 */
 class PropertyReader {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
	
	//Class has got no instance methods so suppress instance creation by private Constructor
	private PropertyReader(){
		
	}
	
	private static Properties prop;
	
	/**
	 * <p>
	 * Load EIE Cache Configuration from properties file.This is one time operation.
	 * </p>
	 * @return
	 */
	public static synchronized Properties loadProperties() {
		if(prop!=null){
			logger.info("EIE configuration already loaded");
			return prop;
		}
		logger.info("Load EIE Cache Configuration from properties file");
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String filename = "ipconfig.properties";
			input = PropertyReader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				throw new EieCacheException("Not able to initialize input stream, "+filename+" not found");
			}
			prop.load(input);
			logger.debug("Configuration successfuly loaded from properties file::"+prop);
		} catch (IOException ex) {
			throw new EieCacheException("Exception while reading Property file:ipconfig.properties"+ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

}