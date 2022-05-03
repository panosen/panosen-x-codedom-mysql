package com.panosen.codedom.mysql;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.builder.ConditionBuilders;

import java.util.List;

public abstract class Conditions<TConditions extends Conditions<?>> extends Condition {

    private List<Condition> conditionList;

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public void addCondition(Condition condition) {
        if (this.conditionList == null) {
            this.conditionList = Lists.newArrayList();
        }
        this.conditionList.add(condition);
    }

    public MustConditions must() {
        MustConditions mustConditions = new MustConditions();
        addCondition(mustConditions);
        return mustConditions;
    }

    public ShouldConditions should() {
        ShouldConditions shouldConditions = new ShouldConditions();
        addCondition(shouldConditions);
        return shouldConditions;
    }

    @SuppressWarnings("unchecked")
    public TConditions equal(String fieldName, int dbType, Object value) {
        EqualCondition equalCondition = ConditionBuilders.equalCondition(fieldName, dbType, value);
        addCondition(equalCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions notEqual(String fieldName, int dbType, Object value) {
        NotEqualCondition equalCondition = ConditionBuilders.notEqualCondition(fieldName, dbType, value);
        addCondition(equalCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions gt(String fieldName, int dbType, Object value) {
        GtCondition gtCondition = ConditionBuilders.gtCondition(fieldName, dbType, value);
        addCondition(gtCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions gte(String fieldName, int dbType, Object value) {
        GteCondition gteCondition = ConditionBuilders.gteCondition(fieldName, dbType, value);
        addCondition(gteCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions lt(String fieldName, int dbType, Object value) {
        LtCondition ltCondition = ConditionBuilders.ltCondition(fieldName, dbType, value);
        addCondition(ltCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions lte(String fieldName, int dbType, Object value) {
        LteCondition lteCondition = ConditionBuilders.lteCondition(fieldName, dbType, value);
        addCondition(lteCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public <E> TConditions in(String fieldName, int dbType, Iterable<? extends Object> values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        addCondition(inCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions in(String fieldName, int dbType, Object... values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        addCondition(inCondition);
        return (TConditions) this;
    }
}
