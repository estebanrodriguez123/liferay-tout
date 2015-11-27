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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.rivetlogic.tout.portlet.ToutPortletConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToutConfigUtil {
    
	private static final String PREFERENCE_LIST_ATTR = "list";
	
    public static String UNDEFINED_ARTICLE = StringPool.BLANK;
    public static int UNDEFINED_DAYS_REMINDER = -1;
    
    private static final String TOUT_CONFIG_PORTLET_ID = "toutadmin_WAR_toutportlet";
    private static final Log logger = LogFactoryUtil.getLog(ToutConfigUtil.class);
    
    public static ToutConfig getToutConfigFromJsonObj(JSONObject jsonObject){
    	ToutConfig toutConfig = null;
		if(null != jsonObject){
    		toutConfig = new ToutConfig();
    		toutConfig.setArticleGroupId(jsonObject.getLong(ToutPortletConstants.TOUT_CONFIG_ARTICLE_GROUP_ID));
    		toutConfig.setArticleId(jsonObject.getString(ToutPortletConstants.TOUT_CONFIG_ARTICLE_ID));
    		toutConfig.setDaysBeforeReminder(jsonObject.getInt(ToutPortletConstants.TOUT_CONFIG_DAYS_BEFORE_REMINDER));
    		toutConfig.setEnabled(jsonObject.getBoolean(ToutPortletConstants.TOUT_CONFIG_ENABLED));
    		toutConfig.setShowMoreURL(jsonObject.getString(ToutPortletConstants.TOUT_CONFIG_SHOW_MORE_URL));
    		toutConfig.setPagesRegex(jsonObject.getString(ToutPortletConstants.TOUT_CONFIG_PAGES));
    		JSONObject sites = jsonObject.getJSONObject(ToutPortletConstants.TOUT_CONFIG_SITES);
    		List <Long> sitesList = null;
    		if(null != sites && null != sites.getJSONArray(PREFERENCE_LIST_ATTR)){
    			sitesList = new ArrayList<Long>();
        		JSONArray jArray = sites.getJSONArray(PREFERENCE_LIST_ATTR);
        		if (jArray != null) {
    			   for (int i=0;i<jArray.length();i++){ 
    				   sitesList.add(Long.valueOf(jArray.getString(i).toString()));
    			   }
    			}
    		}
    		toutConfig.setSites(sitesList);
    		toutConfig.setId(jsonObject.getString(ToutPortletConstants.TOUT_CONFIG_ID));
		}
    	return toutConfig;
    }
    
    public static ToutConfig getToutConfig(String toutConfigId){
    	
    	if(null == toutConfigId || toutConfigId.isEmpty())
    		return null;
    	
    	ToutConfig toutConfig = null;
        try {
            List<PortletPreferences> portletPreferencesList = PortletPreferencesLocalServiceUtil.getPortletPreferences(
                    PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED, TOUT_CONFIG_PORTLET_ID);
            if (!portletPreferencesList.isEmpty()) {
                javax.portlet.PortletPreferences configPP = PortletPreferencesFactoryUtil
                        .fromDefaultXML(portletPreferencesList.get(0).getPreferences());
                JSONArray jsonArray = JSONFactoryUtil.createJSONArray(configPP.getValue(ToutPortletConstants.TOUT_CONFIG, null));
                if(null != jsonArray){
                	int length = jsonArray.length();
                	for(int count = 0; count < length; count ++){
                		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonArray.getString(count));
                		if(null != jsonObject && toutConfigId.equals(jsonObject.getString(ToutPortletConstants.TOUT_CONFIG_ID))){
                			toutConfig = getToutConfigFromJsonObj(jsonObject);
                		}
                	}
                }
            }
        } catch (SystemException e) {
            logger.error(e);
        } catch (Exception e) {
        	logger.error(e);
		}
    	return toutConfig;
    }
    
    public static Set<ToutConfig> getToutConfigSet(){
    	Set<ToutConfig>toutConfigList = new HashSet<ToutConfig>();
            try {
                List<PortletPreferences> portletPreferencesList = PortletPreferencesLocalServiceUtil.getPortletPreferences(
                        PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED, TOUT_CONFIG_PORTLET_ID);
                if (!portletPreferencesList.isEmpty()) {
                    javax.portlet.PortletPreferences configPP = PortletPreferencesFactoryUtil
                            .fromDefaultXML(portletPreferencesList.get(0).getPreferences());
                    JSONArray jsonArray = JSONFactoryUtil.createJSONArray(configPP.getValue(ToutPortletConstants.TOUT_CONFIG, null));
                    if(null != jsonArray){
                    	int length = jsonArray.length();
                    	for(int count = 0; count < length; count ++){
                    		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonArray.getString(count));
                    		
                    		if(null != jsonObject){
	                    		ToutConfig toutConfig = getToutConfigFromJsonObj(jsonObject);
	                    		toutConfigList.add(toutConfig);
                    		}
                    	}
                    }
                }
            } catch (SystemException e) {
                logger.error(e);
            } catch (Exception e) {
            	logger.error(e);
			}
    		
    	return toutConfigList;
    }
    
    public static void saveToutConfig(String toutConfigId , boolean toutEnabled, String toutUrl, int toutDaysBeforeReminder,
            String toutArticleId, long toutArticleGroupId, List<Long>sites, String pagesRegex, javax.portlet.PortletPreferences preferences) throws Exception {
    	JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
    	
    	Set<ToutConfig>toutConfigList = getToutConfigSet();
    	if(null == toutConfigList){
    		toutConfigList = new HashSet<ToutConfig>();
    	}
    	ToutConfig toutConfig = new ToutConfig();
    	if(null != toutConfigId && !toutConfigId.isEmpty()){
    		toutConfig.setId(toutConfigId);
    	}
    	toutConfig.setEnabled(toutEnabled);
    	toutConfig.setShowMoreURL(toutUrl);
    	toutConfig.setArticleId(toutArticleId);
    	toutConfig.setArticleGroupId(toutArticleGroupId);
    	toutConfig.setSites(sites);
    	toutConfig.setPagesRegex(pagesRegex);
    	toutConfig.setDaysBeforeReminder(toutDaysBeforeReminder);
    	toutConfigList.remove(toutConfig);
    	toutConfigList.add(toutConfig);
    	System.out.println(JSONFactoryUtil.serialize(toutConfig));
    	for(ToutConfig toutConfigItem : toutConfigList){
    		jsonArray.put(JSONFactoryUtil.serialize(toutConfigItem));
    	}
    	System.out.println(jsonArray.toString());
    	preferences.setValue(ToutPortletConstants.TOUT_CONFIG, jsonArray.toString());
        preferences.store();
    }
    
    public static void deleteToutConfig(String toutConfigId, javax.portlet.PortletPreferences preferences) throws Exception{
    	
    	JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
    	Set<ToutConfig>toutConfigList = getToutConfigSet();
    	
    	if(null == toutConfigList){
    		toutConfigList = new HashSet<ToutConfig>();
    	}
    	ToutConfig toutConfig = new ToutConfig();
    	if(null != toutConfigId && !toutConfigId.isEmpty()){
    		toutConfig.setId(toutConfigId);
    	}
    	toutConfigList.remove(toutConfig);
    	for(ToutConfig toutConfigItem : toutConfigList){
    		jsonArray.put(JSONFactoryUtil.serialize(toutConfigItem));
    	}
    	
    	preferences.setValue(ToutPortletConstants.TOUT_CONFIG, jsonArray.toString());
    	preferences.store();
    }
    
}