package com.impetus.eej2.cache.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.eej2.cache.exception.EieCacheCheckedException;
import com.impetus.eej2.cache.exception.EieCacheRunTimeException;


/**
 * <p>
 * Loads EIECache application configuration properties. The property configuration is loaded once in the application.
 * Once loaded by any thread, other threads can simply use previously loaded Properties.
 * </p>
 * @author hitesh.pawar
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
	 * Load EIE Cache Configuration from properties file.This is one time operation. In event of any <code>IOException</code>
	 * the exception is wrapped up in <code>EieCacheException</code> as there wont be any sense of taking process forward in event of such scenario
	 * </p>
	 * @return
	 * @throws EieCacheCheckedException 
	 */
	 static synchronized Properties loadProperties()  {
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
				throw new EieCacheRunTimeException("Not able to initialize input stream, "+filename+" not found");
			}
			prop.load(input);
			logger.debug("Configuration successfuly loaded from properties file::"+prop);
		} catch (IOException ex) {
			
			throw new EieCacheRunTimeException("Exception while reading Property file:ipconfig.properties"+ex);
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