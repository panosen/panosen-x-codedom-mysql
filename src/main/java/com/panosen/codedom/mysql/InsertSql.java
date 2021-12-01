package com.panosen.codedom.mysql;

import java.util.List;

public class InsertSql extends Sql {

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    /**
     * column names
     */
    private List<String> columnNameList;

    /**
     * dbtype of column
     */
    private List<Integer> dbTypeList;

    /**
     * values to insert
     */
    private List<Object> valueList;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    public List<Integer> getDbTypeList() {
        return dbTypeList;
    }

    public void setDbTypeList(List<Integer> dbTypeList) {
        this.dbTypeList = dbTypeList;
    }

    public List<Object> getValueList() {
        return valueList;
    }

    public void setValueList(List<Object> valueList) {
        this.valueList = valueList;
    }
}
