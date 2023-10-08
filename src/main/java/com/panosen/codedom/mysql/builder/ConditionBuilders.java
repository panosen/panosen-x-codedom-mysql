package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.*;

import java.util.List;

public class ConditionBuilders {

    public static EqualCondition equalCondition(String fieldName, int dbType, Object value) {
        EqualCondition equalCondition = new EqualCondition();
        equalCondition.setFieldName(fieldName);
        equalCondition.setDbType(dbType);
        equalCondition.setValue(value);
        return equalCondition;
    }

    public static NotEqualCondition notEqualCondition(String fieldName, int dbType, Object value) {
        NotEqualCondition notEqualCondition = new NotEqualCondition();
        notEqualCondition.setFieldName(fieldName);
        notEqualCondition.setDbType(dbType);
        notEqualCondition.setValue(value);
        return notEqualCondition;
    }

    public static GtCondition gtCondition(String fieldName, int dbType, Object value) {
        GtCondition gtCondition = new GtCondition();
        gtCondition.setFieldName(fieldName);
        gtCondition.setDbType(dbType);
        gtCondition.setValue(value);
        return gtCondition;
    }

    public static GteCondition gteCondition(String fieldName, int dbType, Object value) {
        GteCondition gteCondition = new GteCondition();
        gteCondition.setFieldName(fieldName);
        gteCondition.setDbType(dbType);
        gteCondition.setValue(value);
        return gteCondition;
    }

    public static LtCondition ltCondition(String fieldName, int dbType, Object value) {
        LtCondition ltCondition = new LtCondition();
        ltCondition.setFieldName(fieldName);
        ltCondition.setDbType(dbType);
        ltCondition.setValue(value);
        return ltCondition;
    }

    public static LteCondition lteCondition(String fieldName, int dbType, Object value) {
        LteCondition lteCondition = new LteCondition();
        lteCondition.setFieldName(fieldName);
        lteCondition.setDbType(dbType);
        lteCondition.setValue(value);
        return lteCondition;
    }

    public static InCondition inCondition(String fieldName, int dbType, Iterable<? extends Object>  values) {
        InCondition inCondition = new InCondition();
        inCondition.setFieldName(fieldName);
        inCondition.setDbType(dbType);
        inCondition.setValues(Lists.newArrayList(values));
        return inCondition;
    }

    public static InCondition inCondition(String fieldName, int dbType, Object... values) {
        InCondition inCondition = new InCondition();
        inCondition.setFieldName(fieldName);
        inCondition.setDbType(dbType);
        inCondition.setValues(Lists.newArrayList(values));
        return inCondition;
    }

    public static LikeCondition likeCondition(String fieldName, int dbType, Object value) {
        LikeCondition likeCondition = new LikeCondition();
        likeCondition.setFieldName(fieldName);
        likeCondition.setDbType(dbType);
        likeCondition.setValue(value);
        return likeCondition;
    }
}
