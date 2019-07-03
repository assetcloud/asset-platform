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

/*
 * Assignment
 */
'use strict';

angular.module('flowableModeler').controller('FlowableUsernamesCtrl', ['$scope', '$modal', function ($scope, $modal) {

    // Config for the modal window
    var opts = {
        template: 'editor-app/configuration/properties/users-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    _internalCreateModal(opts, $modal, $scope);
}]);


angular.module('flowableModeler').controller('FlowableUsernamesPopupCtrl',
    ['$rootScope', '$scope', '$translate', '$http', 'UserService', 'GroupService', function ($rootScope, $scope, $translate, $http, UserService, GroupService) {
        // Put json representing assignment on scope
        if ($scope.property.value.users) {
            $scope.users = $scope.property.value.users;
        } else {
            $scope.users = [];
        }

        var selectedUsers = [];
        /**
         * 将左侧选中的人员加入右侧列表
         */
        $scope.addUsers = function() {
            $scope.users = jQuery.grep(selectedUsers, function(u){ 
                return u.type == 'person' 
            });
        }

        /**
         * 清空右侧列表
         */
        $scope.cleanUsers = function() {
            $scope.users = jQuery.grep($scope.users, function(u){
                return u.selected === undefined ||  u.selected === false
            });
        }

        /**
         * 全选右侧面板选择人员
         */
        $scope.selectAll = function(e) {
            var checked = e.target.checked;
            jQuery.each($scope.users, function(i, u){
                u.selected = checked;
            });
        }

        // ztree设置
        var setting = {
            check: {
                enable: true
            }, view: {
                dblClickExpand:  function (treeId, treeNode) {
                    return treeNode.level > 0;
                }
            }, data: {
                simpleData: {
                    enable: true
                }
            }, callback: {
                onCheck:  function (event, treeId, treeNode) {
                    var treeObj = jQuery.fn.zTree.getZTreeObj("userZtree");
                    var nodes = treeObj.getCheckedNodes(true);
                    selectedUsers = nodes;
                }
            }
        };

        var userConfig = FLOWABLE.CONFIG.outerProps.users;
        if (userConfig.use) {
            $http.get(userConfig.dataUrl).success(function (data) {
                jQuery.fn.zTree.init(jQuery("#userZtree"), setting, data);
            });
        }

        $scope.save = function () {
            $scope.property.value = {};

            if($scope.users.length > 0) {
                var users = jQuery.map($scope.users, function(v, i){
                    return { id: v.id, name: v.name };
                });
                var userids = jQuery.map($scope.users, function(v, i){
                    return v.id;
                }).join(',');
                $scope.property.value.users = users;
                $scope.property.value.userids = userids;
            } else {
                $scope.property.value = null;
            }

            $scope.updatePropertyInModel($scope.property);
            $scope.close()
        };

        // Close button handler
        $scope.close = function () {
            $scope.property.mode = 'read';
            $scope.$hide();
        };
    }]);