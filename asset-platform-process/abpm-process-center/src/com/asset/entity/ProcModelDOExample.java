package com.asset.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcModelDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProcModelDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andProcModelIdIsNull() {
            addCriterion("proc_model_id is null");
            return (Criteria) this;
        }

        public Criteria andProcModelIdIsNotNull() {
            addCriterion("proc_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcModelIdEqualTo(String value) {
            addCriterion("proc_model_id =", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdNotEqualTo(String value) {
            addCriterion("proc_model_id <>", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdGreaterThan(String value) {
            addCriterion("proc_model_id >", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdGreaterThanOrEqualTo(String value) {
            addCriterion("proc_model_id >=", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdLessThan(String value) {
            addCriterion("proc_model_id <", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdLessThanOrEqualTo(String value) {
            addCriterion("proc_model_id <=", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdLike(String value) {
            addCriterion("proc_model_id like", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdNotLike(String value) {
            addCriterion("proc_model_id not like", value, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdIn(List<String> values) {
            addCriterion("proc_model_id in", values, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdNotIn(List<String> values) {
            addCriterion("proc_model_id not in", values, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdBetween(String value1, String value2) {
            addCriterion("proc_model_id between", value1, value2, "procModelId");
            return (Criteria) this;
        }

        public Criteria andProcModelIdNotBetween(String value1, String value2) {
            addCriterion("proc_model_id not between", value1, value2, "procModelId");
            return (Criteria) this;
        }

        public Criteria andActIdIsNull() {
            addCriterion("act_id is null");
            return (Criteria) this;
        }

        public Criteria andActIdIsNotNull() {
            addCriterion("act_id is not null");
            return (Criteria) this;
        }

        public Criteria andActIdEqualTo(String value) {
            addCriterion("act_id =", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdNotEqualTo(String value) {
            addCriterion("act_id <>", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdGreaterThan(String value) {
            addCriterion("act_id >", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdGreaterThanOrEqualTo(String value) {
            addCriterion("act_id >=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdLessThan(String value) {
            addCriterion("act_id <", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdLessThanOrEqualTo(String value) {
            addCriterion("act_id <=", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdLike(String value) {
            addCriterion("act_id like", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdNotLike(String value) {
            addCriterion("act_id not like", value, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdIn(List<String> values) {
            addCriterion("act_id in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdNotIn(List<String> values) {
            addCriterion("act_id not in", values, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdBetween(String value1, String value2) {
            addCriterion("act_id between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActIdNotBetween(String value1, String value2) {
            addCriterion("act_id not between", value1, value2, "nodeId");
            return (Criteria) this;
        }

        public Criteria andActTypeIsNull() {
            addCriterion("act_type is null");
            return (Criteria) this;
        }

        public Criteria andActTypeIsNotNull() {
            addCriterion("act_type is not null");
            return (Criteria) this;
        }

        public Criteria andActTypeEqualTo(Integer value) {
            addCriterion("act_type =", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotEqualTo(Integer value) {
            addCriterion("act_type <>", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeGreaterThan(Integer value) {
            addCriterion("act_type >", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("act_type >=", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeLessThan(Integer value) {
            addCriterion("act_type <", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeLessThanOrEqualTo(Integer value) {
            addCriterion("act_type <=", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeIn(List<Integer> values) {
            addCriterion("act_type in", values, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotIn(List<Integer> values) {
            addCriterion("act_type not in", values, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeBetween(Integer value1, Integer value2) {
            addCriterion("act_type between", value1, value2, "nodeType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("act_type not between", value1, value2, "nodeType");
            return (Criteria) this;
        }

        public Criteria andCandidateUserIsNull() {
            addCriterion("candidate_user is null");
            return (Criteria) this;
        }

        public Criteria andCandidateUserIsNotNull() {
            addCriterion("candidate_user is not null");
            return (Criteria) this;
        }

        public Criteria andCandidateUserEqualTo(String value) {
            addCriterion("candidate_user =", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserNotEqualTo(String value) {
            addCriterion("candidate_user <>", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserGreaterThan(String value) {
            addCriterion("candidate_user >", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserGreaterThanOrEqualTo(String value) {
            addCriterion("candidate_user >=", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserLessThan(String value) {
            addCriterion("candidate_user <", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserLessThanOrEqualTo(String value) {
            addCriterion("candidate_user <=", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserLike(String value) {
            addCriterion("candidate_user like", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserNotLike(String value) {
            addCriterion("candidate_user not like", value, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserIn(List<String> values) {
            addCriterion("candidate_user in", values, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserNotIn(List<String> values) {
            addCriterion("candidate_user not in", values, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserBetween(String value1, String value2) {
            addCriterion("candidate_user between", value1, value2, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateUserNotBetween(String value1, String value2) {
            addCriterion("candidate_user not between", value1, value2, "candidateUser");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupIsNull() {
            addCriterion("candidate_group is null");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupIsNotNull() {
            addCriterion("candidate_group is not null");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupEqualTo(String value) {
            addCriterion("candidate_group =", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupNotEqualTo(String value) {
            addCriterion("candidate_group <>", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupGreaterThan(String value) {
            addCriterion("candidate_group >", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupGreaterThanOrEqualTo(String value) {
            addCriterion("candidate_group >=", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupLessThan(String value) {
            addCriterion("candidate_group <", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupLessThanOrEqualTo(String value) {
            addCriterion("candidate_group <=", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupLike(String value) {
            addCriterion("candidate_group like", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupNotLike(String value) {
            addCriterion("candidate_group not like", value, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupIn(List<String> values) {
            addCriterion("candidate_group in", values, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupNotIn(List<String> values) {
            addCriterion("candidate_group not in", values, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupBetween(String value1, String value2) {
            addCriterion("candidate_group between", value1, value2, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andCandidateGroupNotBetween(String value1, String value2) {
            addCriterion("candidate_group not between", value1, value2, "candidateGroup");
            return (Criteria) this;
        }

        public Criteria andLimitTimeIsNull() {
            addCriterion("limit_time is null");
            return (Criteria) this;
        }

        public Criteria andLimitTimeIsNotNull() {
            addCriterion("limit_time is not null");
            return (Criteria) this;
        }

        public Criteria andLimitTimeEqualTo(Date value) {
            addCriterion("limit_time =", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeNotEqualTo(Date value) {
            addCriterion("limit_time <>", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeGreaterThan(Date value) {
            addCriterion("limit_time >", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("limit_time >=", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeLessThan(Date value) {
            addCriterion("limit_time <", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeLessThanOrEqualTo(Date value) {
            addCriterion("limit_time <=", value, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeIn(List<Date> values) {
            addCriterion("limit_time in", values, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeNotIn(List<Date> values) {
            addCriterion("limit_time not in", values, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeBetween(Date value1, Date value2) {
            addCriterion("limit_time between", value1, value2, "limitTime");
            return (Criteria) this;
        }

        public Criteria andLimitTimeNotBetween(Date value1, Date value2) {
            addCriterion("limit_time not between", value1, value2, "limitTime");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyIsNull() {
            addCriterion("overtime_strategy is null");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyIsNotNull() {
            addCriterion("overtime_strategy is not null");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyEqualTo(Integer value) {
            addCriterion("overtime_strategy =", value, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyNotEqualTo(Integer value) {
            addCriterion("overtime_strategy <>", value, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyGreaterThan(Integer value) {
            addCriterion("overtime_strategy >", value, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyGreaterThanOrEqualTo(Integer value) {
            addCriterion("overtime_strategy >=", value, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyLessThan(Integer value) {
            addCriterion("overtime_strategy <", value, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyLessThanOrEqualTo(Integer value) {
            addCriterion("overtime_strategy <=", value, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyIn(List<Integer> values) {
            addCriterion("overtime_strategy in", values, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyNotIn(List<Integer> values) {
            addCriterion("overtime_strategy not in", values, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyBetween(Integer value1, Integer value2) {
            addCriterion("overtime_strategy between", value1, value2, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andOvertimeStrategyNotBetween(Integer value1, Integer value2) {
            addCriterion("overtime_strategy not between", value1, value2, "overtimeStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyIsNull() {
            addCriterion("sign_strategy is null");
            return (Criteria) this;
        }

        public Criteria andSignStrategyIsNotNull() {
            addCriterion("sign_strategy is not null");
            return (Criteria) this;
        }

        public Criteria andSignStrategyEqualTo(String value) {
            addCriterion("sign_strategy =", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyNotEqualTo(String value) {
            addCriterion("sign_strategy <>", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyGreaterThan(String value) {
            addCriterion("sign_strategy >", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyGreaterThanOrEqualTo(String value) {
            addCriterion("sign_strategy >=", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyLessThan(String value) {
            addCriterion("sign_strategy <", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyLessThanOrEqualTo(String value) {
            addCriterion("sign_strategy <=", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyLike(String value) {
            addCriterion("sign_strategy like", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyNotLike(String value) {
            addCriterion("sign_strategy not like", value, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyIn(List<String> values) {
            addCriterion("sign_strategy in", values, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyNotIn(List<String> values) {
            addCriterion("sign_strategy not in", values, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyBetween(String value1, String value2) {
            addCriterion("sign_strategy between", value1, value2, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andSignStrategyNotBetween(String value1, String value2) {
            addCriterion("sign_strategy not between", value1, value2, "signStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyIsNull() {
            addCriterion("todo_strategy is null");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyIsNotNull() {
            addCriterion("todo_strategy is not null");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyEqualTo(String value) {
            addCriterion("todo_strategy =", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyNotEqualTo(String value) {
            addCriterion("todo_strategy <>", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyGreaterThan(String value) {
            addCriterion("todo_strategy >", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyGreaterThanOrEqualTo(String value) {
            addCriterion("todo_strategy >=", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyLessThan(String value) {
            addCriterion("todo_strategy <", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyLessThanOrEqualTo(String value) {
            addCriterion("todo_strategy <=", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyLike(String value) {
            addCriterion("todo_strategy like", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyNotLike(String value) {
            addCriterion("todo_strategy not like", value, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyIn(List<String> values) {
            addCriterion("todo_strategy in", values, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyNotIn(List<String> values) {
            addCriterion("todo_strategy not in", values, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyBetween(String value1, String value2) {
            addCriterion("todo_strategy between", value1, value2, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andTodoStrategyNotBetween(String value1, String value2) {
            addCriterion("todo_strategy not between", value1, value2, "todoStrategy");
            return (Criteria) this;
        }

        public Criteria andIfJointSignIsNull() {
            addCriterion("if_joint_sign is null");
            return (Criteria) this;
        }

        public Criteria andIfJointSignIsNotNull() {
            addCriterion("if_joint_sign is not null");
            return (Criteria) this;
        }

        public Criteria andIfJointSignEqualTo(Integer value) {
            addCriterion("if_joint_sign =", value, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignNotEqualTo(Integer value) {
            addCriterion("if_joint_sign <>", value, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignGreaterThan(Integer value) {
            addCriterion("if_joint_sign >", value, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignGreaterThanOrEqualTo(Integer value) {
            addCriterion("if_joint_sign >=", value, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignLessThan(Integer value) {
            addCriterion("if_joint_sign <", value, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignLessThanOrEqualTo(Integer value) {
            addCriterion("if_joint_sign <=", value, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignIn(List<Integer> values) {
            addCriterion("if_joint_sign in", values, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignNotIn(List<Integer> values) {
            addCriterion("if_joint_sign not in", values, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignBetween(Integer value1, Integer value2) {
            addCriterion("if_joint_sign between", value1, value2, "ifJointSign");
            return (Criteria) this;
        }

        public Criteria andIfJointSignNotBetween(Integer value1, Integer value2) {
            addCriterion("if_joint_sign not between", value1, value2, "ifJointSign");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
