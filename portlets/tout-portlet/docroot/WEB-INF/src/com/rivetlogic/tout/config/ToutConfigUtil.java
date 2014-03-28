/**
 * Copyright (C) 2005-2014 Rivet Logic Corporation.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.rivetlogic.tout.config;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.List;

public class ToutConfigUtil {
    
    private static final String PREFERENCE_TOUT_ENABLED = "toutEnabled";
    private static final String PREFERENCE_TOUT_URL = "toutUrl";
    private static final String PREFERENCE_TOUT_DAYS_BEFORE_REMINDER = "toutDaysBeforeReminder";
    private static final String PREFERENCE_TOUT_ARTICLE_ID = "toutArticleId";
    private static final String PREFERENCE_TOUT_ARTICLE_GROUP_ID = "toutArticleGroupId";
    
    public static String UNDEFINED_ARTICLE = StringPool.BLANK;
    public static int UNDEFINED_DAYS_REMINDER = -1;
    private static String UNDEFINED_ARTICLE_GROUP_ID = "-1";
    private static String DEFAULT_TOUT_ENABLE = "false";
    
    private static final String TOUT_CONFIG_PORTLET_ID = "toutadmin_WAR_toutportlet";
    private static final Log logger = LogFactoryUtil.getLog(ToutConfigUtil.class);
    
    public static ToutConfig getToutConfig() {
        
        ToutConfig toutConfig = new ToutConfig();
        try {
            List<PortletPreferences> portletPreferencesList = PortletPreferencesLocalServiceUtil.getPortletPreferences(
                    PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED, TOUT_CONFIG_PORTLET_ID);
            if (!portletPreferencesList.isEmpty()) {
                javax.portlet.PortletPreferences configPP = PortletPreferencesFactoryUtil
                        .fromDefaultXML(portletPreferencesList.get(0).getPreferences());
                toutConfig = getToutConfig(configPP);
            }
            
            return toutConfig;
            
        } catch (SystemException e) {
            logger.error(e);
            return new ToutConfig();
        }
    }
    
    public static ToutConfig getToutConfig(javax.portlet.PortletPreferences preferences) {
        
        ToutConfig toutConfig = new ToutConfig();
        
        boolean toutEnabled = Boolean.parseBoolean(preferences.getValue(PREFERENCE_TOUT_ENABLED, DEFAULT_TOUT_ENABLE));
        String toutUrl = preferences.getValue(PREFERENCE_TOUT_URL, StringPool.BLANK);
        int toutDaysBeforeReminder = Integer.parseInt(preferences.getValue(PREFERENCE_TOUT_DAYS_BEFORE_REMINDER,
                String.valueOf(UNDEFINED_DAYS_REMINDER)));
        String toutArticleId = preferences.getValue(PREFERENCE_TOUT_ARTICLE_ID, UNDEFINED_ARTICLE);
        long toutArticleGroupId = Long.parseLong(preferences.getValue(PREFERENCE_TOUT_ARTICLE_GROUP_ID, UNDEFINED_ARTICLE_GROUP_ID));
        
        toutConfig.setEnabled(toutEnabled);
        toutConfig.setShowMoreURL(toutUrl);
        toutConfig.setDaysBeforeReminder(toutDaysBeforeReminder);
        toutConfig.setArticleId(toutArticleId);
        toutConfig.setArticleGroupId(toutArticleGroupId);
        
        return toutConfig;
    }
    
    public static void saveToutConfig(boolean toutEnabled, String toutUrl, int toutDaysBeforeReminder,
            String toutArticleId, long toutArticleGroupId, javax.portlet.PortletPreferences preferences) throws Exception {
        
        preferences.setValue(PREFERENCE_TOUT_ENABLED, String.valueOf(toutEnabled));
        preferences.setValue(PREFERENCE_TOUT_URL, toutUrl);
        preferences.setValue(PREFERENCE_TOUT_DAYS_BEFORE_REMINDER, String.valueOf(toutDaysBeforeReminder));
        preferences.setValue(PREFERENCE_TOUT_ARTICLE_ID, toutArticleId);
        preferences.setValue(PREFERENCE_TOUT_ARTICLE_GROUP_ID, String.valueOf(toutArticleGroupId));
        preferences.store();
    }
}