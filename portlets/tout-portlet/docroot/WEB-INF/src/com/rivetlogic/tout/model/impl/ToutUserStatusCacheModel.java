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

package com.rivetlogic.tout.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.rivetlogic.tout.model.ToutUserStatus;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ToutUserStatus in entity cache.
 *
 * @author ronny.vargas
 * @see ToutUserStatus
 * @generated
 */
public class ToutUserStatusCacheModel implements CacheModel<ToutUserStatus>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append(", toutDismissed=");
		sb.append(toutDismissed);
		sb.append(", toutSeen=");
		sb.append(toutSeen);
		sb.append(", reminderDate=");
		sb.append(reminderDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ToutUserStatus toEntityModel() {
		ToutUserStatusImpl toutUserStatusImpl = new ToutUserStatusImpl();

		toutUserStatusImpl.setUserId(userId);
		toutUserStatusImpl.setArticleId(articleId);
		toutUserStatusImpl.setToutDismissed(toutDismissed);
		toutUserStatusImpl.setToutSeen(toutSeen);

		if (reminderDate == Long.MIN_VALUE) {
			toutUserStatusImpl.setReminderDate(null);
		}
		else {
			toutUserStatusImpl.setReminderDate(new Date(reminderDate));
		}

		toutUserStatusImpl.resetOriginalValues();

		return toutUserStatusImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();
		articleId = objectInput.readLong();
		toutDismissed = objectInput.readBoolean();
		toutSeen = objectInput.readBoolean();
		reminderDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userId);
		objectOutput.writeLong(articleId);
		objectOutput.writeBoolean(toutDismissed);
		objectOutput.writeBoolean(toutSeen);
		objectOutput.writeLong(reminderDate);
	}

	public long userId;
	public long articleId;
	public boolean toutDismissed;
	public boolean toutSeen;
	public long reminderDate;
}