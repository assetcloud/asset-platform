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

angular.module('flowableModeler').controller('FlowableOrgCtrl', [ '$scope', '$modal', function($scope, $modal) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/orgs-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    _internalCreateModal(opts, $modal, $scope);
}]);

angular.module('flowableModeler').controller('FlowableOrgPopupCtrl',
    [ '$rootScope', '$scope', '$translate', '$http', 'UserService', 'GroupService', function($rootScope, $scope, $translate, $http, UserService, GroupService) {

        if ($scope.property.orgnames !== undefined && $scope.property.orgnames !== null) {
            $scope.orgnames = $scope.property.orgnames;
        }else {
            $scope.property.orgnames = {};
            $scope.property.orgids = {};
        }

        $scope.save = function () {
            $scope.property.value = {};
            $scope.property.value.orgnames = jQuery('#forgnames').val();
            $scope.property.value.orgids = jQuery('#forgids').val();
            $scope.updatePropertyInModel($scope.property);
            $scope.close()
        };

        // Close button handler
        $scope.close = function() {
            $scope.property.mode = 'read';
            $scope.$hide();
        };
}]);