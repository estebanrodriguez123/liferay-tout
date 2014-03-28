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

<portlet:actionURL name="saveToutPreferences" var="savePreferencesURL">
	<portlet:param name="redirect" value="<%= currentURL %>"/>
</portlet:actionURL>

<portlet:renderURL var="articleSelectorContentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
    <portlet:param name="<%=ToutPortletConstants.JSP_PAGE%>" value="<%=ToutPortletConstants.TEMPLATE_ARTICLE_SELECTOR%>"/>
</portlet:renderURL>


<aui:form name="tout-fm" action="${savePreferencesURL}" method="post">
    <aui:fieldset label="tout-selec-title">
        <div>
            <aui:input type="checkbox" value="${toutEnabled}" name="<%=ToutPortletConstants.ATTR_TOUT_ENABLED%>" label="tout-enabled-label"/>
        </div>
        <liferay-ui:panel cssClass="tout-control-panel" collapsible="false" extended="false" id="toutValuesPanel" persistState="true" title="">

            <%--Know more text input--%>
            <aui:input name="<%=ToutPortletConstants.ATTR_TOUT_URL%>" type="text" value="${toutUrl}" label="tout-know-more-label">
                <aui:validator name="required" />
            </aui:input>

            <%--days before reminder text input--%>
            <aui:input name="<%=ToutPortletConstants.ATTR_TOUT_DAYS_BEFORE_REMINDER%>" value="${toutDaysBeforeReminder}" type="text" label="tout-days-remider-label">
                <aui:validator name="required" />
                <aui:validator name="digits" />
            </aui:input>

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
    
    <aui:button type="submit" name="submit" label="" value='tout-save-preferences'/>
</aui:form>

<aui:script use="tout-config">
    A.ToutConfig.setPortletNamespace('${pns}');
    A.one('#${pns}tout-fm input[type=checkbox]').on('click', A.ToutConfig.handleDisableAction );
    A.one('#${pns}tout-selectArticleButton').on('click', A.ToutConfig.displayArticles, null, '${articleSelectorContentURL}' );
    A.all('a.articleSelectorPreviewLink').on('click', A.ToutConfig.articleSelectorPreviewHandler);
</aui:script>
