package com.panosen.codedom.mysql;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.builder.ConditionBuilders;

import java.util.List;

public abstract class Conditions<TConditions extends  Conditions<?>> extends Condition {

    private List<Condition> conditionList;

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }


    protected <TCondition extends Condition> TCondition addCondition(Class<TCondition> clazz)
            throws IllegalAccessException, InstantiationException {
        if (this.conditionList == null) {
            this.conditionList = Lists.newArrayList();
        }
        TCondition condition = clazz.newInstance();
        this.conditionList.add(condition);
        return condition;
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
        return (TConditions)this;
    }

    @SuppressWarnings("unchecked")
    public TConditions in(String fieldName, int dbType, List<Object> values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        addCondition(inCondition);
        return (TConditions)this;
    }

    @SuppressWarnings("unchecked")
    public TConditions in(String fieldName, int dbType, Object... values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        addCondition(inCondition);
        return (TConditions)this;
    }
}
