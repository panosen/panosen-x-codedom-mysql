package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.EqualCondition;
import com.panosen.codedom.mysql.InCondition;

import java.util.List;

public class ConditionBuilders {

    public static EqualCondition equalCondition(String fieldName, int dbType, Object value) {
        EqualCondition equalCondition = new EqualCondition();
        equalCondition.setFieldName(fieldName);
        equalCondition.setDbType(dbType);
        equalCondition.setValue(value);
        return equalCondition;
    }

    public static InCondition inCondition(String fieldName, int dbType, List<Object> values) {
        InCondition inCondition = new InCondition();
        inCondition.setFieldName(fieldName);
        inCondition.setDbType(dbType);
        inCondition.setValues(values);
        return inCondition;
    }

    public static InCondition inCondition(String fieldName, int dbType, Object... values) {
        InCondition inCondition = new InCondition();
        inCondition.setFieldName(fieldName);
        inCondition.setDbType(dbType);
        inCondition.setValues(Lists.newArrayList(values));
        return inCondition;
    }
}
