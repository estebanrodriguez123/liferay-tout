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

AUI.add('toutDisplay', function (A, NAME) {

    A.Tout = A.Base.create('tout', A.Base, [], {
        portletId : null,
        portletNamespace: null,
        modal: null,
        contentURL: null,
        windowTitle: "",
        showToutPopUp: false,
        learnMoreURL: null,
        articleId: null,

        initializer: function(){
            var contentNode = this.get('container');
            this.portletId = this.get('portletId');
            this.portletNamespace = this.get('portletNamespace');
            this.windowTitle = (this.get('windowTitle')!="")?this.get('windowTitle'): '&nbsp;';
            this.contentURL = this.get('contentURL');
            this.learnMoreURL = this.get('learnMoreURL');
            if (this.get('showToutPopUp')) {
                this.showPopUp();
            }
            if (contentNode){
                this.setInnerContent();
                
                if(contentNode.one(".tout-content")){
                    
                    var instance = this;
                    
                    contentNode.all(".tout-action-button").on('click', function(e){
                        
                        e.preventDefault();
                        
                        var target = e.currentTarget;
                        var buttonId = target.getAttribute('id');
                        var resourceURL = target.getAttribute('url');
                        
                        A.io.request(resourceURL, {
                            method : 'get',
                            dataType : 'json',
                            on : {
                                success : function(event, id, obj) {
                                    var responseData = this.get('responseData');
                                    if (!responseData.responseOK){
                                      // Not much left to do at this point
                                    }
                                    
                                    if (buttonId == instance.portletNamespace+'learnMoreButton'){
                                        window.open(instance.learnMoreURL);
                                    }
                                    
                                    Liferay.fire('closeWindow',{id: instance.portletId});
                                    
                                }
                            }
                        });
                    });
                    
                }
            }
            this.setScrollHeight();
        },

        showPopUp: function() {
            var instance = this;
            Liferay.Util.openWindow(
                {
                    dialog: {
                        centered: true,
                        constrain2view: true,
                        cssClass: 'tout-content-iframe',
                        resizable: false,
                        destroyOnHide: true
                    },
                    xy: ['center', 'center'],
                    id: instance.portletId,
                    title: this.windowTitle,
                    uri: instance.contentURL
        }, function(modal) {
                    instance.modal = modal;
                    A.one('body').addClass('tout-opened');
                    var closingX = instance.modal.get('boundingBox').one('.close');
                    if(instance.modal.get('bodyContent').size()>0){
                        closingX.on('click', function()
                        {
                            //same behaviour as "remindMeLater" when closing the dialog
                            A.one('#'+instance.portletNamespace+'remindLaterButton').simulate("click");
                        });
                    }
                    instance.modal.on('destroy', function() {
                        A.one('body').removeClass('tout-opened');
                    });
                }
            );
        },

        /*Resizes the pop up when the content changes*/
        setInnerContent : function() {
            var instance = this;
            Liferay.provide(window, 'setInnerContent', function(heigth, contentDiv, buttonsDiv, body) {
                var dialog = {
                    bodyNode: A.one(".modal:visible"),
                    contentNode: contentDiv,
                    buttonsNode: buttonsDiv,
                    headerNode: A.one(".modal-header"),
                    body: body
                };
                instance.setContentHeight(dialog, heigth);
            });
        },

        setContentHeight: function(self, height) {
            var instance = this;
            var visibleAreaHeight = self.body.get('winHeight');
            var gap_size = 80;
            var padding = 30;
            // if (Liferay.Browser.isFirefox()) {
            //     padding = 20;
            // }
            var buttonBarHeight = self.buttonsNode.height(); 
            var headerHeight = self.headerNode.height(); 

            //var cHeight = (height + buttonBarHeight + headerHeight + padding);
            //cHeight = (cHeight > (visibleAreaHeight - gap_size))
                // ? (visibleAreaHeight  - gap_size)
                // : cHeight;
            //instance.modal.set('height', cHeight);
            //instance.modal.bodyNode.get('firstChild').set('height', cHeight);
            

            if(self.contentNode){
                var maxContentHeight = visibleAreaHeight - headerHeight - buttonBarHeight - padding;
                // if (Liferay.Browser.isFirefox()) {
                //     maxContentHeight = maxContentHeight - padding;
                // }
                self.contentNode.setStyle('height', '');
                instance.modal.set('height', A.one('body').get('winHeight'));
                if (self.contentNode.height() > maxContentHeight){
                    self.contentNode.setStyle('height',maxContentHeight+'px');
                } else {
                    instance.modal.set('height', self.contentNode.height() + headerHeight + buttonBarHeight + padding + gap_size);
                }
                instance.modal.align();
            }
        },

        setScrollHeight: function() {
            A.on("domready", function(e){
                var contentPopUp = A.one('.tout-ctn');
                if (contentPopUp) {
                    var buttonsDiv = A.one(".tout-buttons");
                    var contentDiv = A.one(".tout-content");
                    window.parent.setInnerContent(contentPopUp.get('scrollHeight'), contentDiv, buttonsDiv, A.one('body'));
                    var t = null;
                    var resize = function() {
                        window.parent.setInnerContent(contentPopUp.get('scrollHeight'), contentDiv, buttonsDiv, A.one('body'));
                        setTimeout(function() {
                            A.once('resize', function() {
                                resize();
                            });
                        }, 50);
                    };
                    A.once('resize', function() {
                        resize();
                    });
                }
            });
        }

    }, {
        ATTRS: {
            container: {
                value: null
            },
            portletId: {
                value: null
            },
            portletNamespace: {
                value: null
            },
            contentURL: {
                value: null
            },
            windowTitle: {
                value: ""
            },
            showToutPopUp: {
                value: false
            },
            learnMoreURL: {
                value: null
            }
        }
    });

}, '@VERSION@', {
    "requires": ["yui-base", "base-build", "node", "event", "liferay-portlet-url"]
});
