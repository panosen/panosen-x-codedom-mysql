package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.SelectSql;
import com.panosen.codedom.mysql.Where;

public class SelectSqlBuilder {

    private final SelectSql selectSql = new SelectSql();

    public SelectSql getSelectSql() {
        return selectSql;
    }

    public SelectSqlBuilder column(String column) {
        if (selectSql.getColumnNames() == null) {
            selectSql.setColumnNames(Lists.newArrayList());
        }
        selectSql.getColumnNames().add(column);
        return this;
    }

    public SelectSqlBuilder columns(String... columns) {
        if (selectSql.getColumnNames() == null) {
            selectSql.setColumnNames(Lists.newArrayList());
        }
        selectSql.getColumnNames().addAll(Lists.newArrayList(columns));
        return this;
    }

    public SelectSqlBuilder from(String tableName) {
        selectSql.setTableName(tableName);
        return this;
    }

    public SelectSqlBuilder limit(Integer limitSize) {
        selectSql.setLimitSize(limitSize);
        return this;
    }

    public SelectSqlBuilder limit(Integer limitFrom, Integer limitSize) {
        selectSql.setLimitFrom(limitFrom);
        selectSql.setLimitSize(limitSize);
        return this;
    }

    public WhereBuilder where() {
        Where where = new Where();
        selectSql.setWhere(where);
        return new WhereBuilder(where);
    }
}
