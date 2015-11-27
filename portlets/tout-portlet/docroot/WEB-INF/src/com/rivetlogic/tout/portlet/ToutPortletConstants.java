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


public class ToutPortletConstants {
    
	public static final String TOUT_CONFIG = "toutConfig";
	public static final String TOUT_CONFIG_LIST = "toutConfigList";
	//public static final String TOUT_CONFIG_ID_PARAM = "toutConfigId";
	
    public static final String ATTR_TOUT_ENABLED = "toutEnabled";
    public static final String ATTR_TOUT_URL = "toutUrl";
    public static final String ATTR_TOUT_DAYS_BEFORE_REMINDER = "toutDaysBeforeReminder";
    public static final String ATTR_TOUT_ARTICLE_ID = "selectedArticleId";
    public static final String ATTR_TOUT_ARTICLE = "selectedArticle";
    public static final String ATTR_TOUT_ARTICLE_GROUP_ID = "selectedArticleGroupId";
    public static final String ATTR_TOUT_ARTICLE_GROUP = "selectedArticleGroup";
    public static final String ATTR_DISPLAY_ARTICLE_ID = "displayArticleId";
    public static final String ATTR_DISPLAY_ARTICLE_GROUP_ID = "displayArticleGroupId";
    public static final String ATTR_TOUT_SHOW = "showTout";
    public static final String ATTR_TOUT_ACTION = "action";
    public static final String ATTR_TOUT_SHOW_ARTICLE = "article";
    public static final String ATTR_TOUT_SHOW_ARTICLE_ID = "articleId";
    public static final String ATTR_TOUT_ID = "toutConfigId";
    public static final String ATTR_TOUT_SITES = "sites";
    public static final String ATTR_TOUT_PAGES = "pageRegex";
    
    public static final String TOUT_CONFIG_ID = "id";
    public static final String TOUT_CONFIG_ENABLED = "enabled";
    public static final String TOUT_CONFIG_ARTICLE_ID = "articleId";
    public static final String TOUT_CONFIG_ARTICLE_GROUP_ID = "articleGroupId";
    public static final String TOUT_CONFIG_DAYS_BEFORE_REMINDER = "daysBeforeReminder";
    public static final String TOUT_CONFIG_SHOW_MORE_URL = "showMoreURL";
    public static final String TOUT_CONFIG_PAGES = "pagesRegex";
    public static final String TOUT_CONFIG_SITES = "sites";
    
    public static final String CMD = "cmd";
    public static final String CMD_SHOW_ARTICLE_AS_SELECTED = "showArticleAsSelected";
    public static final String CMD_SHOW_MATCHING_PAGES = "showMatchingPages";
    public static final String JSP_PAGE = "jspPage";
    public static final String TEMPLATE_ARTICLE_VIEWER = "/html/control-panel/articleViewer.jsp";
    public static final String TEMPLATE_SELECTED_ARTICLE_DISPLAY = "/html/control-panel/selected_article_display.jsp";
    public static final String TEMPLATE_ARTICLE_SELECTOR = "/html/control-panel/articleSelector.jsp";
    public static final String TEMPLATE_CONTENT_DISPLAY = "/html/content.jsp";
    public static final String TEMPLATE_ADD_TOUT_CONFIG = "/html/control-panel/add.jsp";
    
    public static final long UNDEFINED_GROUP_ID = 0;
    
    public static final String ATTR_ORDER_BY_COL = "orderByCol";
    public static final String ATTR_ORDER_BY_TYPE = "orderByType";
    public static final String ATTR_ORDER_ASC = "asc";
    public static final String ATTR_DEFAULT_ORDER_COL = "modifiedDate";
    public static final String ATTR_GROUP_FILTER = "groupFilter";
    
    public static final String JSON_ANSWER_STATUS = "responseOK";
    public static final String JSON_ANSWER_ERROR_MESSAGE = "errorMessage";
    
    public static final String ERROR_STORING_PREFERENCES = "tout-error-storing-preferences";
    public static final String ERROR_DELETING_TOUT_PREFERENCES = "tout-error-deleting-tout-preferences";
    public static final String ERROR_MISSING_REQUIRED_PARAMETER = "tout-error-missing-required-parameter";
    public static final String ERROR_BAD_PARAMETER_REGEX = "tout-error-bad-pages-regex-value";
    public static final String ERROR_NO_MATCHING_PAGES = "tout-error-no-matching-pages-found";
    public static final String ERROR_RETRIEVING_MATCHING_PAGES = "tout-error-retrieving-matching-pages";
    
    public static final String ACTION = "action";
    public static final String ACTION_EDIT = "action-edit";
    public static final String ACTION_ADD = "action-add";
    
    public static final int TABLE_NUM_ROWS = 10;
    public static final int ONE_ROW = 1;
}
