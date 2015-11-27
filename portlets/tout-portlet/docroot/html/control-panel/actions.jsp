<%@include file="/html/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
ToutConfig toutConfig = (ToutConfig)row.getObject();
String toutConfigId = toutConfig.getId();
%>

<liferay-ui:icon-menu >
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="<%= ToutPortletConstants.TEMPLATE_ADD_TOUT_CONFIG %>" />
		<portlet:param name="<%= ToutPortletConstants.JSP_PAGE %>" value="<%= ToutPortletConstants.TEMPLATE_ADD_TOUT_CONFIG %>" />
		<portlet:param name="<%= ToutPortletConstants.ATTR_TOUT_ID %>" value="<%= String.valueOf(toutConfigId) %>"/>
		<portlet:param name="<%= ToutPortletConstants.ACTION %>" value="<%= ToutPortletConstants.ACTION_EDIT %>"/>
		<portlet:param name="redirect" value="<%= redirect %>"/>
	</portlet:renderURL>

	<liferay-ui:icon image="edit" url="<%=editURL.toString() %>" />

	<portlet:actionURL name="deleteToutConfig" var="deleteURL">
		<portlet:param name="<%= ToutPortletConstants.ATTR_TOUT_ID %>" value="<%= String.valueOf(toutConfigId) %>"/>
		<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>"/>
	</portlet:actionURL>
		
	<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
</liferay-ui:icon-menu>