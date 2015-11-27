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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.rivetlogic.tout.service.ClpSerializer;
import com.rivetlogic.tout.service.ToutUserStatusLocalServiceUtil;
import com.rivetlogic.tout.service.persistence.ToutUserStatusPK;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ronny.vargas
 */
public class ToutUserStatusClp extends BaseModelImpl<ToutUserStatus>
	implements ToutUserStatus {
	public ToutUserStatusClp() {
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
	public ToutUserStatusPK getPrimaryKey() {
		return new ToutUserStatusPK(_userId, _toutConfigId);
	}

	@Override
	public void setPrimaryKey(ToutUserStatusPK primaryKey) {
		setUserId(primaryKey.userId);
		setToutConfigId(primaryKey.toutConfigId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ToutUserStatusPK(_userId, _toutConfigId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ToutUserStatusPK)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("toutConfigId", getToutConfigId());
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

		String toutConfigId = (String)attributes.get("toutConfigId");

		if (toutConfigId != null) {
			setToutConfigId(toutConfigId);
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

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_toutUserStatusRemoteModel != null) {
			try {
				Class<?> clazz = _toutUserStatusRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_toutUserStatusRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getToutConfigId() {
		return _toutConfigId;
	}

	@Override
	public void setToutConfigId(String toutConfigId) {
		_toutConfigId = toutConfigId;

		if (_toutUserStatusRemoteModel != null) {
			try {
				Class<?> clazz = _toutUserStatusRemoteModel.getClass();

				Method method = clazz.getMethod("setToutConfigId", String.class);

				method.invoke(_toutUserStatusRemoteModel, toutConfigId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getArticleId() {
		return _articleId;
	}

	@Override
	public void setArticleId(long articleId) {
		_articleId = articleId;

		if (_toutUserStatusRemoteModel != null) {
			try {
				Class<?> clazz = _toutUserStatusRemoteModel.getClass();

				Method method = clazz.getMethod("setArticleId", long.class);

				method.invoke(_toutUserStatusRemoteModel, articleId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getToutDismissed() {
		return _toutDismissed;
	}

	@Override
	public boolean isToutDismissed() {
		return _toutDismissed;
	}

	@Override
	public void setToutDismissed(boolean toutDismissed) {
		_toutDismissed = toutDismissed;

		if (_toutUserStatusRemoteModel != null) {
			try {
				Class<?> clazz = _toutUserStatusRemoteModel.getClass();

				Method method = clazz.getMethod("setToutDismissed",
						boolean.class);

				method.invoke(_toutUserStatusRemoteModel, toutDismissed);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getToutSeen() {
		return _toutSeen;
	}

	@Override
	public boolean isToutSeen() {
		return _toutSeen;
	}

	@Override
	public void setToutSeen(boolean toutSeen) {
		_toutSeen = toutSeen;

		if (_toutUserStatusRemoteModel != null) {
			try {
				Class<?> clazz = _toutUserStatusRemoteModel.getClass();

				Method method = clazz.getMethod("setToutSeen", boolean.class);

				method.invoke(_toutUserStatusRemoteModel, toutSeen);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getReminderDate() {
		return _reminderDate;
	}

	@Override
	public void setReminderDate(Date reminderDate) {
		_reminderDate = reminderDate;

		if (_toutUserStatusRemoteModel != null) {
			try {
				Class<?> clazz = _toutUserStatusRemoteModel.getClass();

				Method method = clazz.getMethod("setReminderDate", Date.class);

				method.invoke(_toutUserStatusRemoteModel, reminderDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getToutUserStatusRemoteModel() {
		return _toutUserStatusRemoteModel;
	}

	public void setToutUserStatusRemoteModel(
		BaseModel<?> toutUserStatusRemoteModel) {
		_toutUserStatusRemoteModel = toutUserStatusRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _toutUserStatusRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_toutUserStatusRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ToutUserStatusLocalServiceUtil.addToutUserStatus(this);
		}
		else {
			ToutUserStatusLocalServiceUtil.updateToutUserStatus(this);
		}
	}

	@Override
	public ToutUserStatus toEscapedModel() {
		return (ToutUserStatus)ProxyUtil.newProxyInstance(ToutUserStatus.class.getClassLoader(),
			new Class[] { ToutUserStatus.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ToutUserStatusClp clone = new ToutUserStatusClp();

		clone.setUserId(getUserId());
		clone.setToutConfigId(getToutConfigId());
		clone.setArticleId(getArticleId());
		clone.setToutDismissed(getToutDismissed());
		clone.setToutSeen(getToutSeen());
		clone.setReminderDate(getReminderDate());

		return clone;
	}

	@Override
	public int compareTo(ToutUserStatus toutUserStatus) {
		ToutUserStatusPK primaryKey = toutUserStatus.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ToutUserStatusClp)) {
			return false;
		}

		ToutUserStatusClp toutUserStatus = (ToutUserStatusClp)obj;

		ToutUserStatusPK primaryKey = toutUserStatus.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{userId=");
		sb.append(getUserId());
		sb.append(", toutConfigId=");
		sb.append(getToutConfigId());
		sb.append(", articleId=");
		sb.append(getArticleId());
		sb.append(", toutDismissed=");
		sb.append(getToutDismissed());
		sb.append(", toutSeen=");
		sb.append(getToutSeen());
		sb.append(", reminderDate=");
		sb.append(getReminderDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.rivetlogic.tout.model.ToutUserStatus");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toutConfigId</column-name><column-value><![CDATA[");
		sb.append(getToutConfigId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>articleId</column-name><column-value><![CDATA[");
		sb.append(getArticleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toutDismissed</column-name><column-value><![CDATA[");
		sb.append(getToutDismissed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toutSeen</column-name><column-value><![CDATA[");
		sb.append(getToutSeen());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reminderDate</column-name><column-value><![CDATA[");
		sb.append(getReminderDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _userId;
	private String _userUuid;
	private String _toutConfigId;
	private long _articleId;
	private boolean _toutDismissed;
	private boolean _toutSeen;
	private Date _reminderDate;
	private BaseModel<?> _toutUserStatusRemoteModel;
	private Class<?> _clpSerializerClass = com.rivetlogic.tout.service.ClpSerializer.class;
}