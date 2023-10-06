package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.OrderBy;
import com.panosen.codedom.mysql.SelectSql;

public class SelectSqlBuilder {

    private final SelectSql selectSql = new SelectSql();

    public SelectSql getSelectSql() {
        return selectSql;
    }

    public SelectSqlBuilder count() {
        selectSql.setUseCount(true);
        return this;
    }

    public ColumnBuilder column(String columnName) {
        if (selectSql.getColumnList() == null) {
            selectSql.setColumnList(Lists.newArrayList());
        }

        ColumnBuilder columnBuilder = new ColumnBuilder();
        selectSql.getColumnList().add(columnBuilder.getColumn());

        columnBuilder.name(columnName);

        return columnBuilder;
    }

    public TableBuilder from(String tableName) {
        TableBuilder tableBuilder = new TableBuilder();
        tableBuilder.name(tableName);
        selectSql.setTable(tableBuilder.getTable());
        return tableBuilder;
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
        joinBuilder.table(tableName);
        selectSql.getJoinList().add(joinBuilder.getJoin());

        return joinBuilder;
    }
}
