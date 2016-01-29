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

<%@page import="com.rivetlogic.tout.config.ToutConfig"%>
<%@include file="/html/init.jsp" %>

<% List<ToutConfig> toutConfigList = (ArrayList<ToutConfig>)request.getAttribute("toutConfigList"); %>

<aui:button-row>
	<portlet:renderURL var="addToutConfigURL">
		<portlet:param name="mvcPath" value="/html/control-panel/add.jsp" />
		<portlet:param name="<%=ToutPortletConstants.JSP_PAGE%>" value="/html/control-panel/add.jsp" />
		<portlet:param name="<%=ToutPortletConstants.ACTION%>" value="<%=ToutPortletConstants.ACTION_ADD%>"/>
		<portlet:param name="redirect" value="<%= redirect %>" />
	</portlet:renderURL>

	<aui:button value="add-toutConfig" onClick="<%=addToutConfigURL %>"/>
</aui:button-row>

<!--  a id="view${selectedArticle.articleId}" href="${articleViewerContentURL}" class=articleSelectorPreviewLink-->

<liferay-ui:search-container
	emptyResultsMessage="no-tout-configuration-found">
	<liferay-ui:search-container-results
		results="<%= toutConfigList %>"
		total="<%= toutConfigList.size() %>" />

	<liferay-ui:search-container-row
		className="com.rivetlogic.tout.config.ToutConfig" keyProperty="id"
		modelVar="toutConfig" escapedModel="<%= true %>">

		<liferay-ui:search-container-column-text name="tout-showUrl-column"
			property="showMoreURL" />
			
		<liferay-ui:search-container-column-text name="tout-status-column"
			value="<%=(toutConfig.isEnabled() ? \"Enabled\" : \"Disabled\")%>" />

		<liferay-ui:search-container-column-text name="tout-daysBeforeReminder-column"
			property="daysBeforeReminder" />
		<% 
			String sitesStr = StringPool.BLANK;
			List<Long>sitesIds = toutConfig.getSites();
			if(null != sitesIds && !sitesIds.isEmpty()){
				List<Group>  availableGroups = GroupLocalServiceUtil.getGroups(ArrayUtil.toLongArray(sitesIds));
				if(null != availableGroups && !availableGroups.isEmpty()){
					List<String> sitesNamesList = new ArrayList<String>();
					for(Group group: availableGroups){
						sitesNamesList.add(group.getScopeDescriptiveName(themeDisplay));
					}
					sitesStr = StringUtil.merge(sitesNamesList, ", ");
				}
			}
		%>
		<liferay-ui:search-container-column-text name="tout-sites-column"
			value="<%=sitesStr%>" />
			
		<liferay-ui:search-container-column-text name="tout-pagesRegex-column"
			property="pagesRegex" />
      <%                
      	JournalArticle toutArticle = JournalArticleLocalServiceUtil.getArticle(toutConfig.getArticleGroupId(),
      			toutConfig.getArticleId());
                        %>
      <portlet:renderURL var="articleViewerContentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		    <portlet:param name="<%=ToutPortletConstants.JSP_PAGE%>" value="<%=ToutPortletConstants.TEMPLATE_ARTICLE_VIEWER%>"/>
		    <portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ARTICLE_ID%>" value="<%=String.valueOf(toutConfig.getArticleId())%>"/>
		    <portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ARTICLE_GROUP_ID%>" value="<%=String.valueOf(toutConfig.getArticleGroupId())%>"/>
		</portlet:renderURL>
		
		<liferay-ui:search-container-column-text name="tout-article-column">
			<a id="view<%=String.valueOf(toutConfig.getArticleId())%>" href="${articleViewerContentURL}" class=articleSelectorPreviewLink>
			<%=toutArticle.getTitleCurrentValue()%></a>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-jsp align="right"
			path="/html/control-panel/actions.jsp" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:script use="tout-config">
    A.ToutConfig.setPortletNamespace('${pns}');
    A.all('a.articleSelectorPreviewLink').on('click', A.ToutConfig.articleSelectorPreviewHandler);
</aui:script>