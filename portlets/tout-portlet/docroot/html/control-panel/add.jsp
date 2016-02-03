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

<liferay-ui:error message="<%=ToutPortletConstants.ERROR_STORING_PREFERENCES %>" key="<%=ToutPortletConstants.ERROR_STORING_PREFERENCES %>"/>
<liferay-ui:error message="<%=ToutPortletConstants.ERROR_BAD_PARAMETER_REGEX %>" key="<%=ToutPortletConstants.ERROR_BAD_PARAMETER_REGEX %>"/>

<%
ToutConfig toutConfig = null;
if(null != request.getAttribute(ToutPortletConstants.TOUT_CONFIG)){
	toutConfig = (ToutConfig)request.getAttribute(ToutPortletConstants.TOUT_CONFIG);
}
List<Group>  availableGroups = GroupLocalServiceUtil.getGroups(company.getCompanyId(), GroupConstants.DEFAULT_PARENT_GROUP_ID, true);
%>

<portlet:actionURL name="saveToutPreferences" var="savePreferencesURL">
	<portlet:param name="<%= WebKeys.REDIRECT %>" value="<%= redirect %>"/>
	<portlet:param name="<%=ToutPortletConstants.ACTION%>" value="<%=request.getParameter(ToutPortletConstants.ACTION)%>"/>
	<portlet:param name="<%= ToutPortletConstants.JSP_PAGE %>" value="<%= ToutPortletConstants.TEMPLATE_ADD_TOUT_CONFIG %>" />
	<portlet:param name="<%= ToutPortletConstants.ATTR_TOUT_ID %>" value="<%= toutConfig.getId() %>" />
</portlet:actionURL>

<portlet:resourceURL var="getPagesMatchingRegexURL" >
 <portlet:param name="<%= ToutPortletConstants.CMD%>" value="test"/>
</portlet:resourceURL>

<portlet:actionURL name="getPagesMatchingRegex" var="getPagesMatchingRegexURL2">
	<portlet:param name="<%=ToutPortletConstants.ACTION%>" value="<%=request.getParameter(ToutPortletConstants.ACTION)%>"/>
	<portlet:param name="<%= ToutPortletConstants.JSP_PAGE %>" value="<%= ToutPortletConstants.TEMPLATE_ADD_TOUT_CONFIG %>" />
	<portlet:param name="<%= ToutPortletConstants.ATTR_TOUT_ID %>" value="<%= toutConfig.getId() %>" />
</portlet:actionURL>

<portlet:renderURL var="articleSelectorContentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
    <portlet:param name="<%=ToutPortletConstants.JSP_PAGE%>" value="<%=ToutPortletConstants.TEMPLATE_ARTICLE_SELECTOR%>"/>
</portlet:renderURL>

<portlet:renderURL var="viewToutConfigURL" />

<liferay-ui:header
	backURL="<%= viewToutConfigURL %>"
	title="<%=request.getParameter(ToutPortletConstants.ACTION)%>"
