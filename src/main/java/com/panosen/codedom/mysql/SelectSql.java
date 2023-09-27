package com.panosen.codedom.mysql;

import java.util.List;

/**
 * select * from table;
 */
public class SelectSql extends Sql {

    /**
     * select * from ${tableSchema}.tableName alias;
     */
    private Table table;

    /**
     * select count(*) from ${tableName}
     */
    private boolean useCount;

    /**
     * select ${columnList} from table
     */
    private List<Column> columnList;

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
    private ConditionStatement where;

    /**
     * order by
     */
    private List<OrderBy> orderByList;

    /**
     * group by
     */
    private GroupBy groupBy;

    /**
     * join
     */
    private List<Join> joinList;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isUseCount() {
        return useCount;
    }

    public void setUseCount(boolean useCount) {
        this.useCount = useCount;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
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

    public ConditionStatement getWhere() {
        return where;
    }

    public void setWhere(ConditionStatement where) {
        this.where = where;
    }

    public List<OrderBy> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<OrderBy> orderByList) {
        this.orderByList = orderByList;
    }

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public List<Join> getJoinList() {
        return joinList;
    }

    public void setJoinList(List<Join> joinList) {
        this.joinList = joinList;
    }
}
