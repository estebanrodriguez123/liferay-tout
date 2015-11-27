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

import static com.rivetlogic.tout.util.UserActionsEnum.DISMISSED;
import static com.rivetlogic.tout.util.UserActionsEnum.REMINDLATER;
import static com.rivetlogic.tout.util.UserActionsEnum.REVIEWED;
import static com.rivetlogic.tout.util.UserActionsEnum.valueOf;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.rivetlogic.tout.config.ToutConfig;
import com.rivetlogic.tout.config.ToutConfigUtil;
import com.rivetlogic.tout.model.ToutUserStatus;
import com.rivetlogic.tout.service.ToutUserStatusLocalServiceUtil;
import com.rivetlogic.tout.service.persistence.ToutUserStatusPK;
import com.rivetlogic.tout.util.UserActionsEnum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ronny
 */
public class ToutPortlet extends MVCPortlet {
    
    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        
        if (setToutDialogShowing(request, getMatchingToutConfig(request))) {
            super.doView(request, response);
        }
    }
    
    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        
        String jspPage = ParamUtil.getString(request, ToutPortletConstants.JSP_PAGE);
        
        if (jspPage.equals(ToutPortletConstants.TEMPLATE_CONTENT_DISPLAY)) {
            
            ToutConfig tout = getMatchingToutConfig(request);
            if (setToutDialogShowing(request, tout)) {
                setArticleToDisplay(request, tout);
            }
        }
        
        super.render(request, response);
    }
    
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        JSONObject resultJsonObject = JSONFactoryUtil.createJSONObject();
        
        try {
            processActions(request);
            resultJsonObject.put(ToutPortletConstants.JSON_ANSWER_STATUS, true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultJsonObject = JSONFactoryUtil.createJSONObject();
            resultJsonObject.put(ToutPortletConstants.JSON_ANSWER_STATUS, false);
            resultJsonObject.put(ToutPortletConstants.JSON_ANSWER_ERROR_MESSAGE, e.getMessage());
        }
        
        returnJSONObject(response, resultJsonObject);
    }
    
    private void processActions(PortletRequest request) throws SystemException {

        long articleId = ParamUtil.getLong(request, ToutPortletConstants.ATTR_TOUT_SHOW_ARTICLE_ID);       
        String actionName = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ACTION);
        String toutConfigId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ID);
        
        ToutConfig tout = getMatchingToutConfig(request);
        
        if (!actionName.isEmpty()) {
            UserActionsEnum action = valueOf(actionName);
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            ToutUserStatusPK pk = new ToutUserStatusPK(themeDisplay.getUser().getUserId(), toutConfigId);
            ToutUserStatus userStatus = getToutUserStatus(pk);
            userStatus.setArticleId(articleId);
            
            if (action.equals(DISMISSED)) {
                articleDismissedForUser(userStatus);
                
            } else if (action.equals(REVIEWED)) {
                articleReviewedByUser(userStatus);
                
            } else if (action.equals(REMINDLATER)) {
                int daysBeforeReminder = tout.getDaysBeforeReminder();
                remindArticleToUser(userStatus, daysBeforeReminder);
            }
            
            ToutUserStatusLocalServiceUtil.updateToutUserStatus(userStatus);
        }
        
    }
    
    private void articleDismissedForUser(ToutUserStatus userStatus) {
        userStatus.setReminderDate(null);
        userStatus.setToutDismissed(true);
        userStatus.setToutSeen(false);
    }
    
    private void articleReviewedByUser(ToutUserStatus userStatus) {
        userStatus.setReminderDate(null);
        userStatus.setToutDismissed(false);
        userStatus.setToutSeen(true);
    }
    
    private void remindArticleToUser(ToutUserStatus userStatus, int daysBeforeReminder) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, INIT_VALUE);
        cal.set(Calendar.MINUTE, INIT_VALUE);
        cal.set(Calendar.SECOND, INIT_VALUE);
        cal.set(Calendar.MILLISECOND, INIT_VALUE);
        cal.add(Calendar.DATE, daysBeforeReminder);
        
        userStatus.setReminderDate(cal.getTime());
        userStatus.setToutDismissed(false);
        userStatus.setToutSeen(false);
    }
    
    private ToutConfig getMatchingToutConfig(PortletRequest request){
    	ToutConfig toutConfig = null;
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	Layout layout = themeDisplay.getLayout();
    	try{
	    	Set<ToutConfig>toutConfigList = ToutConfigUtil.getToutConfigSet();
	    	if(null != toutConfigList){
		    	for(ToutConfig toutConfigItem : toutConfigList){
		    		Group currentGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
		    		long groupId = (currentGroup.isRoot() ? currentGroup.getGroupId() : currentGroup.getParentGroupId());
		    		if (toutConfigItem != null && toutConfigItem.isEnabledOnSite(groupId, layout.getNameCurrentValue()) && themeDisplay.isSignedIn()
		                    && !layout.getGroup().getName().equals(GroupConstants.CONTROL_PANEL)) {
		    			toutConfig = toutConfigItem;
		    			break;
		    		}
		    	}
	    	}
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return toutConfig;
    }
    
    private boolean setToutDialogShowing(PortletRequest request, ToutConfig tout) {
        if(null == tout)
        	return false;
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        boolean display = false;
        try {
            JournalArticle toutArticle = JournalArticleLocalServiceUtil.getArticle(tout.getArticleGroupId(),
                    tout.getArticleId());
            ToutUserStatusPK pk = new ToutUserStatusPK(themeDisplay.getUser().getUserId(), tout.getId());
            ToutUserStatus userStatus = getToutUserStatus(pk);
            
            // For user, we keep the ID (not articleId) because it
            // correspond to (articleId-version)
            if (userStatus.getArticleId() != toutArticle.getId()) {
                display = true;
            } else if (!userStatus.getToutDismissed() && !userStatus.getToutSeen()
                    && userStatus.getReminderDate() != null && new Date().after(userStatus.getReminderDate())) {
                display = true;
            }
            request.setAttribute(ToutPortletConstants.ATTR_TOUT_SHOW, display);
        	
        } catch (Exception e) {
            logger.error(e);
            request.setAttribute(ToutPortletConstants.ATTR_TOUT_SHOW, false);
        }
        
        return display;
    }
    
    private void setArticleToDisplay(PortletRequest request, ToutConfig tout) {
        if (tout != null && tout.isEnabled()) {
            JournalArticle article = getJournalArticle(tout);
            request.setAttribute(ToutPortletConstants.ATTR_TOUT_URL, tout.getShowMoreURL());
            request.setAttribute(ToutPortletConstants.ATTR_TOUT_SHOW_ARTICLE, article);
            request.setAttribute(ToutPortletConstants.ATTR_TOUT_ID, tout.getId());
            JournalContentUtil.clearCache(tout.getArticleGroupId(), tout.getArticleId(), null);
        }
    }
    
    /**
     * just fetches toutUserStatus Object, creates one if necessary adds error
     * log if fails
     * 
     * @return ToutConfig
     */
    private ToutUserStatus getToutUserStatus(ToutUserStatusPK pk) {
        ToutUserStatus toutUserStatus = null;
        try {
            toutUserStatus = ToutUserStatusLocalServiceUtil.fetchToutUserStatus(pk);
            if (toutUserStatus == null) {
                toutUserStatus = ToutUserStatusLocalServiceUtil.createToutUserStatus(pk);
            }
        } catch (SystemException s) {
            logger.error(s);
        }
        return toutUserStatus;
    }
    
    /**
     * just fetches JournalArticle Object, adds error log if fails
     * 
     * @return JournalArticle
     */
    private JournalArticle getJournalArticle(ToutConfig toutConfig) {
        JournalArticle article = null;
        try {
            article = JournalArticleLocalServiceUtil.getArticle(toutConfig.getArticleGroupId(),
                    toutConfig.getArticleId());
        } catch (SystemException s) {
            logger.error(s);
        } catch (PortalException p) {
            logger.error(p);
        }
        return article;
    }
    
    private void returnJSONObject(PortletResponse response, JSONObject jsonObj) {
        HttpServletResponse servletResponse = PortalUtil.getHttpServletResponse(response);
        PrintWriter pw;
        try {
            pw = servletResponse.getWriter();
            pw.write(jsonObj.toString());
            pw.close();
        } catch (IOException e) {
            logger.error(JSON_ERROR, e);
        }
    }
    
    private static final Log logger = LogFactoryUtil.getLog(ToutPortlet.class);
    private static final int INIT_VALUE = 0;
    private static final String JSON_ERROR = "Error while returning json";
}