/>
<aui:form onSubmit='A.ToutConfig.handleFormSubmit();' name="tout-fm" action="${savePreferencesURL}" method="post">
    <aui:fieldset label="tout-selec-title">
    <aui:input type="hidden" name="<%= ToutPortletConstants.ATTR_TOUT_ID %>" value='<%= toutConfig != null ? toutConfig.getId() : StringPool.BLANK %>'/>
        <div>
            <aui:input type="checkbox" value="<%= toutConfig != null ? toutConfig.isEnabled() : false %>" name="<%=ToutPortletConstants.ATTR_TOUT_ENABLED%>" label="tout-enabled-label"/>
        </div>
        <liferay-ui:panel cssClass="tout-control-panel" collapsible="false" extended="false" id="toutValuesPanel" persistState="true" title="">

            <%--Know more text input--%>
            <aui:input name="<%=ToutPortletConstants.ATTR_TOUT_URL%>" type="text" value="<%=toutConfig != null ? toutConfig.getShowMoreURL() : StringPool.BLANK %>" label="tout-know-more-label">
                <aui:validator name="required" />
            </aui:input>

            <%--days before reminder text input--%>
            <aui:input name="<%=ToutPortletConstants.ATTR_TOUT_DAYS_BEFORE_REMINDER%>" value="<%=toutConfig != null ? toutConfig.getDaysBeforeReminder() : 0 %>" type="text" label="tout-days-remider-label">
                <aui:validator name="required" />
                <aui:validator name="digits" />
            </aui:input>
            <aui:select label="tout-select-sites-label" name="sites" id="sites" size="<%=availableGroups.size() -1 %>" multiple="true">
            <%

			 for (Group group : availableGroups) {
			         PortletURL changeGroupUrl = renderResponse.createRenderURL();
			         changeGroupUrl.setParameter(ToutPortletConstants.JSP_PAGE, ToutPortletConstants.TEMPLATE_ARTICLE_SELECTOR);
			         changeGroupUrl.setParameter(ToutPortletConstants.ATTR_GROUP_FILTER, String.valueOf(group.getGroupId() ));
					boolean selected = null != toutConfig && 
							null != toutConfig.getSites() &&
									toutConfig.getSites().contains(group.getGroupId());
					if( group.getGroupId() != company.getGroupId()){
			%>
			<aui:option selected="<%= selected %>" value="<%= group.getGroupId() %>" cssClass="site"><%=group.getScopeDescriptiveName(themeDisplay)%></aui:option> 
			<%
					}
			 }
			%>
			</aui:select>
            <aui:input id="tout-pagesRegexInput" name="<%=ToutPortletConstants.ATTR_TOUT_PAGES%>" type="text" value="<%=toutConfig != null ? toutConfig.getPagesRegex() : StringPool.BLANK %>" label="tout-pages-label">
            </aui:input>
            <aui:button id="${pns}tout-getPagesMatchingRegexButton" type="button" value="tout-check-pages-regex" cssClass="event-button check-pages-button"/>
            <div class="pages-results">
	            <div id="matching-pages-panel" class="pages-panel tout-panel tout-panel-default" style="display: none;">
	            	<ul><!-- Matching pages list --></ul>
	            </div>
	            <p id="tout-pages-error-msg" class="tout-error-message" style="display: none;"><!-- Pages error message --></p>
            </div>
            <%--add article button and search result--%>
            <div class="control-group error">
            <aui:button id="${pns}tout-selectArticleButton" type="button" value="tout-select-article" cssClass="event-button"/>                      
            <aui:input name ="<%=ToutPortletConstants.ATTR_TOUT_ARTICLE_ID %>" type="hidden" value ="${selectedArticle.articleId}">
                <aui:validator name="required" errorMessage="tout-article-required"/>
            </aui:input></div>  
            <aui:input name = "<%=ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID %>" type="hidden" value ="${selectedArticleGroup.groupId}" />
            
            <div id="${pns}SelectedArticleContainer">
	            <c:if test="${selectedArticle != null}">
		            <%@include file="/html/control-panel/selected_article_display.jspf" %>
	            </c:if>
            </div>
        </liferay-ui:panel>
    </aui:fieldset>
    
    <aui:button id="${pns}tout-submitToutConfigSubmit" type="submit" name="submit" label="" value='tout-save-preferences'/>
</aui:form>

<aui:script use="tout-config">
    A.ToutConfig.setPortletNamespace('${pns}');
    A.one('#${pns}tout-fm input[type=checkbox]').on('click', A.ToutConfig.handleDisableAction );
    A.one('#${pns}tout-selectArticleButton').on('click', A.ToutConfig.displayArticles, null, '${articleSelectorContentURL}' );
    A.one('#${pns}tout-getPagesMatchingRegexButton').on('click', A.ToutConfig.displayMatchingPages, null, '${portletId}', '<%=ToutPortletConstants.CMD_SHOW_MATCHING_PAGES%>');
    A.all('a.articleSelectorPreviewLink').on('click', A.ToutConfig.articleSelectorPreviewHandler);
    <% if(toutConfig != null && null != toutConfig.getPagesRegex() && !toutConfig.getPagesRegex().isEmpty()){ %>
    A.ToutConfig.displayMatchingPages(null, '${portletId}', '<%=ToutPortletConstants.CMD_SHOW_MATCHING_PAGES%>');
    <%}%>
</aui:script>
