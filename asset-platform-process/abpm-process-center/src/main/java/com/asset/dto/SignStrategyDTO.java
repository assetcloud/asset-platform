package com.asset.dto;

import lombok.Data;

/**
 *
 * 如果前台没有
 *
 * 	"sign_strategy": {
 * 		"agree_rule": {   //这里表示会签节点的通过规则
 * 			"rule": {
 * 				"strategy": 0,      //当这个值为0代表 按人数选择通过
 * 				"num": 80           //代表人数或者百分比
 *                        } 		},
 * 		"disagree_rule": {
 * 			"rule": {
 * 				"strategy": 0,
 * 				"num": 80 			},
 * 			"handle_way:        2
 *    }
 * 	}
 */
@Data
public class SignStrategyDTO {
    AgreeRuleDTO agree_rule;
    DisagreeRuleDTO disagree_rule;
}
