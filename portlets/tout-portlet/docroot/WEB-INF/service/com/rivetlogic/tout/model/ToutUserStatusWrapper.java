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

package com.rivetlogic.tout.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ToutUserStatus}.
 * </p>
 *
 * @author ronny.vargas
 * @see ToutUserStatus
 * @generated
 */
public class ToutUserStatusWrapper implements ToutUserStatus,
	ModelWrapper<ToutUserStatus> {
	public ToutUserStatusWrapper(ToutUserStatus toutUserStatus) {
		_toutUserStatus = toutUserStatus;
	}

	@Override
	public Class<?> getModelClass() {
		return ToutUserStatus.class;
	}

	@Override
	public String getModelClassName() {
		return ToutUserStatus.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("articleId", getArticleId());
		attributes.put("toutDismissed", getToutDismissed());
		attributes.put("toutSeen", getToutSeen());
		attributes.put("reminderDate", getReminderDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long articleId = (Long)attributes.get("articleId");

		if (articleId != null) {
			setArticleId(articleId);
		}

		Boolean toutDismissed = (Boolean)attributes.get("toutDismissed");

		if (toutDismissed != null) {
			setToutDismissed(toutDismissed);
		}

		Boolean toutSeen = (Boolean)attributes.get("toutSeen");

		if (toutSeen != null) {
			setToutSeen(toutSeen);
		}

		Date reminderDate = (Date)attributes.get("reminderDate");

		if (reminderDate != null) {
			setReminderDate(reminderDate);
		}
	}

	/**
	* Returns the primary key of this Tout Portlet Status by User.
	*
	* @return the primary key of this Tout Portlet Status by User
	*/
	@Override
	public long getPrimaryKey() {
		return _toutUserStatus.getPrimaryKey();
	}

	/**
	* Sets the primary key of this Tout Portlet Status by User.
	*
	* @param primaryKey the primary key of this Tout Portlet Status by User
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_toutUserStatus.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user ID of this Tout Portlet Status by User.
	*
	* @return the user ID of this Tout Portlet Status by User
	*/
	@Override
	public long getUserId() {
		return _toutUserStatus.getUserId();
	}

	/**
	* Sets the user ID of this Tout Portlet Status by User.
	*
	* @param userId the user ID of this Tout Portlet Status by User
	*/
	@Override
	public void setUserId(long userId) {
		_toutUserStatus.setUserId(userId);
	}

	/**
	* Returns the user uuid of this Tout Portlet Status by User.
	*
	* @return the user uuid of this Tout Portlet Status by User
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _toutUserStatus.getUserUuid();
	}

	/**
	* Sets the user uuid of this Tout Portlet Status by User.
	*
	* @param userUuid the user uuid of this Tout Portlet Status by User
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_toutUserStatus.setUserUuid(userUuid);
	}

	/**
	* Returns the article ID of this Tout Portlet Status by User.
	*
	* @return the article ID of this Tout Portlet Status by User
	*/
	@Override
	public long getArticleId() {
		return _toutUserStatus.getArticleId();
	}

	/**
	* Sets the article ID of this Tout Portlet Status by User.
	*
	* @param articleId the article ID of this Tout Portlet Status by User
	*/
	@Override
	public void setArticleId(long articleId) {
		_toutUserStatus.setArticleId(articleId);
	}

	/**
	* Returns the tout dismissed of this Tout Portlet Status by User.
	*
	* @return the tout dismissed of this Tout Portlet Status by User
	*/
	@Override
	public boolean getToutDismissed() {
		return _toutUserStatus.getToutDismissed();
	}

	/**
	* Returns <code>true</code> if this Tout Portlet Status by User is tout dismissed.
	*
	* @return <code>true</code> if this Tout Portlet Status by User is tout dismissed; <code>false</code> otherwise
	*/
	@Override
	public boolean isToutDismissed() {
		return _toutUserStatus.isToutDismissed();
	}

	/**
	* Sets whether this Tout Portlet Status by User is tout dismissed.
	*
	* @param toutDismissed the tout dismissed of this Tout Portlet Status by User
	*/
	@Override
	public void setToutDismissed(boolean toutDismissed) {
		_toutUserStatus.setToutDismissed(toutDismissed);
	}

	/**
	* Returns the tout seen of this Tout Portlet Status by User.
	*
	* @return the tout seen of this Tout Portlet Status by User
	*/
	@Override
	public boolean getToutSeen() {
		return _toutUserStatus.getToutSeen();
	}

	/**
	* Returns <code>true</code> if this Tout Portlet Status by User is tout seen.
	*
	* @return <code>true</code> if this Tout Portlet Status by User is tout seen; <code>false</code> otherwise
	*/
	@Override
	public boolean isToutSeen() {
		return _toutUserStatus.isToutSeen();
	}

	/**
	* Sets whether this Tout Portlet Status by User is tout seen.
	*
	* @param toutSeen the tout seen of this Tout Portlet Status by User
	*/
	@Override
	public void setToutSeen(boolean toutSeen) {
		_toutUserStatus.setToutSeen(toutSeen);
	}

	/**
	* Returns the reminder date of this Tout Portlet Status by User.
	*
	* @return the reminder date of this Tout Portlet Status by User
	*/
	@Override
	public java.util.Date getReminderDate() {
		return _toutUserStatus.getReminderDate();
	}

	/**
	* Sets the reminder date of this Tout Portlet Status by User.
	*
	* @param reminderDate the reminder date of this Tout Portlet Status by User
	*/
	@Override
	public void setReminderDate(java.util.Date reminderDate) {
		_toutUserStatus.setReminderDate(reminderDate);
	}

	@Override
	public boolean isNew() {
		return _toutUserStatus.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_toutUserStatus.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _toutUserStatus.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_toutUserStatus.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _toutUserStatus.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _toutUserStatus.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_toutUserStatus.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _toutUserStatus.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_toutUserStatus.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_toutUserStatus.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_toutUserStatus.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ToutUserStatusWrapper((ToutUserStatus)_toutUserStatus.clone());
	}

	@Override
	public int compareTo(ToutUserStatus toutUserStatus) {
		return _toutUserStatus.compareTo(toutUserStatus);
	}

	@Override
	public int hashCode() {
		return _toutUserStatus.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<ToutUserStatus> toCacheModel() {
		return _toutUserStatus.toCacheModel();
	}

	@Override
	public ToutUserStatus toEscapedModel() {
		return new ToutUserStatusWrapper(_toutUserStatus.toEscapedModel());
	}

	@Override
	public ToutUserStatus toUnescapedModel() {
		return new ToutUserStatusWrapper(_toutUserStatus.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _toutUserStatus.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _toutUserStatus.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_toutUserStatus.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ToutUserStatusWrapper)) {
			return false;
		}

		ToutUserStatusWrapper toutUserStatusWrapper = (ToutUserStatusWrapper)obj;

		if (Validator.equals(_toutUserStatus,
					toutUserStatusWrapper._toutUserStatus)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public ToutUserStatus getWrappedToutUserStatus() {
		return _toutUserStatus;
	}

	@Override
	public ToutUserStatus getWrappedModel() {
		return _toutUserStatus;
	}

	@Override
	public void resetOriginalValues() {
		_toutUserStatus.resetOriginalValues();
	}

	private ToutUserStatus _toutUserStatus;
}