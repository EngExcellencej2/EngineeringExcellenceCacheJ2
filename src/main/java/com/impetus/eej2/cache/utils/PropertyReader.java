package com.impetus.eej2.cache.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.eej2.cache.exception.EIECacheCheckedException;
import com.impetus.eej2.cache.exception.EIECacheRunTimeException;

/**
 * <p>
 * Loads EIECache application configuration properties. The property
 * configuration is loaded once in the application. Once loaded by any thread,
 * other threads can simply use previously loaded Properties.
 * </p>
 * 
 * @author hitesh.pawar
 * @version 0.2
 */
class PropertyReader {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PropertyReader.class);

	// Class has got no instance methods so suppress instance creation by
	// private Constructor
	private PropertyReader() {

	}

	private static Properties prop;

	/**
	 * <p>
	 * Load EIE Cache Configuration from properties file.This is one time
	 * operation. In event of any <code>IOException</code> the exception is
	 * wrapped up in <code>EieCacheException</code> as there wont be any sense
	 * of taking process forward in event of such scenario
	 * </p>
	 * 
	 * @return
	 * @throws EIECacheCheckedException
	 */
	static synchronized Properties loadProperties() {
		if (prop != null) {
			LOGGER.info("EIE configuration already loaded");
			return prop;
		}
		LOGGER.info("Load EIE Cache Configuration from properties file");
		prop = new Properties();
		InputStream input = null;
		try {
			String filename = "ipconfig.properties";
			input = PropertyReader.class.getClassLoader().getResourceAsStream(
					filename);
			if (input == null) {
				throw new EIECacheRunTimeException(
						"Not able to initialize input stream, " + filename
								+ " not found");
			}
			prop.load(input);
			LOGGER.debug("Configuration successfuly loaded from properties file::"
					+ prop);
		} catch (IOException ioException) {
			LOGGER.error("Exception while reading Property file:ipconfig.properties",ioException);
			throw new EIECacheRunTimeException(
					"Exception while reading Property file:ipconfig.properties"
							+ ioException);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ioException) {
					LOGGER.error("Exception while closing the resources",ioException);
				}
			}
		}
		return prop;
	}

}