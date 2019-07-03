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

angular.module('flowableModeler').controller('FlowableRoleCtrl', [ '$scope', '$modal', function($scope, $modal) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/roles-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    _internalCreateModal(opts, $modal, $scope);
}]);

angular.module('flowableModeler').controller('FlowableRolePopupCtrl',
    [ '$rootScope', '$scope', '$translate', '$http', 'UserService', 'GroupService', function($rootScope, $scope, $translate, $http, UserService, GroupService) {

        if ($scope.property.value.roles) {
            $scope.roles = $scope.property.value.roles;
        }else {
            $scope.property.roles = [];
        }

        $scope.save = function () {
            $scope.property.value = {};
            if($scope.roles.length > 0) {
                var roles = jQuery.map($scope.roles, function(v, i){
                    return { id: v.id, name: v.name };
                });
                $scope.property.value.roles = roles;
            } else {
                $scope.property.value = null;
            }

            $scope.updatePropertyInModel($scope.property);
            $scope.close()
        };

        // Close button handler
        $scope.close = function() {
            $scope.property.mode = 'read';
            $scope.$hide();
        };

        var selectedRols = [];
        /**
         * 将左侧选中的加入右侧列表
         */
        $scope.add = function() {
            $scope.roles = jQuery.map(selectedRols, function(r){
                r.selected = false;
                return r;
            });
        }

        /**
         * 清空右侧列表
         */
        $scope.clean = function() {
            $scope.roles = jQuery.grep($scope.roles, function(r){
                return r.selected === undefined ||  r.selected === false
            });
        }

        /**
         * 全选右侧面板
         */
        $scope.selectAll = function(e) {
            var checked = e.target.checked;
            jQuery.each($scope.roles, function(i, r){
                r.selected = checked;
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
                    var treeObj = jQuery.fn.zTree.getZTreeObj("roleZtree");
                    var nodes = treeObj.getCheckedNodes(true);
                    selectedRols = nodes;
                }
            }
        };

        var mergeData = function(left, right) {
            if(!right || right.length == 0 ) return;
            var rightMaps = {};
            jQuery.each(right, function(i, item){
                rightMaps[item.id] = item;
            });

            jQuery.each(left, function(i, item){
                if(rightMaps[item.id]) {
                    item.checked = true;
                }
            });
        }

        var config = FLOWABLE.CONFIG.outerProps.roles;
        if (config.use) {
            $http.get(config.dataUrl).success(function (data) {
                mergeData(data, $scope.roles);
                jQuery.fn.zTree.init(jQuery("#roleZtree"), setting, data);
            });
        }
}]);