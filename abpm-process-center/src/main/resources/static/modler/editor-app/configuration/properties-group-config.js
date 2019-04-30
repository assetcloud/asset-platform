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

// "PROPERTY.GROUP.BASIC": "基本属性",
// "PROPERTY.GROUP.FORM": "表单",
// "PROPERTY.GROUP.ADVANCED": "高级",
// "PROPERTY.GROUP.SIGNED": "会签",
// "PROPERTY.GROUP.PARTICIPANT": "参与人",
// "PROPERTY.GROUP.OTHERS": "其它",

/**
 * 属性分组配置
 */
FLOWABLE.PROPERTY_GROUP_CONFIG =
[
    {
        id: 'basic',
        name: "PROPERTY.GROUP.BASIC",
        order: 1,
        items: [
            'overrideid',
            'process_id',
            'name',
            'initiator', // 发起人
            'process_potentialstarteruser',  // 候选启动人
            'asynchronousdefinition',
            'preemptmode',
            'checkusers',
            'dataproperties',
            'isforcompensation',
            'duedatedefinition',
            'prioritydefinition',
            'exclusivedefinition',
            'istransaction',
            'servicetaskuselocalscopeforresultvariable',
            'servicetasktriggerable',
            'decisiontaskthrowerroronnohits',
            'shellcommand', // string 命令
            'shellarg1', // text 参数 1
            'shellarg2', // text 参数 2
            'shellarg3', // text 参数 3
            'shellarg4', // text 参数 4
            'shellarg5', //  text 参数 5
            'shellwait', // text 等待
            'shelloutputvariable', // text 输出变量
            'shellerrorcodevariable', //    
            'shellredirecterror', // text 重定向异常
            'shellcleanenv', // text Clean env
            'shelldirectory', // text Directory
            'defaultflow', // 缺省
            'text', // 文本
            'terminateall', // 
            'cancelactivity', // Cancel activity
            'cancelremaininginstances', // Cancel remaining instances
            'interrupting', // Interrupting
        ]
    },
    {
        id: 'form',
        name: "PROPERTY.GROUP.FORM",
        order: 2,
        items: [
            'formkeydefinition',
            'formreference',
            'formproperties',
        ]
    },
     {
        id: 'participant',
        name: "PROPERTY.GROUP.PARTICIPANT",
        order: 3,
        items: [
            'users',
            'roles',
            'orgs'
        ]
    },
    {
        id: 'signed',
        name: "PROPERTY.GROUP.SIGNED",
        order: 4,
        items: [
            'multiinstance_type',
            'multiinstance_cardinality',
            'multiinstance_collection',
            'multiinstance_variable',
            'multiinstance_condition',
        ]
    },
    {
        id: 'advanced',
        name: "PROPERTY.GROUP.ADVANCED",
        order: 5,
        items: [
            'executionlisteners',
            'eventlisteners',
            'skipexpression',
            'tasklisteners',
        ]
    },
    // {
    //     id: 'others',
    //     name: "PROPERTY.GROUP.OTHERS",
    //     items: [

    //     ]
    // }
];
