package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.*;

import java.util.List;

public class WhereBuilder {

    private final Where where;

    public WhereBuilder(Where where) {
        this.where = where;
    }

    public MustConditions must() {
        MustConditions mustConditions = new MustConditions();
        where.setCondition(mustConditions);
        return mustConditions;
    }

    public ShouldConditions should() {
        ShouldConditions shouldConditions = new ShouldConditions();
        where.setCondition(shouldConditions);
        return shouldConditions;
    }

    public void equal(String fieldName, int dbType, Object value) {
        EqualCondition equalCondition = ConditionBuilders.equalCondition(fieldName, dbType, value);
        where.setCondition(equalCondition);
    }

    public void in(String fieldName, int dbType, List<Object> values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        where.setCondition(inCondition);
    }

    public void in(String fieldName, int dbType, Object... values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, dbType, values);
        where.setCondition(inCondition);
    }
}
