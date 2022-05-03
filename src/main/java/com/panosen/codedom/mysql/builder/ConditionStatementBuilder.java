package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.*;

import java.util.List;

public abstract class ConditionStatementBuilder {

    private final ConditionStatement conditionStatement;

    public ConditionStatementBuilder(ConditionStatement conditionStatement) {
        this.conditionStatement = conditionStatement;
    }

    public MustConditions must() {
        MustConditions mustConditions = new MustConditions();
        conditionStatement.setCondition(mustConditions);
        return mustConditions;
    }

    public ShouldConditions should() {
        ShouldConditions shouldConditions = new ShouldConditions();
        conditionStatement.setCondition(shouldConditions);
        return shouldConditions;
    }

    public void equal(String fieldName, int dbType, Object value) {
        EqualCondition equalCondition = ConditionBuilders.equalCondition(fieldName, dbType, value);
        conditionStatement.setCondition(equalCondition);
    }

    public void gt(String fieldName, int dbType, Object value) {
        conditionStatement.setCondition(ConditionBuilders.gtCondition(fieldName, dbType, value));
    }

    public void gte(String fieldName, int dbType, Object value) {
        conditionStatement.setCondition(ConditionBuilders.gteCondition(fieldName, dbType, value));
    }

    public void lt(String fieldName, int dbType, Object value) {
        conditionStatement.setCondition(ConditionBuilders.ltCondition(fieldName, dbType, value));
    }

    public void lte(String fieldName, int dbType, Object value) {
        conditionStatement.setCondition(ConditionBuilders.lteCondition(fieldName, dbType, value));
    }

    public <E> void in(String fieldName, int dbType, Iterable<? extends E> values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        conditionStatement.setCondition(inCondition);
    }

    public void in(String fieldName, int dbType, Object... values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        conditionStatement.setCondition(inCondition);
    }
}
