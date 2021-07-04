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
     * select ${columnNames} from table
     */
    private List<String> columnNames;

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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
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
}
