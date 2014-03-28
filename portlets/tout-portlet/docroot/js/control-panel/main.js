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

AUI.add('tout-config', function(A, NAME) {
    
    var ns = A.namespace("ToutConfig");
    var pns = "";
    
    ns.setPortletNamespace = function(namespace){
        pns = namespace;
    };

    ns.drawPopUpWindow = function(title, url) {
        var tipModal = null;
        
        Liferay.Util.openWindow({
            dialog : {
                centered: true,
                cache : false,
                width : 1100,
                height : 600,
                bodyContent : Liferay.Language.get('tout-loading-content') + '...',
                modal : true
            },
            id : 'articles-iframe',
            title : title,
            uri : url
        }, function(modal) {
            tipModal = modal;
            tipModal.on('visibleChange', function() {
                modal.destroy();
                A.all('a.articleSelectorPreviewLink').on('click', ns.articleSelectorPreviewHandler);
                Liferay.Form.get(pns+'tout-fm').formValidator.validate();
            });
        });
    };

    // ------------- ARTICLE SELECTOR DIALOG -------------
    ns.displayArticles = function(e, url) {
        var title = Liferay.Language.get('tout-select-article');
        ns.drawPopUpWindow(title, url);
    };

    ns.handleSelectAction = function(e, portletId, containerId) {
        var buttonNode = e.currentTarget;
        var buttonId = buttonNode.getAttribute("id").split('__');
        var articleId = buttonId[0];
        var groupId = buttonId[1];

        ns.setAsSelected(articleId, groupId, buttonNode, portletId, containerId);
    };
    
    ns.setAsSelected = function(articleId, groupId, buttonNode, portletId, containerId) {

        var resourceURL = Liferay.PortletURL.createResourceURL();
        resourceURL.setPortletId(portletId);
        resourceURL.setParameter('cmd', 'showArticleAsSelected');
        resourceURL.setParameter('selectedArticleId', articleId);
        resourceURL.setParameter('selectedArticleGroupId', groupId);

        var pns = '_' + portletId + '_';
        window.parent.document.getElementById(pns + "selectedArticleId").value = articleId;
        window.parent.document.getElementById(pns + "selectedArticleGroupId").value = groupId;

        A.io.request(resourceURL.toString(), {
            method : 'GET',
            dataType : 'text',
            on : {
                success : function(event, id, obj) {
                    var responseData = this.get('responseData');
                    var selectedContainer = window.parent.document.getElementById(pns + 'SelectedArticleContainer');
                    selectedContainer.innerHTML = responseData;                    

                    // Close iframe
                    var dialog = Liferay.Util.getWindow('articles-iframe');
                    if (dialog != null) {
                        dialog.hide();
                    }
                }
            }
        });

    };

    // --------------------- ARTICLE PREVIEW POPUP WINDOW

    ns.articleSelectorPreviewHandler = function(e) {
        e.preventDefault();
        var instance = e.currentTarget;
        var linkNodeUrl = instance.getAttribute('href');
        var title = Liferay.Language.get('tout-view-article');
        var dialog = Liferay.Util.Window.getWindow({
            dialog : {
                centered : true,
                constrain2view : true,
                width : 980,
                height : 500,
                cache : false,
                stack : true,
                modal : true,
                bodyContent : Liferay.Language.get('tout-loading-content') + '...'
            },
            title : title,
            uri : linkNodeUrl
        }).render();
    };

    // ------------- CONTROL PANEL FIELDS -------------
    ns.setFieldsAvailability = function() {
        var checkboxNode = A.one("input[type=checkbox]");
        if (checkboxNode) {
            var checkboxid = checkboxNode.getAttribute("id");
            checkboxid = checkboxid.replace("Checkbox", "");
            var enabledCheck = A.one("#" + checkboxid).getAttribute("value");

            var inputs = A.all('#toutValuesPanel input');
            var button = A.all('#toutValuesPanel button');
            if (enabledCheck == "true") {
                button.removeAttribute("disabled");
                inputs.each(function(node) {
                    node.removeAttribute("disabled");
                });
            } else {
                button.setAttribute("disabled", true);
                inputs.each(function(node) {
                    node.setAttribute("disabled", true);
                });

            }
        }
    };

    ns.handleDisableAction = function(e) {
        ns.setFieldsAvailability();
    };

    A.on("domready", function() {
        ns.setFieldsAvailability();
    });
}, '@VERSION@', {
    "requires" : [ "yui-base", "base-build", "node", "aui-modal", 'liferay-portlet-url', 'liferay-util-window',
            'aui-io-plugin-deprecated', 'aui-dialog-iframe-deprecated' ]
});
