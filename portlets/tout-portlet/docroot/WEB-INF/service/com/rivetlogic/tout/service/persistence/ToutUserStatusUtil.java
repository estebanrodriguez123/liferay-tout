/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.rivetlogic.tout.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.rivetlogic.tout.model.ToutUserStatus;

import java.util.List;

/**
 * The persistence utility for the Tout Portlet Status by User service. This utility wraps {@link ToutUserStatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author ronny.vargas
 * @see ToutUserStatusPersistence
 * @see ToutUserStatusPersistenceImpl
 * @generated
 */
public class ToutUserStatusUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ToutUserStatus toutUserStatus) {
		getPersistence().clearCache(toutUserStatus);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ToutUserStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ToutUserStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ToutUserStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ToutUserStatus update(ToutUserStatus toutUserStatus)
		throws SystemException {
		return getPersistence().update(toutUserStatus);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ToutUserStatus update(ToutUserStatus toutUserStatus,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(toutUserStatus, serviceContext);
	}

	/**
	* Caches the Tout Portlet Status by User in the entity cache if it is enabled.
	*
	* @param toutUserStatus the Tout Portlet Status by User
	*/
	public static void cacheResult(
		com.rivetlogic.tout.model.ToutUserStatus toutUserStatus) {
		getPersistence().cacheResult(toutUserStatus);
	}

	/**
	* Caches the Tout Portlet Status by Users in the entity cache if it is enabled.
	*
	* @param toutUserStatuses the Tout Portlet Status by Users
	*/
	public static void cacheResult(
		java.util.List<com.rivetlogic.tout.model.ToutUserStatus> toutUserStatuses) {
		getPersistence().cacheResult(toutUserStatuses);
	}

	/**
	* Creates a new Tout Portlet Status by User with the primary key. Does not add the Tout Portlet Status by User to the database.
	*
	* @param userId the primary key for the new Tout Portlet Status by User
	* @return the new Tout Portlet Status by User
	*/
	public static com.rivetlogic.tout.model.ToutUserStatus create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	* Removes the Tout Portlet Status by User with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the Tout Portlet Status by User
	* @return the Tout Portlet Status by User that was removed
	* @throws com.rivetlogic.tout.NoSuchToutUserStatusException if a Tout Portlet Status by User with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.tout.model.ToutUserStatus remove(long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.tout.NoSuchToutUserStatusException {
		return getPersistence().remove(userId);
	}

	public static com.rivetlogic.tout.model.ToutUserStatus updateImpl(
		com.rivetlogic.tout.model.ToutUserStatus toutUserStatus)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(toutUserStatus);
	}

	/**
	* Returns the Tout Portlet Status by User with the primary key or throws a {@link com.rivetlogic.tout.NoSuchToutUserStatusException} if it could not be found.
	*
	* @param userId the primary key of the Tout Portlet Status by User
	* @return the Tout Portlet Status by User
	* @throws com.rivetlogic.tout.NoSuchToutUserStatusException if a Tout Portlet Status by User with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.tout.model.ToutUserStatus findByPrimaryKey(
		long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.rivetlogic.tout.NoSuchToutUserStatusException {
		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	* Returns the Tout Portlet Status by User with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userId the primary key of the Tout Portlet Status by User
	* @return the Tout Portlet Status by User, or <code>null</code> if a Tout Portlet Status by User with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.rivetlogic.tout.model.ToutUserStatus fetchByPrimaryKey(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	/**
	* Returns all the Tout Portlet Status by Users.
	*
	* @return the Tout Portlet Status by Users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.rivetlogic.tout.model.ToutUserStatus> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the Tout Portlet Status by Users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.tout.model.impl.ToutUserStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of Tout Portlet Status by Users
	* @param end the upper bound of the range of Tout Portlet Status by Users (not inclusive)
	* @return the range of Tout Portlet Status by Users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.rivetlogic.tout.model.ToutUserStatus> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the Tout Portlet Status by Users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.tout.model.impl.ToutUserStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of Tout Portlet Status by Users
	* @param end the upper bound of the range of Tout Portlet Status by Users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of Tout Portlet Status by Users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.rivetlogic.tout.model.ToutUserStatus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the Tout Portlet Status by Users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of Tout Portlet Status by Users.
	*
	* @return the number of Tout Portlet Status by Users
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ToutUserStatusPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ToutUserStatusPersistence)PortletBeanLocatorUtil.locate(com.rivetlogic.tout.service.ClpSerializer.getServletContextName(),
					ToutUserStatusPersistence.class.getName());

			ReferenceRegistry.registerReference(ToutUserStatusUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ToutUserStatusPersistence persistence) {
	}

	private static ToutUserStatusPersistence _persistence;
}