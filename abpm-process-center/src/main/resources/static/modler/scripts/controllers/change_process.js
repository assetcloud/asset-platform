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
angular.module('flowableModeler')
    .controller('ChangeProcessCtrl', ['$rootScope', '$scope', '$translate', '$http', '$location', '$routeParams', '$modal', '$popover', '$timeout', 'appResourceRoot', 'ResourceService',
        function ($rootScope, $scope, $translate, $http, $location, $routeParams, $modal, $popover, $timeout, appResourceRoot, ResourceService) {

            // Main page (needed for visual indicator of current page)
            $rootScope.setMainPageById('processes');

            // Initialize model
            $scope.model = {
                // Store the main model id, this points to the current version of a model,
                // even when we're showing history

                latestModelId: $routeParams.modelId
            };

            $scope.loadProcess = function () {
                var newVersionUrl;
                var oldVersionUrl;

                if ($routeParams.modelHistoryId) {
                    newVersionUrl = FLOWABLE.APP_URL.getModelUrl($routeParams.modelId);
                    +'/history/' + $routeParams.modelHistoryId;
                } else {
                    //应该访问的是这里的内容
                    newVersionUrl = FLOWABLE.APP_URL.getModelUrl($routeParams.modelId);
                }

                //如果访问该URL成功，返回信息，这里是执行：
                //访问最新版本的流程定义，然后填充内容，新版本的放在上面，旧版本的放在下面
                $http({method: 'GET', url: newVersionUrl}).success(function (data, status, headers, config) {
                    $scope.model.process = data;

                    $scope.loadVersions();


                    $scope.model.bpmn20DownloadUrl = $routeParams.modelHistoryId == undefined ?
                        FLOWABLE.APP_URL.getModelBpmn20ExportUrl($routeParams.modelId) :
                        FLOWABLE.APP_URL.getModelHistoryBpmn20ExportUrl($routeParams.modelId, $routeParams.modelHistoryId);

                    $rootScope.$on('$routeChangeStart', function (event, next, current) {
                        jQuery('.qtip').qtip('destroy', true);
                    });


                    //change_process.html页面即流程迁移页面，当前方法是读取  旧版本  的流程定义图示
                    $timeout(function () {
                        jQuery("#bpmnModel").attr('data-model-id', $routeParams.modelId);
                        jQuery("#bpmnModel").attr('data-model-type', 'design');

                        // in case we want to show a historic model, include additional attribute on the div
                        if (!$scope.model.process.latestVersion) {
                            jQuery("#bpmnModel").attr('data-history-id', $routeParams.modelHistoryId);
                        }

                        var viewerUrl = appResourceRoot + "display/displaymodel.html?version=" + Date.now();

                        // If Flowable has been deployed inside an AMD environment Raphael will fail to register
                        // itself globally until displaymodel.js (which depends ona global Raphale variable) is running,
                        // therefore remove AMD's define method until we have loaded in Raphael and displaymodel.js
                        // and assume/hope its not used during.
                        var amdDefine = window.define;
                        window.define = undefined;
                        ResourceService.loadFromHtml(viewerUrl, function () {
                            // Restore AMD's define method again
                            window.define = amdDefine;
                        });
                    });
                    console.log(2);

                }).error(function (data, status, headers, config) {
                    alert("error");
                    console.log("error");
                    $scope.returnToList();
                });


            };

            $scope.loadProcess2 = function () {
                //这里先获取旧版本流程定义的流程ID
                var lastestVersion = $scope.model.process.version;
                var oldVersion = 1;
                var oldVersionModelID = 0;

                console.log(1);

                if (lastestVersion != 1) {
                    oldVersion = lastestVersion - 1;
                } else {
                    oldVersion = 1;
                }

                //遍历历史流程
                var datas = $scope.model.versions.data;
                for (var p in datas) {
                    if (datas[p].version == oldVersion) {
                        oldVersionModelID = datas[p].id;
                        break;
                    }
                }

                //接着把oldVersionID填充进去就可以了
                if ($routeParams.modelHistoryId) {
                    oldVersionUrl = FLOWABLE.APP_URL.getModelUrl(oldVersionModelID);
                    +'/history/' + $routeParams.modelHistoryId;
                } else {
                    //应该访问的是这里的内容
                    oldVersionUrl = FLOWABLE.APP_URL.getModelUrl(oldVersionModelID);
                }

                // 上边是获取最新版本的流程定义，这里是获取旧版本的流程定义（先获取最新版本是哪个version,然后减1就是上个版本）
                //change_process.html页面即流程迁移页面，当前方法是读取  旧版本  的流程定义图示
                $http({method: 'GET', url: oldVersionUrl}).success(function (data, status, headers, config) {
                    $scope.model.process = data;

                    console.log(123);
                    console.log(data);

                    $scope.loadVersions();

                    $scope.model.bpmn20DownloadUrl = $routeParams.modelHistoryId == undefined ?
                        FLOWABLE.APP_URL.getModelBpmn20ExportUrl($routeParams.modelId) :
                        FLOWABLE.APP_URL.getModelHistoryBpmn20ExportUrl($routeParams.modelId, $routeParams.modelHistoryId);

                    $rootScope.$on('$routeChangeStart', function (event, next, current) {
                        jQuery('.qtip').qtip('destroy', true);
                    });

                    //change_process.html页面即流程迁移页面，当前方法是读取  旧版本  的流程定义图示
                    $timeout(function () {
                        jQuery("#bpmnModelOld").attr('data-model-id', $routeParams.modelId);
                        jQuery("#bpmnModelOld").attr('data-model-type', 'design');

                        // in case we want to show a historic model, include additional attribute on the div
                        if (!$scope.model.process.latestVersion) {
                            jQuery("#bpmnModelOld").attr('data-history-id', $routeParams.modelHistoryId);
                        }

                        var viewerUrl = appResourceRoot + "display/displaymodel.html?version=" + Date.now();

                        // If Flowable has been deployed inside an AMD environment Raphael will fail to register
                        // itself globally until displaymodel.js (which depends ona global Raphale variable) is running,
                        // therefore remove AMD's define method until we have loaded in Raphael and displaymodel.js
                        // and assume/hope its not used during.
                        var amdDefine = window.define;
                        window.define = undefined;
                        ResourceService.loadFromHtml(viewerUrl, function () {
                            // Restore AMD's define method again
                            window.define = amdDefine;
                        });
                    });

                }).error(function (data, status, headers, config) {
                    $scope.returnToList();
                });
            }


            $scope.useAsNewVersion = function () {
                _internalCreateModal({
                    template: 'views/popup/model-use-as-new-version.html',
                    scope: $scope
                }, $modal, $scope);
            };


            //这里从数据库中读取流程定义的所有版本信息
            $scope.loadVersions = function () {

                var params = {
                    includeLatestVersion: !$scope.model.process.latestVersion
                };

                $http({
                    method: 'GET',
                    url: FLOWABLE.APP_URL.getModelHistoriesUrl($scope.model.latestModelId),
                    params: params
                }).success(function (data, status, headers, config) {
                    if ($scope.model.process.latestVersion) {
                        if (!data.data) {
                            data.data = [];
                        }
                        console.log("sasasa:" + data.data);
                        alert("sasasa:" + data.data);
                        data.data.unshift($scope.model.process);
                    }

                    $scope.model.versions = data;
                });
            };

            //
            $scope.showVersion = function (version) {
                if (version) {
                    if (version.latestVersion) {
                        $location.path("/processes/" + $scope.model.latestModelId);
                    } else {
                        // Show latest version, no history-suffix needed in URL
                        $location.path("/processes/" + $scope.model.latestModelId + "/history/" + version.id);
                    }
                }
            };

            $scope.returnToList = function () {
                $location.path("/processes/");
            };

            $scope.rollBack = function () {
                $http({
                    method: 'GET',
                    url: '/rollback',
                    data: $scope.model.process
                }).then(function successCallback(response) {
                    // 请求成功执行代码
                }, function errorCallback(response) {
                    // 请求失败执行代码
                });
            };

            $scope.editProcess = function () {
                _internalCreateModal({
                    template: 'views/popup/model-edit.html',
                    scope: $scope
                }, $modal, $scope);
            };

            $scope.duplicateProcess = function () {
                var modalInstance = _internalCreateModal({
                    template: 'views/popup/process-duplicate.html?version=' + Date.now()
                }, $modal, $scope);

                modalInstance.$scope.originalModel = $scope.model;
            };
            //扩展的部署流程
            $scope.fdeploy = function () {
                var modelId = $routeParams.modelId;
                $http({
                    method: 'post',
                    url: '/repository/procdeploy/' + modelId
                }).success(function (req) {
                    if (req != null && req != "") {
                        console.log(req);
                        layer.alert($scope.model.process.name + '-部署成功!');
                    } else {
                        layer.alert($scope.model.process.name + '-部署失败!');
                    }
                })
            };

            $scope.deleteProcess = function () {
                _internalCreateModal({
                    template: 'views/popup/model-delete.html',
                    scope: $scope
                }, $modal, $scope);
            };

            $scope.openEditor = function () {
                if ($scope.model.process) {
                    $location.path("/editor/" + $scope.model.process.id);
                }
            };

            $scope.toggleHistory = function ($event) {
                if (!$scope.historyState) {
                    var state = {};
                    $scope.historyState = state;

                    // Create popover
                    state.popover = $popover(angular.element($event.target), {
                        template: 'views/popover/history.html',
                        placement: 'bottom-right',
                        show: true,
                        scope: $scope,
                        container: 'body'
                    });

                    var destroy = function () {
                        state.popover.destroy();
                        delete $scope.historyState;
                    }

                    // When popup is hidden or scope is destroyed, hide popup
                    state.popover.$scope.$on('tooltip.hide', destroy);
                    $scope.$on('$destroy', destroy);
                }
            };

            //这里是这里真正访问的方法，前面的都是自己预先定义的方法
            $scope.loadProcess();
        }]);
