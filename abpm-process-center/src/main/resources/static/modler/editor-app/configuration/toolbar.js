/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
'use strict';

var FLOWABLE = FLOWABLE || {};
FLOWABLE.TOOLBAR_CONFIG = {
    "items" : [
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.SAVE",
            "name" : "TOOLBAR.ACTION.BUTTON.SAVE",
            "cssClass" : "editor-icon editor-icon-save",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.saveModel"
        },
        {
            "type" : "separator",
            "title" : "TOOLBAR.ACTION.SAVE",
            "cssClass" : "toolbar-separator"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.CUT",
            "name" : "TOOLBAR.ACTION.BUTTON.CUT",
            "cssClass" : "editor-icon editor-icon-cut",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.cut",
            "enabled" : false,
            "enabledAction" : "element"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.COPY",
            "name" : "TOOLBAR.ACTION.BUTTON.COPY",
            "cssClass" : "editor-icon editor-icon-copy",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.copy",
            "enabled" : false,
            "enabledAction" : "element"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.PASTE",
            "name" : "TOOLBAR.ACTION.BUTTON.PASTE",
            "cssClass" : "editor-icon editor-icon-paste",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.paste",
            "enabled" : false
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.DELETE",
            "name" : "TOOLBAR.ACTION.BUTTON.DELETE",
            "cssClass" : "editor-icon editor-icon-delete",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.deleteItem",
            "enabled" : false,
            "enabledAction" : "element"
        },
        {
            "type" : "separator",
            "title" : "TOOLBAR.ACTION.SAVE",
            "cssClass" : "toolbar-separator"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.REDO",
            "name" : "TOOLBAR.ACTION.BUTTON.REDO",
            "cssClass" : "editor-icon editor-icon-redo",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.redo",
            "enabled" : false
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.UNDO",
            "name" : "TOOLBAR.ACTION.BUTTON.UNDO",
            "cssClass" : "editor-icon editor-icon-undo",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.undo",
            "enabled" : false
        },
        {
            "type" : "separator",
            "title" : "TOOLBAR.ACTION.SAVE",
            "cssClass" : "toolbar-separator"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.ALIGNVERTICAL",
            "name" : "TOOLBAR.ACTION.BUTTON.ALIGNVERTICAL",
            "cssClass" : "editor-icon editor-icon-align-vertical",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.alignVertical",
            "enabled" : false,
            "enabledAction" : "element",
            "disableInForm" : true,
            "minSelectionCount" : 2
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.ALIGNHORIZONTAL",
            "name" : "TOOLBAR.ACTION.BUTTON.ALIGNHORIZONTAL",
            "cssClass" : "editor-icon editor-icon-align-horizontal",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.alignHorizontal",
            "enabledAction" : "element",
            "enabled" : false,
            "disableInForm" : true,
            "minSelectionCount" : 2
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.SAMESIZE",
            "name" : "TOOLBAR.ACTION.BUTTON.SAMESIZE",
            "cssClass" : "editor-icon editor-icon-same-size",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.sameSize",
            "enabledAction" : "element",
            "enabled" : false,
            "disableInForm" : true,
            "minSelectionCount" : 2
        },
        {
        	"type" : "separator",
        	"title" : "TOOLBAR.ACTION.SAVE",
        	"cssClass" : "toolbar-separator",
        	"disableInForm" : true
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.ZOOMIN",
            "name" : "TOOLBAR.ACTION.BUTTON.ZOOMIN",
            "cssClass" : "editor-icon editor-icon-zoom-in",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.zoomIn"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.ZOOMOUT",
            "name" : "TOOLBAR.ACTION.BUTTON.ZOOMOUT",
            "cssClass" : "editor-icon editor-icon-zoom-out",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.zoomOut"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.ZOOMACTUAL",
            "name" : "TOOLBAR.ACTION.BUTTON.ZOOMACTUAL",
            "cssClass" : "editor-icon editor-icon-zoom-actual",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.zoomActual"
        },
        {
            "type" : "button",
            "title" : "TOOLBAR.ACTION.ZOOMFIT",
            "name" : "TOOLBAR.ACTION.BUTTON.ZOOMFIT",
            "cssClass" : "editor-icon editor-icon-zoom-fit",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.zoomFit"
        },
        {
            "type" : "separator",
            "title" : "TOOLBAR.ACTION.SAVE",
            "cssClass" : "toolbar-separator",
            "disableInForm" : true
        },
    	{
            "type" : "button",
            "name" : "TOOLBAR.ACTION.BUTTON.BENDPOINT.ADD",
            "title" : "TOOLBAR.ACTION.BENDPOINT.ADD",
            "cssClass" : "editor-icon-old editor-icon-bendpoint-add",
            "action" : "FLOWABLE.TOOLBAR.ACTIONS.addBendPoint",
            "id" : "add-bendpoint-button",
            "disableInForm" : true
    	},
    	{
            "type" : "button",
            "name" : "TOOLBAR.ACTION.BUTTON.BENDPOINT.REMOVE",
    	    "title" : "TOOLBAR.ACTION.BENDPOINT.REMOVE",
    	    "cssClass" : "editor-icon-old editor-icon-bendpoint-remove",
    	    "action" : "FLOWABLE.TOOLBAR.ACTIONS.removeBendPoint",
    	    "id" : "remove-bendpoint-button",
    	    "disableInForm" : true
    	},
    ],
    
    "secondaryItems" : []
};