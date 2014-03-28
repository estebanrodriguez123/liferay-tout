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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.rivetlogic.tout.config.ToutConfig;
import com.rivetlogic.tout.config.ToutConfigUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Ronny.Vargas
 */
public class ToutConfigPortlet extends MVCPortlet {
    
    private static final Log logger = LogFactoryUtil.getLog(ToutConfigPortlet.class);
    private static final String TYPE_TEXT_HTML = "text/html";
    
    @Override
    public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        
        PortletPreferences preferences = request.getPreferences();
        
        ToutConfig toutConfig = ToutConfigUtil.getToutConfig(preferences);
        
        request.setAttribute(ToutPortletConstants.ATTR_TOUT_ENABLED, toutConfig.isEnabled());
        request.setAttribute(ToutPortletConstants.ATTR_TOUT_URL, toutConfig.getShowMoreURL());
        
        if (toutConfig.getDaysBeforeReminder() != ToutConfigUtil.UNDEFINED_DAYS_REMINDER) {
            request.setAttribute(ToutPortletConstants.ATTR_TOUT_DAYS_BEFORE_REMINDER,
                    toutConfig.getDaysBeforeReminder());
        }
        
        if (toutConfig.getArticleId() != ToutConfigUtil.UNDEFINED_ARTICLE) {
            
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
        
        super.doView(request, response);
    }
    
    public void saveToutPreferences(ActionRequest request, ActionResponse response) {
        
        boolean toutEnabled = ParamUtil.getBoolean(request, ToutPortletConstants.ATTR_TOUT_ENABLED);
        String toutUrl = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_URL);
        int toutDaysBeforeReminder = ParamUtil.getInteger(request, ToutPortletConstants.ATTR_TOUT_DAYS_BEFORE_REMINDER);
        String toutArticleId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_ID);
        long toutArticleGroupId = ParamUtil.getLong(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID);
        
        try {
            ToutConfigUtil.saveToutConfig(toutEnabled, toutUrl, toutDaysBeforeReminder, toutArticleId,
                    toutArticleGroupId, request.getPreferences());
        } catch (Exception e) {
            SessionErrors.add(request, ToutPortletConstants.ERROR_STORING_PREFERENCES);
            logger.error(e);
        }
    }
    
    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        // if rendering back to parent from Dialog
        if (ParamUtil.getString(request, ToutPortletConstants.JSP_PAGE).equals(
                ToutPortletConstants.TEMPLATE_ARTICLE_VIEWER)) {
            
            String selectedArticleId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_ID);
            String selectedGroupId = ParamUtil.getString(request, ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID);
            request.setAttribute(ToutPortletConstants.ATTR_DISPLAY_ARTICLE_ID, selectedArticleId);
            request.setAttribute(ToutPortletConstants.ATTR_DISPLAY_ARTICLE_GROUP_ID, selectedGroupId);
            
        } else if (ParamUtil.getString(request, ToutPortletConstants.JSP_PAGE).equals(
                ToutPortletConstants.TEMPLATE_ARTICLE_SELECTOR)) {
            
            long filterGroup = ParamUtil.getLong(request, ToutPortletConstants.ATTR_GROUP_FILTER,
                    ToutPortletConstants.UNDEFINED_GROUP_ID);
            request.setAttribute(ToutPortletConstants.ATTR_GROUP_FILTER, filterGroup);
            
        }
        
        super.render(request, response);
    }
    
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException {
        String cmd = ParamUtil.getString(request, ToutPortletConstants.CMD);
        if (cmd.equals(ToutPortletConstants.CMD_SHOW_ARTICLE_AS_SELECTED)) {
            displaySelectedArticle(request, response);
        }
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
