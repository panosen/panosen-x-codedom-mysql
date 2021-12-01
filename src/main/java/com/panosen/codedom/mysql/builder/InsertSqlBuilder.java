package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.InsertSql;

public class InsertSqlBuilder {

    private final InsertSql insertSql = new InsertSql();

    public InsertSql getInsertSql() {
        return insertSql;
    }

    public InsertSqlBuilder intoTable(String tableName) {
        insertSql.setTableName(tableName);
        return this;
    }

    public InsertSqlBuilder value(String columnName, int dbType, Object value) {
        if (insertSql.getColumnNameList() == null) {
            insertSql.setColumnNameList(Lists.newArrayList());
        }
        if (insertSql.getDbTypeList() == null) {
            insertSql.setDbTypeList(Lists.newArrayList());
        }
        if (insertSql.getValueList() == null) {
            insertSql.setValueList(Lists.newArrayList());
        }

        insertSql.getColumnNameList().add(columnName);
        insertSql.getDbTypeList().add(dbType);
        insertSql.getValueList().add(value);

        return this;
    }
}
