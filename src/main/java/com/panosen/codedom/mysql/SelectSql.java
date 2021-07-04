package com.panosen.codedom.mysql;

import java.util.List;

/**
 * select * from table;
 */
public class SelectSql extends Sql {

    /**
     * select * from ${tableName};
     */
    private String tableName;

    /**
     * select ${columnNameList} from table
     */
    private List<String> columnNameList;

    /**
     * Only useful when limitSize is set.
     * select * from ${tableName} limit ${limitFrom}, 10;
     */
    private Integer limitFrom;

    /**
     * select * from table limit ${limitSize};
     */
    private Integer limitSize;

    /**
     * where
     */
    private Where where;

    /**
     * order by
     */
    private List<OrderBy> orderByList;

    /**
     * select * from table group by ${groupByList}
     */
    private List<String> groupByList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    public Integer getLimitFrom() {
        return limitFrom;
    }

    public void setLimitFrom(Integer limitFrom) {
        this.limitFrom = limitFrom;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize = limitSize;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public List<OrderBy> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<OrderBy> orderByList) {
        this.orderByList = orderByList;
    }

    public List<String> getGroupByList() {
        return groupByList;
    }

    public void setGroupByList(List<String> groupByList) {
        this.groupByList = groupByList;
    }
}
