<%--
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
 */
--%>

<%@include file="/html/init.jsp" %>
 
<%
    /* Sorting */
    String orderByCol = ParamUtil.getString(request, ToutPortletConstants.ATTR_ORDER_BY_COL);
    String orderByType = ParamUtil.getString(request, ToutPortletConstants.ATTR_ORDER_BY_TYPE);

    if (orderByCol == null || orderByCol.equals(StringPool.BLANK)) {
        orderByCol = ToutPortletConstants.ATTR_DEFAULT_ORDER_COL;
    }

    if (orderByType == null || orderByType.equals(StringPool.BLANK)) {
        orderByType = ToutPortletConstants.ATTR_ORDER_ASC ;
    }

    request.setAttribute(ToutPortletConstants.ATTR_ORDER_BY_COL, orderByCol);
    request.setAttribute(ToutPortletConstants.ATTR_ORDER_BY_TYPE, orderByType);
    
    /* Group filter */
    long selectedGroup = ParamUtil.getLong(request, ToutPortletConstants.ATTR_GROUP_FILTER);
    
    if (selectedGroup == ToutPortletConstants.UNDEFINED_GROUP_ID){
        selectedGroup = company.getGroupId();
    }

    List<Group>  availableGroups = GroupLocalServiceUtil.getGroups(company.getCompanyId(), GroupConstants.DEFAULT_PARENT_GROUP_ID, true);
    
    PortletURL iteratorURL = renderResponse.createRenderURL();
    iteratorURL.setParameter(ToutPortletConstants.JSP_PAGE, ToutPortletConstants.TEMPLATE_ARTICLE_SELECTOR);
    iteratorURL.setParameter(ToutPortletConstants.ATTR_GROUP_FILTER, String.valueOf(selectedGroup));

%>

<liferay-ui:icon-menu cssClass="select-existing-selector" direction="right" icon='<%= themeDisplay.getPathThemeImages() + "/common/attributes.png" %>' message="tout-change-site-group" showWhenSingleIcon="true">

<%

 for (Group group : availableGroups) {

     if (group.getGroupId() != selectedGroup){
         PortletURL changeGroupUrl = renderResponse.createRenderURL();
         changeGroupUrl.setParameter(ToutPortletConstants.JSP_PAGE, ToutPortletConstants.TEMPLATE_ARTICLE_SELECTOR);
         changeGroupUrl.setParameter(ToutPortletConstants.ATTR_GROUP_FILTER, String.valueOf(group.getGroupId() ));
%>
     <liferay-ui:icon
         id='<%= "scope" + group.getGroupId() %>'
         message="<%= group.getScopeDescriptiveName(themeDisplay) %>"
         method="get"
         src="<%= group.getIconURL(themeDisplay) %>"
         url="<%= changeGroupUrl.toString() %>"
     />
<%
     }
  }
%>
</liferay-ui:icon-menu>

<div id="<portlet:namespace/>container">

	<liferay-ui:search-container delta="<%=ToutPortletConstants.TABLE_NUM_ROWS%>" orderByCol="${orderByCol}" orderByType="${orderByType}" iteratorURL="<%= iteratorURL %>" emptyResultsMessage="empty-articles-message">
	    
	    <liferay-ui:search-container-results
        total='<%=JournalArticleLocalServiceUtil.searchCount(company.getCompanyId(), selectedGroup, 
                java.util.Collections.EMPTY_LIST, JournalArticleConstants.CLASSNAME_ID_DEFAULT, null /*keywords*/,
                null /*version*/, null /*null*/, null /*ddmStructureKey*/, null /*ddmTemplateKey*/, null /*displayDateGT*/, 
                null /*displayDateLT*/, WorkflowConstants.STATUS_APPROVED, null /*reviewDate*/) %>'
        results='<%=JournalArticleLocalServiceUtil.search(company.getCompanyId(), selectedGroup, 
                java.util.Collections.EMPTY_LIST, JournalArticleConstants.CLASSNAME_ID_DEFAULT, null /*keywords*/,
                null /*version*/, null /*null*/, null /*ddmStructureKey*/, null /*ddmTemplateKey*/, null /*displayDateGT*/, 
                null /*displayDateLT*/, WorkflowConstants.STATUS_APPROVED, null /*reviewDate*/,
                searchContainer.getStart(), searchContainer.getEnd(), 
                OrderByComparatorFactoryUtil.create("JournalArticle", orderByCol, orderByType.equals(ToutPortletConstants.ATTR_ORDER_ASC )))%>'
    />
	
	    <liferay-ui:search-container-row
	        className="com.liferay.portlet.journal.model.JournalArticle"
	        keyProperty="articleId"
	        modelVar="content">
	        <%
	            Group group = GroupLocalServiceUtil.getGroup(content.getGroupId());
	        %>
	        
	        <portlet:renderURL var="articleViewerContentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			    <portlet:param name="<%=ToutPortletConstants.JSP_PAGE%>" value="<%=ToutPortletConstants.TEMPLATE_ARTICLE_VIEWER%>"/>
			    <portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ARTICLE_ID%>" value="${content.articleId}"/>
			    <portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID%>" value="${content.groupId}"/>
			</portlet:renderURL>
	
	        <liferay-ui:search-container-column-text
	                name="article-group-name" value="<%=group.getDescriptiveName()%>" orderable="true" orderableProperty="groupId"/>
	        <liferay-ui:search-container-column-text
	                name="article-name" property="titleCurrentValue" orderable="true" orderableProperty="urlTitle"/>
	        <liferay-ui:search-container-column-text
	                name="article-mod-date" property="modifiedDate" orderable="true" orderableProperty="modifiedDate"/>
	        <liferay-ui:search-container-column-text name="" >
	            <a id="view${selectedArticle.articleId}" href="${articleViewerContentURL}" class="articleSelectorPreviewLink">
                <liferay-ui:message key="tout-view-article"/></a>
	        </liferay-ui:search-container-column-text>
	        <liferay-ui:search-container-column-text name="article-selection">
	            <input type="button" name="selectedButton${content.id}"
	                   id="${content.articleId}__${content.groupId}"
	                   value="<%= LanguageUtil.get(pageContext, "tout-select") %>"
	                   class="article-selector-row-button" />
	        </liferay-ui:search-container-column-text>
	
	    </liferay-ui:search-container-row>
	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>"/>
	</liferay-ui:search-container>
</div>

<aui:script use="tout-config">
    A.all('#${pns}container .article-selector-row-button').on('click', A.ToutConfig.handleSelectAction, null, '${portletId}', '${pns}container');
    A.delegate('click', A.ToutConfig.articleSelectorPreviewHandler, ".table-data", "a.articleSelectorPreviewLink");
</aui:script>