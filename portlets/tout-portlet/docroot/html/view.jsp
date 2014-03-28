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

<portlet:renderURL var="contentURL" windowState="<%=LiferayWindowState.POP_UP.toString() %>">
    <portlet:param name="<%=ToutPortletConstants.JSP_PAGE%>" value="<%=ToutPortletConstants.TEMPLATE_CONTENT_DISPLAY%>"/>
</portlet:renderURL>

<div id="${pns}tout-placeholder"></div>

<aui:script use="toutDisplay">
    var tout = new A.Tout(
    {
        container: A.one('#${pns}tout-placeholder'),
        portletId: '${portletId}',
        portletNamespace: '${pns}',
        contentURL: '${contentURL}',
        showToutPopUp: ${showTout}
    });
</aui:script>