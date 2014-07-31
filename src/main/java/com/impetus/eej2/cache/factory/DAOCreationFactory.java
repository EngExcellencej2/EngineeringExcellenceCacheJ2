package com.impetus.eej2.cache.factory;

import com.impetus.eej2.cache.dao.EIECacheDataStaxDAOImpl;
import com.impetus.eej2.cache.dao.IEIECacheDAO;
import com.impetus.eej2.cache.utils.IEIECacheConstants;

/**
 * A factory for creating DaoCreation objects.
 */
public class DAOCreationFactory {

	/**
	 * Gets the dao object.
	 *
	 * @param driverType the driver type
	 * @return the dao object
	 */
	public static IEIECacheDAO getDaoObject(String driverType) {
		if (IEIECacheConstants.DATA_STAX_DRIVER.equals(driverType)) {
			return new EIECacheDataStaxDAOImpl();
		}
		return null;
	}

	/**
	 * private constructor
	 */
	private DAOCreationFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
