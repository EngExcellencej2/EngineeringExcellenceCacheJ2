package com.impetus.eej2.cache.factory;

import com.impetus.eej2.cache.dao.EIECacheDaoImpl;
import com.impetus.eej2.cache.dao.IEIECacheDao;

/**
 * A factory for creating DaoCreation objects.
 */
public class DaoCreationFactory {

	/**
	 * Gets the dao object.
	 *
	 * @param driverType the driver type
	 * @return the dao object
	 */
	public static IEIECacheDao getDaoObject(String driverType) {
		if ("Datastax".equals(driverType)) {
			return new EIECacheDaoImpl();
		}
		return null;
	}

}
