package com.example.jjj.crm_system.service.po;

import java.util.ArrayList;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityExample() {
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

        public Criteria andActivityidIsNull() {
            addCriterion("ActivityID is null");
            return (Criteria) this;
        }

        public Criteria andActivityidIsNotNull() {
            addCriterion("ActivityID is not null");
            return (Criteria) this;
        }

        public Criteria andActivityidEqualTo(Integer value) {
            addCriterion("ActivityID =", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotEqualTo(Integer value) {
            addCriterion("ActivityID <>", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThan(Integer value) {
            addCriterion("ActivityID >", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ActivityID >=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThan(Integer value) {
            addCriterion("ActivityID <", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidLessThanOrEqualTo(Integer value) {
            addCriterion("ActivityID <=", value, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidIn(List<Integer> values) {
            addCriterion("ActivityID in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotIn(List<Integer> values) {
            addCriterion("ActivityID not in", values, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidBetween(Integer value1, Integer value2) {
            addCriterion("ActivityID between", value1, value2, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivityidNotBetween(Integer value1, Integer value2) {
            addCriterion("ActivityID not between", value1, value2, "activityid");
            return (Criteria) this;
        }

        public Criteria andActivitynameIsNull() {
            addCriterion("ActivityName is null");
            return (Criteria) this;
        }

        public Criteria andActivitynameIsNotNull() {
            addCriterion("ActivityName is not null");
            return (Criteria) this;
        }

        public Criteria andActivitynameEqualTo(String value) {
            addCriterion("ActivityName =", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotEqualTo(String value) {
            addCriterion("ActivityName <>", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameGreaterThan(String value) {
            addCriterion("ActivityName >", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameGreaterThanOrEqualTo(String value) {
            addCriterion("ActivityName >=", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameLessThan(String value) {
            addCriterion("ActivityName <", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameLessThanOrEqualTo(String value) {
            addCriterion("ActivityName <=", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameLike(String value) {
            addCriterion("ActivityName like", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotLike(String value) {
            addCriterion("ActivityName not like", value, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameIn(List<String> values) {
            addCriterion("ActivityName in", values, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotIn(List<String> values) {
            addCriterion("ActivityName not in", values, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameBetween(String value1, String value2) {
            addCriterion("ActivityName between", value1, value2, "activityname");
            return (Criteria) this;
        }

        public Criteria andActivitynameNotBetween(String value1, String value2) {
            addCriterion("ActivityName not between", value1, value2, "activityname");
            return (Criteria) this;
        }

        public Criteria andAccountidIsNull() {
            addCriterion("AccountID is null");
            return (Criteria) this;
        }

        public Criteria andAccountidIsNotNull() {
            addCriterion("AccountID is not null");
            return (Criteria) this;
        }

        public Criteria andAccountidEqualTo(Integer value) {
            addCriterion("AccountID =", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotEqualTo(Integer value) {
            addCriterion("AccountID <>", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThan(Integer value) {
            addCriterion("AccountID >", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThanOrEqualTo(Integer value) {
            addCriterion("AccountID >=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThan(Integer value) {
            addCriterion("AccountID <", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThanOrEqualTo(Integer value) {
            addCriterion("AccountID <=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidIn(List<Integer> values) {
            addCriterion("AccountID in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotIn(List<Integer> values) {
            addCriterion("AccountID not in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidBetween(Integer value1, Integer value2) {
            addCriterion("AccountID between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotBetween(Integer value1, Integer value2) {
            addCriterion("AccountID not between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeIsNull() {
            addCriterion("ActivityStartTime is null");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeIsNotNull() {
            addCriterion("ActivityStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeEqualTo(String value) {
            addCriterion("ActivityStartTime =", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeNotEqualTo(String value) {
            addCriterion("ActivityStartTime <>", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeGreaterThan(String value) {
            addCriterion("ActivityStartTime >", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeGreaterThanOrEqualTo(String value) {
            addCriterion("ActivityStartTime >=", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeLessThan(String value) {
            addCriterion("ActivityStartTime <", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeLessThanOrEqualTo(String value) {
            addCriterion("ActivityStartTime <=", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeLike(String value) {
            addCriterion("ActivityStartTime like", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeNotLike(String value) {
            addCriterion("ActivityStartTime not like", value, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeIn(List<String> values) {
            addCriterion("ActivityStartTime in", values, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeNotIn(List<String> values) {
            addCriterion("ActivityStartTime not in", values, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeBetween(String value1, String value2) {
            addCriterion("ActivityStartTime between", value1, value2, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitystarttimeNotBetween(String value1, String value2) {
            addCriterion("ActivityStartTime not between", value1, value2, "activitystarttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeIsNull() {
            addCriterion("ActivityCutTime is null");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeIsNotNull() {
            addCriterion("ActivityCutTime is not null");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeEqualTo(String value) {
            addCriterion("ActivityCutTime =", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeNotEqualTo(String value) {
            addCriterion("ActivityCutTime <>", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeGreaterThan(String value) {
            addCriterion("ActivityCutTime >", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeGreaterThanOrEqualTo(String value) {
            addCriterion("ActivityCutTime >=", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeLessThan(String value) {
            addCriterion("ActivityCutTime <", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeLessThanOrEqualTo(String value) {
            addCriterion("ActivityCutTime <=", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeLike(String value) {
            addCriterion("ActivityCutTime like", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeNotLike(String value) {
            addCriterion("ActivityCutTime not like", value, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeIn(List<String> values) {
            addCriterion("ActivityCutTime in", values, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeNotIn(List<String> values) {
            addCriterion("ActivityCutTime not in", values, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeBetween(String value1, String value2) {
            addCriterion("ActivityCutTime between", value1, value2, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitycuttimeNotBetween(String value1, String value2) {
            addCriterion("ActivityCutTime not between", value1, value2, "activitycuttime");
            return (Criteria) this;
        }

        public Criteria andActivitydetailIsNull() {
            addCriterion("ActivityDetail is null");
            return (Criteria) this;
        }

        public Criteria andActivitydetailIsNotNull() {
            addCriterion("ActivityDetail is not null");
            return (Criteria) this;
        }

        public Criteria andActivitydetailEqualTo(String value) {
            addCriterion("ActivityDetail =", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailNotEqualTo(String value) {
            addCriterion("ActivityDetail <>", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailGreaterThan(String value) {
            addCriterion("ActivityDetail >", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailGreaterThanOrEqualTo(String value) {
            addCriterion("ActivityDetail >=", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailLessThan(String value) {
            addCriterion("ActivityDetail <", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailLessThanOrEqualTo(String value) {
            addCriterion("ActivityDetail <=", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailLike(String value) {
            addCriterion("ActivityDetail like", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailNotLike(String value) {
            addCriterion("ActivityDetail not like", value, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailIn(List<String> values) {
            addCriterion("ActivityDetail in", values, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailNotIn(List<String> values) {
            addCriterion("ActivityDetail not in", values, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailBetween(String value1, String value2) {
            addCriterion("ActivityDetail between", value1, value2, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitydetailNotBetween(String value1, String value2) {
            addCriterion("ActivityDetail not between", value1, value2, "activitydetail");
            return (Criteria) this;
        }

        public Criteria andActivitystateIsNull() {
            addCriterion("ActivityState is null");
            return (Criteria) this;
        }

        public Criteria andActivitystateIsNotNull() {
            addCriterion("ActivityState is not null");
            return (Criteria) this;
        }

        public Criteria andActivitystateEqualTo(Boolean value) {
            addCriterion("ActivityState =", value, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateNotEqualTo(Boolean value) {
            addCriterion("ActivityState <>", value, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateGreaterThan(Boolean value) {
            addCriterion("ActivityState >", value, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("ActivityState >=", value, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateLessThan(Boolean value) {
            addCriterion("ActivityState <", value, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateLessThanOrEqualTo(Boolean value) {
            addCriterion("ActivityState <=", value, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateIn(List<Boolean> values) {
            addCriterion("ActivityState in", values, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateNotIn(List<Boolean> values) {
            addCriterion("ActivityState not in", values, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateBetween(Boolean value1, Boolean value2) {
            addCriterion("ActivityState between", value1, value2, "activitystate");
            return (Criteria) this;
        }

        public Criteria andActivitystateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("ActivityState not between", value1, value2, "activitystate");
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