package com.asset.dto;

import lombok.Data;

/**
 * 	"sign_strategy": {
 * 		"agree_rule": {
 * 			"rule": {
 * 				"strategy": 0,
 * 				"num": 80
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
