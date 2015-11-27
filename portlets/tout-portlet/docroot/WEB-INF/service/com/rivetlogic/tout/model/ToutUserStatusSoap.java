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

import com.rivetlogic.tout.service.persistence.ToutUserStatusPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author ronny.vargas
 * @generated
 */
public class ToutUserStatusSoap implements Serializable {
	public static ToutUserStatusSoap toSoapModel(ToutUserStatus model) {
		ToutUserStatusSoap soapModel = new ToutUserStatusSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setToutConfigId(model.getToutConfigId());
		soapModel.setArticleId(model.getArticleId());
		soapModel.setToutDismissed(model.getToutDismissed());
		soapModel.setToutSeen(model.getToutSeen());
		soapModel.setReminderDate(model.getReminderDate());

		return soapModel;
	}

	public static ToutUserStatusSoap[] toSoapModels(ToutUserStatus[] models) {
		ToutUserStatusSoap[] soapModels = new ToutUserStatusSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ToutUserStatusSoap[][] toSoapModels(ToutUserStatus[][] models) {
		ToutUserStatusSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ToutUserStatusSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ToutUserStatusSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ToutUserStatusSoap[] toSoapModels(List<ToutUserStatus> models) {
		List<ToutUserStatusSoap> soapModels = new ArrayList<ToutUserStatusSoap>(models.size());

		for (ToutUserStatus model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ToutUserStatusSoap[soapModels.size()]);
	}

	public ToutUserStatusSoap() {
	}

	public ToutUserStatusPK getPrimaryKey() {
		return new ToutUserStatusPK(_userId, _toutConfigId);
	}

	public void setPrimaryKey(ToutUserStatusPK pk) {
		setUserId(pk.userId);
		setToutConfigId(pk.toutConfigId);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getToutConfigId() {
		return _toutConfigId;
	}

	public void setToutConfigId(String toutConfigId) {
		_toutConfigId = toutConfigId;
	}

	public long getArticleId() {
		return _articleId;
	}

	public void setArticleId(long articleId) {
		_articleId = articleId;
	}

	public boolean getToutDismissed() {
		return _toutDismissed;
	}

	public boolean isToutDismissed() {
		return _toutDismissed;
	}

	public void setToutDismissed(boolean toutDismissed) {
		_toutDismissed = toutDismissed;
	}

	public boolean getToutSeen() {
		return _toutSeen;
	}

	public boolean isToutSeen() {
		return _toutSeen;
	}

	public void setToutSeen(boolean toutSeen) {
		_toutSeen = toutSeen;
	}

	public Date getReminderDate() {
		return _reminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		_reminderDate = reminderDate;
	}

	private long _userId;
	private String _toutConfigId;
	private long _articleId;
	private boolean _toutDismissed;
	private boolean _toutSeen;
	private Date _reminderDate;
}