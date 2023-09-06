package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.OrderBy;
import com.panosen.codedom.mysql.SelectSql;
import com.panosen.codedom.mysql.enums.JoinType;

public class SelectSqlBuilder {

    private final SelectSql selectSql = new SelectSql();

    public SelectSql getSelectSql() {
        return selectSql;
    }

    public SelectSqlBuilder count() {
        selectSql.setUseCount(true);
        return this;
    }

    public SelectSqlBuilder column(String column) {
        if (selectSql.getColumnNameList() == null) {
            selectSql.setColumnNameList(Lists.newArrayList());
        }
        selectSql.getColumnNameList().add(column);
        return this;
    }

    public SelectSqlBuilder columns(String... columns) {
        if (selectSql.getColumnNameList() == null) {
            selectSql.setColumnNameList(Lists.newArrayList());
        }
        selectSql.getColumnNameList().addAll(Lists.newArrayList(columns));
        return this;
    }

    public SelectSqlBuilder from(String tableName) {
        return from(tableName, null);
    }

    public SelectSqlBuilder from(String tableName, String tableSchema) {
        selectSql.setTableSchema(tableSchema);
        selectSql.setTableName(tableName);
        return this;
    }

    public SelectSqlBuilder limit(Integer limitSize) {
        selectSql.setLimitFrom(null);
        selectSql.setLimitSize(limitSize);
        return this;
    }

    public SelectSqlBuilder limit(Integer limitFrom, Integer limitSize) {
        selectSql.setLimitFrom(limitFrom);
        selectSql.setLimitSize(limitSize);
        return this;
    }

    public SelectSqlBuilder orderBy(String columnName) {
        return orderBy(columnName, false);
    }

    public SelectSqlBuilder orderBy(String columnName, Boolean desc) {
        if (selectSql.getOrderByList() == null) {
            selectSql.setOrderByList(Lists.newArrayList());
        }

        OrderBy orderBy = new OrderBy();
        orderBy.setColumnName(columnName);
        orderBy.setDesc(desc);
        selectSql.getOrderByList().add(orderBy);

        return this;
    }

    public GroupByBuilder groupBy() {
        GroupByBuilder groupByBuilder = new GroupByBuilder();
        selectSql.setGroupBy(groupByBuilder.getGroupBy());
        return groupByBuilder;
    }

    public ConditionsBuilder where() {
        ConditionsBuilder conditionsBuilder = new ConditionsBuilder();
        selectSql.setWhere(conditionsBuilder.getConditionStatement());
        return conditionsBuilder;
    }

    public JoinBuilder join(String tableName) {
        if (selectSql.getJoinList() == null) {
            selectSql.setJoinList(Lists.newArrayList());
        }
        JoinBuilder joinBuilder = new JoinBuilder();
        joinBuilder.setTableName(tableName);
        selectSql.getJoinList().add(joinBuilder.getJoin());
        return joinBuilder;
    }

    public JoinBuilder join(String tableName, JoinType joinType) {
        if (selectSql.getJoinList() == null) {
            selectSql.setJoinList(Lists.newArrayList());
        }
        JoinBuilder joinBuilder = new JoinBuilder();
        joinBuilder.setTableName(tableName);
        joinBuilder.setJoinType(joinType);
        selectSql.getJoinList().add(joinBuilder.getJoin());
        return joinBuilder;
    }
}
