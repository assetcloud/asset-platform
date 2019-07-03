/* Copyright 2005-2015 Alfresco Software, Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
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

FLOWABLE.CONFIG = {
	'onPremise' : true,
	'contextRoot' : '',
	'webContextRoot' : '',
	'datesLocalization' : false,

	/** 外部属性配置 
	 * 用于灵活配置外接系统的人员，角色，组织
	*/
	outerProps: {

		/**
		 * 选择用户
		 */
		users: {
			use: true,
			dataUrl: '/data/users.json'
		},
		/**
		 * 组织
		 */
		org: {
			use: true,
			dataUrl: '/data/org.json'
		},
		/**
		 * 角色
		 */
		roles: {
			use: true,
			dataUrl: '/data/roles.json'
		}
	}
};
