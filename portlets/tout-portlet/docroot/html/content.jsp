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

<% String toutId = request.getAttribute(ToutPortletConstants.ATTR_TOUT_ID).toString();%>

<portlet:resourceURL var="dismissedURL">
	<portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ACTION%>" value="<%= UserActionsEnum.DISMISSED.name() %>"/>
	<portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_SHOW_ARTICLE_ID%>" value="${article.id}"/>
	<portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ID%>" value="<%=toutId%>"/>
</portlet:resourceURL>

<portlet:resourceURL var="remindLaterURL">
	<portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ACTION%>" value="<%= UserActionsEnum.REMINDLATER.name() %>"/>
	<portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_SHOW_ARTICLE_ID%>" value="${article.id}"/>
	<portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ID%>" value="<%=toutId%>"/>
</portlet:resourceURL>

<portlet:resourceURL var="learnMoreURL">
    <portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ACTION%>" value="<%= UserActionsEnum.REVIEWED.name() %>"/>
    <portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_SHOW_ARTICLE_ID%>" value="${article.id}"/>
    <portlet:param name="<%=ToutPortletConstants.ATTR_TOUT_ID%>" value="<%=toutId%>"/>
</portlet:resourceURL>

<div id="${pns}ToutCtn" class="tout-ctn">

    <div class="tout-content" id="${article.articleId}" >
        <c:choose>
            <c:when test="${not empty article.articleId}">
                <liferay-ui:journal-article groupId="${article.groupId}"  articleId="${article.articleId}" />
            </c:when>
            <c:otherwise>
                <div class="portlet-msg-info">
                    <liferay-ui:message key="no-tout-to-display"/>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="clearfix"></div>
    <br/>
    <div id="tout-buttons" class="tout-buttons ">
        <aui:button class="btn" cssClass="tout-action-button btn-primary" name="dismissButton"
                    value="tout-no-thanks" onClick="event.preventDefault();" url="<%= dismissedURL %>" />
        <aui:button class="btn" cssClass="tout-action-button btn-primary" name="remindLaterButton"
                    value="tout-remind-later" onClick="event.preventDefault();" url="<%= remindLaterURL %>" />
        <aui:button class="btn" cssClass="tout-action-button btn-primary" name="learnMoreButton"
                    value="tout-learn-more" onClick="event.preventDefault();" url="<%= learnMoreURL  %>"/>
    </div>
</div>
<aui:script use="toutDisplay">
    var tout = new A.Tout({
        container: A.one('#${pns}ToutCtn'),
        portletId: '${portletId}',
        portletNamespace: '${pns}',
        showToutPopUp: ${showTout},
        windowTitle: '${article.titleCurrentValue}',
        learnMoreURL: '${toutUrl}'
    });
</aui:script>