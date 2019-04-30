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
 *
 */
'use strict';

angular.module('flowableModeler').controller('FlowableAssignmentCtrl', [ '$scope', '$modal', function($scope, $modal) {

    // Config for the modal window
    var opts = {
        template:  'editor-app/configuration/properties/assignment-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    _internalCreateModal(opts, $modal, $scope);
}]);

angular.module('flowableModeler').controller('FlowableAssignmentPopupCtrl',
    [ '$rootScope', '$scope', '$translate', '$http', 'UserService', 'GroupService', function($rootScope, $scope, $translate, $http, UserService, GroupService) {
    	
    // Put json representing assignment on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.assignment !== undefined
        && $scope.property.value.assignment !== null) {
        $scope.assignment = $scope.property.value.assignment;
        if (typeof $scope.assignment.type === 'undefined') {
            $scope.assignment.type = 'static';
        }
    }else {
    	$scope.assignment = {
    			type:'static',
    			assignee: undefined
    	};
    }

    $scope.popup = {
        assignmentObject: {
            type:$scope.assignment.type,
            static: {
                assignee: undefined
            }
        }
    };


    $scope.assignmentOptions = [
        {id: "user", title: $translate.instant('PROPERTY.ASSIGNMENT.IDM.DROPDOWN.USER')},
        {id: "users", title: $translate.instant('PROPERTY.ASSIGNMENT.IDM.DROPDOWN.USERS')}
    ];

    //fill the static area
    if ($scope.assignment.assignee) {
        $scope.popup.assignmentObject.static.assignee = $scope.assignment.assignee;
    }

    $scope.allSteps = EDITOR.UTIL.collectSortedElementsFromPrecedingElements($scope.selectedShape);

    $scope.save = function () {
        handleAssignmentInput($scope.popup.assignmentObject.static);
        $scope.assignment.type = $scope.popup.assignmentObject.type;
        $scope.assignment.idm = undefined;
        $scope.assignment.assignee = '${userids}';
        $scope.property.value = {};
        $scope.property.value.assignment = $scope.assignment;
        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    // Close button handler
    $scope.close = function() {
    	$scope.property.mode = 'read';
    	$scope.$hide();
    };

    var handleAssignmentInput = function ($assignment) {
    
        function isEmptyString(value) {
          return (value === undefined || value === null || value.trim().length === 0);
        }
    
        if (isEmptyString($assignment.assignee)){
          $assignment.assignee = undefined;
        }
        var toRemoveIndexes;
        var removedItems=0;
        var i = 0;
        if ($assignment.candidateUsers) {
          toRemoveIndexes = [];
          for (i = 0; i < $assignment.candidateUsers.length; i++) {
            if (isEmptyString($assignment.candidateUsers[i].value)) {
              toRemoveIndexes[toRemoveIndexes.length] = i;
            }
          }
    
          if (toRemoveIndexes.length == $assignment.candidateUsers.length) {
            $assignment.candidateUsers = undefined;
          } else {
             removedItems=0;
            for (i = 0; i < toRemoveIndexes.length; i++) {
              $assignment.candidateUsers.splice(toRemoveIndexes[i]-removedItems, 1);
              removedItems++;
            }
          }
        }
    };
}]);