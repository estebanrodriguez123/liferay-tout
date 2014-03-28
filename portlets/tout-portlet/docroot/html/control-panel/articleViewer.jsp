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

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@include file="/html/init.jsp" %>

<div id="${pns}articleViewer" class="tout-article-viewer">

    <div class="tout-articleViewer" >
        <c:choose>
            <c:when test='${not empty displayArticleId}'>
                <liferay-ui:journal-article groupId="${displayArticleGroupId}" articleId="${displayArticleId}" />
            </c:when>
            <c:otherwise>
                <div class="portlet-msg-info">
                    <liferay-ui:message key="no-tout-to-display"/>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

