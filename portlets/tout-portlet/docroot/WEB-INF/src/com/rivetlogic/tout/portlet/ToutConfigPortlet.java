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

package com.rivetlogic.tout.portlet;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.rivetlogic.tout.config.ToutConfig;
import com.rivetlogic.tout.config.ToutConfigUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ronny.Vargas
 */
public class ToutConfigPortlet extends MVCPortlet {
    
    private static final Log logger = LogFactoryUtil.getLog(ToutConfigPortlet.class);
    private static final String TYPE_TEXT_HTML = "text/html";
    
    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
    	Set<ToutConfig>toutConfigsSet =  ToutConfigUtil.getToutConfigSet();
    	List<ToutConfig> toutConfigList = new ArrayList<ToutConfig>();
    	toutConfigList.addAll(toutConfigsSet);
        request.setAttribute(ToutPortletConstants.TOUT_CONFIG_LIST, toutConfigList);
	    super.doView(request, response);
    }
    
    public void deleteToutConfig(ActionRequest request, ActionResponse response){
    	String toutConfigId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ID);
    	if(null == toutConfigId || toutConfigId.isEmpty()){
    		SessionErrors.add(request, String.format(ToutPortletConstants.ERROR_DELETING_TOUT_PREFERENCES, ToutPortletConstants.ATTR_TOUT_ID));
    	}else{
    		try{
    			ToutConfigUtil.deleteToutConfig(toutConfigId, request.getPreferences());
    		}catch(Exception e){
    			SessionErrors.add(request, ToutPortletConstants.ERROR_DELETING_TOUT_PREFERENCES);
    		}
    	}
    }
    
    public void saveToutPreferences(ActionRequest request, ActionResponse response) {
        boolean toutEnabled = ParamUtil.getBoolean(request, ToutPortletConstants.ATTR_TOUT_ENABLED);
        String toutUrl = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_URL);
        int toutDaysBeforeReminder = ParamUtil.getInteger(request, ToutPortletConstants.ATTR_TOUT_DAYS_BEFORE_REMINDER);
        String toutArticleId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_ID);
        long toutArticleGroupId = ParamUtil.getLong(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID);
        String toutConfigId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ID);
        String pagesRegex = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_PAGES);
        
        try{
        	if(null != pagesRegex)
        		Pattern.compile(pagesRegex.toLowerCase());
        }catch(PatternSyntaxException e){
        	SessionErrors.add(request, ToutPortletConstants.ERROR_BAD_PARAMETER_REGEX);
        	return;
        }
        
        try {
        	List<String> sites = Arrays.asList(ParamUtil.getParameterValues(request, ToutPortletConstants.ATTR_TOUT_SITES));
        	List<Long>sitesIds = null;
        	if(null != sites && !sites.isEmpty()){
        		sitesIds = new ArrayList<Long>();
	        	for(String site : sites){
	        		sitesIds.add(Long.valueOf(site));
	        	}
        	}
            ToutConfigUtil.saveToutConfig(toutConfigId, toutEnabled, toutUrl, toutDaysBeforeReminder, toutArticleId,
                    toutArticleGroupId, sitesIds, pagesRegex, request.getPreferences());
        } catch (Exception e) {
            SessionErrors.add(request, ToutPortletConstants.ERROR_STORING_PREFERENCES);
            logger.error(e);
        }
        
    }

    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
    	switch(ParamUtil.getString(request, ToutPortletConstants.JSP_PAGE)){
    		case ToutPortletConstants.TEMPLATE_ARTICLE_VIEWER:
                String selectedArticleId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_ID);
                String selectedGroupId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID);
                request.setAttribute(ToutPortletConstants.ATTR_DISPLAY_ARTICLE_ID, selectedArticleId);
                request.setAttribute(ToutPortletConstants.ATTR_DISPLAY_ARTICLE_GROUP_ID, selectedGroupId);
    			break;
    		case ToutPortletConstants.TEMPLATE_ARTICLE_SELECTOR:
                long filterGroup = ParamUtil.getLong(request, ToutPortletConstants.ATTR_GROUP_FILTER,
                        ToutPortletConstants.UNDEFINED_GROUP_ID);
                request.setAttribute(ToutPortletConstants.ATTR_GROUP_FILTER, filterGroup);
    			break;
    		case ToutPortletConstants.TEMPLATE_ADD_TOUT_CONFIG:
    			String toutConfigId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ID);
    			ToutConfig toutConfig = ToutConfigUtil.getToutConfig(toutConfigId);
    			if (null == toutConfig){
    				toutConfig = new ToutConfig();
    				if(null != toutConfigId && !toutConfigId.isEmpty()){
    					toutConfig.setId(toutConfigId);
    				}
    			}
    	        request.setAttribute(ToutPortletConstants.TOUT_CONFIG, toutConfig);
    	        if (null != toutConfig.getArticleId() && !toutConfig.getArticleId().isEmpty()) {
    	            try {
    	                JournalArticle article = JournalArticleLocalServiceUtil.getArticle(toutConfig.getArticleGroupId(),
    	                        toutConfig.getArticleId());
    	                if (article != null) {
    	                    addSelectedArticleRequest(article, request);
    	                }
    	            } catch (Exception e) {
    	                logger.error(e);
    	            }
    	        }
    			break;
    		default:
    			break;
    	}
        super.render(request, response);
    }
    
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        String cmd = ParamUtil.getString(request, ToutPortletConstants.CMD);
        if (cmd.equals(ToutPortletConstants.CMD_SHOW_ARTICLE_AS_SELECTED)) {
            displaySelectedArticle(request, response);
        }else if(cmd.equals(ToutPortletConstants.CMD_SHOW_MATCHING_PAGES)){
        	displayMatchingPages(request, response);
        }
    }
    
    private void displayMatchingPages(ResourceRequest request, ResourceResponse response) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
    	try{
	    	String sitesIdsParam = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_SITES);
	    	String pagesRegex = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_PAGES);
	    	if(null == pagesRegex || pagesRegex.isEmpty()){
	    		printJsonResponse(LanguageUtil.get(portletConfig, themeDisplay.getLocale(), ToutPortletConstants.ERROR_BAD_PARAMETER_REGEX), 
	    				String.valueOf(HttpServletResponse.SC_BAD_REQUEST), response);
	    		return;
	    	}
	    	Pattern pattern = null;
			try {
	    		pattern = Pattern.compile(pagesRegex.toLowerCase());
			} catch (PatternSyntaxException  e) {
				logger.error(e);
				printJsonResponse(LanguageUtil.get(portletConfig, themeDisplay.getLocale(), ToutPortletConstants.ERROR_BAD_PARAMETER_REGEX), 
						String.valueOf(HttpServletResponse.SC_BAD_REQUEST), response);
				return;
			}
	    	String[] sitesIdsArr = (null != sitesIdsParam && !sitesIdsParam.isEmpty() ? sitesIdsParam.split(StringPool.COMMA) : null);
	    	Set<Layout> pages = getSitesPages(sitesIdsArr, themeDisplay.getCompanyId());
	    	Set<String>matchingSitesPagesStr = new HashSet<String>();
	    	if(null != pages)
		    	for(Layout layout : pages){
					if (pattern.matcher(layout.getNameCurrentValue().toLowerCase()).find()){
						String matchingSitePageStr = layout.getGroup().getDescriptiveName() + 
								StringPool.SPACE + StringPool.DASH + StringPool.SPACE +
								layout.getNameCurrentValue();
						matchingSitesPagesStr.add(matchingSitePageStr);
					}
		    	}
	    	if(!matchingSitesPagesStr.isEmpty()){
				JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
				for(String item : matchingSitesPagesStr){
					jsonArray.put(JSONFactoryUtil.serialize(item));
				}
				printJsonResponse(jsonArray.toString(), String.valueOf(HttpServletResponse.SC_OK), response);	
	    	}else{
	    		printJsonResponse(LanguageUtil.get(portletConfig, themeDisplay.getLocale(), ToutPortletConstants.ERROR_NO_MATCHING_PAGES), 
	    				String.valueOf(HttpServletResponse.SC_NOT_FOUND), response);
	    	}
    	}catch(Exception e){
    		logger.error(e);
    		printJsonResponse(LanguageUtil.get(portletConfig, themeDisplay.getLocale(), ToutPortletConstants.ERROR_RETRIEVING_MATCHING_PAGES), 
    				String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR), response);
    	}
    }
    
    private void printJsonResponse(String jsonStr, String statusCode, ResourceResponse response){
    	if(null == statusCode)
    		statusCode = String.valueOf(HttpServletResponse.SC_OK);
    	response.setProperty(ResourceResponse.HTTP_STATUS_CODE, statusCode);
    	PrintWriter out = null;
    	try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.error(e);
		}
		if(null != out && !out.checkError()){
			response.setContentType(ContentTypes.APPLICATION_JSON);
			out.print(jsonStr);
	    	out.flush();
	    	out.close();
		}
    }
    
    private Set<Layout> getSitesPages(String[] sitesIdsArr, Long companyId) throws SystemException{
    	Set<Long>sitesIds = null;
    	if(null != sitesIdsArr && sitesIdsArr.length > 0){
    		sitesIds = new HashSet<Long>();
			for(String siteId : sitesIdsArr){
				sitesIds.add(Long.valueOf(siteId));
			}
    	}else{
    		sitesIds = new HashSet<Long>();
			List<Group>  availableGroups = GroupLocalServiceUtil.getGroups(companyId, GroupConstants.DEFAULT_PARENT_GROUP_ID, true);
			if(null != availableGroups && !availableGroups.isEmpty()){
				for(Group group : availableGroups){
					sitesIds.add(group.getGroupId());
				}
			}
    	}
    	Set<Layout>pages = null;
    	if(null != sitesIds && !sitesIds.isEmpty()){
    		pages = new HashSet<Layout>();
    		for(Long siteId : sitesIds){
				pages.addAll(LayoutLocalServiceUtil.getLayouts(siteId, false));
				pages.addAll(LayoutLocalServiceUtil.getLayouts(siteId, true));
    		}
    	}
    	return pages;
    }
    
    private void displaySelectedArticle(ResourceRequest request, ResourceResponse response) {
        
        try {
            String selectedArticleId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_ID,
                    ToutConfigUtil.UNDEFINED_ARTICLE);
            long selectedArticleGroupId = ParamUtil.getLong(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID);
            
            if (!selectedArticleId.equals(ToutConfigUtil.UNDEFINED_ARTICLE)) {
                JournalArticle article = JournalArticleLocalServiceUtil.getArticle(selectedArticleGroupId,
                        selectedArticleId);
                addSelectedArticleRequest(article, request);
            }
            
            PortletRequestDispatcher dispatcher = request.getPortletSession().getPortletContext()
                    .getRequestDispatcher(ToutPortletConstants.TEMPLATE_SELECTED_ARTICLE_DISPLAY);
            response.setContentType(TYPE_TEXT_HTML);
            dispatcher.include(request, response);
        } catch (Exception e) {
            logger.error(e);
        }
    }
    
    private void addSelectedArticleRequest(JournalArticle article, PortletRequest request) {
        if (article != null) {
            try {
                Group group = GroupLocalServiceUtil.getGroup(article.getGroupId());
                request.setAttribute(ToutPortletConstants.ATTR_TOUT_ARTICLE, article);
                request.setAttribute(ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP, group);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
}
