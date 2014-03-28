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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.rivetlogic.tout.NoSuchToutUserStatusException;
import com.rivetlogic.tout.model.ToutUserStatus;
import com.rivetlogic.tout.model.impl.ToutUserStatusImpl;
import com.rivetlogic.tout.model.impl.ToutUserStatusModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the Tout Portlet Status by User service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author ronny.vargas
 * @see ToutUserStatusPersistence
 * @see ToutUserStatusUtil
 * @generated
 */
public class ToutUserStatusPersistenceImpl extends BasePersistenceImpl<ToutUserStatus>
	implements ToutUserStatusPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ToutUserStatusUtil} to access the Tout Portlet Status by User persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ToutUserStatusImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
			ToutUserStatusModelImpl.FINDER_CACHE_ENABLED,
			ToutUserStatusImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
			ToutUserStatusModelImpl.FINDER_CACHE_ENABLED,
			ToutUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
			ToutUserStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ToutUserStatusPersistenceImpl() {
		setModelClass(ToutUserStatus.class);
	}

	/**
	 * Caches the Tout Portlet Status by User in the entity cache if it is enabled.
	 *
	 * @param toutUserStatus the Tout Portlet Status by User
	 */
	@Override
	public void cacheResult(ToutUserStatus toutUserStatus) {
		EntityCacheUtil.putResult(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
			ToutUserStatusImpl.class, toutUserStatus.getPrimaryKey(),
			toutUserStatus);

		toutUserStatus.resetOriginalValues();
	}

	/**
	 * Caches the Tout Portlet Status by Users in the entity cache if it is enabled.
	 *
	 * @param toutUserStatuses the Tout Portlet Status by Users
	 */
	@Override
	public void cacheResult(List<ToutUserStatus> toutUserStatuses) {
		for (ToutUserStatus toutUserStatus : toutUserStatuses) {
			if (EntityCacheUtil.getResult(
						ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
						ToutUserStatusImpl.class, toutUserStatus.getPrimaryKey()) == null) {
				cacheResult(toutUserStatus);
			}
			else {
				toutUserStatus.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all Tout Portlet Status by Users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ToutUserStatusImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ToutUserStatusImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the Tout Portlet Status by User.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ToutUserStatus toutUserStatus) {
		EntityCacheUtil.removeResult(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
			ToutUserStatusImpl.class, toutUserStatus.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ToutUserStatus> toutUserStatuses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ToutUserStatus toutUserStatus : toutUserStatuses) {
			EntityCacheUtil.removeResult(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
				ToutUserStatusImpl.class, toutUserStatus.getPrimaryKey());
		}
	}

	/**
	 * Creates a new Tout Portlet Status by User with the primary key. Does not add the Tout Portlet Status by User to the database.
	 *
	 * @param userId the primary key for the new Tout Portlet Status by User
	 * @return the new Tout Portlet Status by User
	 */
	@Override
	public ToutUserStatus create(long userId) {
		ToutUserStatus toutUserStatus = new ToutUserStatusImpl();

		toutUserStatus.setNew(true);
		toutUserStatus.setPrimaryKey(userId);

		return toutUserStatus;
	}

	/**
	 * Removes the Tout Portlet Status by User with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the Tout Portlet Status by User
	 * @return the Tout Portlet Status by User that was removed
	 * @throws com.rivetlogic.tout.NoSuchToutUserStatusException if a Tout Portlet Status by User with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ToutUserStatus remove(long userId)
		throws NoSuchToutUserStatusException, SystemException {
		return remove((Serializable)userId);
	}

	/**
	 * Removes the Tout Portlet Status by User with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the Tout Portlet Status by User
	 * @return the Tout Portlet Status by User that was removed
	 * @throws com.rivetlogic.tout.NoSuchToutUserStatusException if a Tout Portlet Status by User with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ToutUserStatus remove(Serializable primaryKey)
		throws NoSuchToutUserStatusException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ToutUserStatus toutUserStatus = (ToutUserStatus)session.get(ToutUserStatusImpl.class,
					primaryKey);

			if (toutUserStatus == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchToutUserStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(toutUserStatus);
		}
		catch (NoSuchToutUserStatusException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ToutUserStatus removeImpl(ToutUserStatus toutUserStatus)
		throws SystemException {
		toutUserStatus = toUnwrappedModel(toutUserStatus);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(toutUserStatus)) {
				toutUserStatus = (ToutUserStatus)session.get(ToutUserStatusImpl.class,
						toutUserStatus.getPrimaryKeyObj());
			}

			if (toutUserStatus != null) {
				session.delete(toutUserStatus);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (toutUserStatus != null) {
			clearCache(toutUserStatus);
		}

		return toutUserStatus;
	}

	@Override
	public ToutUserStatus updateImpl(
		com.rivetlogic.tout.model.ToutUserStatus toutUserStatus)
		throws SystemException {
		toutUserStatus = toUnwrappedModel(toutUserStatus);

		boolean isNew = toutUserStatus.isNew();

		Session session = null;

		try {
			session = openSession();

			if (toutUserStatus.isNew()) {
				session.save(toutUserStatus);

				toutUserStatus.setNew(false);
			}
			else {
				session.merge(toutUserStatus);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
			ToutUserStatusImpl.class, toutUserStatus.getPrimaryKey(),
			toutUserStatus);

		return toutUserStatus;
	}

	protected ToutUserStatus toUnwrappedModel(ToutUserStatus toutUserStatus) {
		if (toutUserStatus instanceof ToutUserStatusImpl) {
			return toutUserStatus;
		}

		ToutUserStatusImpl toutUserStatusImpl = new ToutUserStatusImpl();

		toutUserStatusImpl.setNew(toutUserStatus.isNew());
		toutUserStatusImpl.setPrimaryKey(toutUserStatus.getPrimaryKey());

		toutUserStatusImpl.setUserId(toutUserStatus.getUserId());
		toutUserStatusImpl.setArticleId(toutUserStatus.getArticleId());
		toutUserStatusImpl.setToutDismissed(toutUserStatus.isToutDismissed());
		toutUserStatusImpl.setToutSeen(toutUserStatus.isToutSeen());
		toutUserStatusImpl.setReminderDate(toutUserStatus.getReminderDate());

		return toutUserStatusImpl;
	}

	/**
	 * Returns the Tout Portlet Status by User with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the Tout Portlet Status by User
	 * @return the Tout Portlet Status by User
	 * @throws com.rivetlogic.tout.NoSuchToutUserStatusException if a Tout Portlet Status by User with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ToutUserStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchToutUserStatusException, SystemException {
		ToutUserStatus toutUserStatus = fetchByPrimaryKey(primaryKey);

		if (toutUserStatus == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchToutUserStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return toutUserStatus;
	}

	/**
	 * Returns the Tout Portlet Status by User with the primary key or throws a {@link com.rivetlogic.tout.NoSuchToutUserStatusException} if it could not be found.
	 *
	 * @param userId the primary key of the Tout Portlet Status by User
	 * @return the Tout Portlet Status by User
	 * @throws com.rivetlogic.tout.NoSuchToutUserStatusException if a Tout Portlet Status by User with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ToutUserStatus findByPrimaryKey(long userId)
		throws NoSuchToutUserStatusException, SystemException {
		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the Tout Portlet Status by User with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the Tout Portlet Status by User
	 * @return the Tout Portlet Status by User, or <code>null</code> if a Tout Portlet Status by User with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ToutUserStatus fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ToutUserStatus toutUserStatus = (ToutUserStatus)EntityCacheUtil.getResult(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
				ToutUserStatusImpl.class, primaryKey);

		if (toutUserStatus == _nullToutUserStatus) {
			return null;
		}

		if (toutUserStatus == null) {
			Session session = null;

			try {
				session = openSession();

				toutUserStatus = (ToutUserStatus)session.get(ToutUserStatusImpl.class,
						primaryKey);

				if (toutUserStatus != null) {
					cacheResult(toutUserStatus);
				}
				else {
					EntityCacheUtil.putResult(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
						ToutUserStatusImpl.class, primaryKey,
						_nullToutUserStatus);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ToutUserStatusModelImpl.ENTITY_CACHE_ENABLED,
					ToutUserStatusImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return toutUserStatus;
	}

	/**
	 * Returns the Tout Portlet Status by User with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the Tout Portlet Status by User
	 * @return the Tout Portlet Status by User, or <code>null</code> if a Tout Portlet Status by User with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ToutUserStatus fetchByPrimaryKey(long userId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns all the Tout Portlet Status by Users.
	 *
	 * @return the Tout Portlet Status by Users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ToutUserStatus> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ToutUserStatus> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<ToutUserStatus> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ToutUserStatus> list = (List<ToutUserStatus>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TOUTUSERSTATUS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TOUTUSERSTATUS;

				if (pagination) {
					sql = sql.concat(ToutUserStatusModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ToutUserStatus>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ToutUserStatus>(list);
				}
				else {
					list = (List<ToutUserStatus>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the Tout Portlet Status by Users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ToutUserStatus toutUserStatus : findAll()) {
			remove(toutUserStatus);
		}
	}

	/**
	 * Returns the number of Tout Portlet Status by Users.
	 *
	 * @return the number of Tout Portlet Status by Users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TOUTUSERSTATUS);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the Tout Portlet Status by User persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.rivetlogic.tout.model.ToutUserStatus")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ToutUserStatus>> listenersList = new ArrayList<ModelListener<ToutUserStatus>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ToutUserStatus>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ToutUserStatusImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_TOUTUSERSTATUS = "SELECT toutUserStatus FROM ToutUserStatus toutUserStatus";
	private static final String _SQL_COUNT_TOUTUSERSTATUS = "SELECT COUNT(toutUserStatus) FROM ToutUserStatus toutUserStatus";
	private static final String _ORDER_BY_ENTITY_ALIAS = "toutUserStatus.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ToutUserStatus exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ToutUserStatusPersistenceImpl.class);
	private static ToutUserStatus _nullToutUserStatus = new ToutUserStatusImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ToutUserStatus> toCacheModel() {
				return _nullToutUserStatusCacheModel;
			}
		};

	private static CacheModel<ToutUserStatus> _nullToutUserStatusCacheModel = new CacheModel<ToutUserStatus>() {
			@Override
			public ToutUserStatus toEntityModel() {
				return _nullToutUserStatus;
			}
		};
}