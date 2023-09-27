package com.panosen.codedom.mysql;

public class Table {

    /**
     * select * from ${tableSchema}.tableName;
     */
    private String tableSchema;

    /**
     * select * from ${tableName};
     */
    private String tableName;

    /**
     * alias name of table
     */
    private String tableAlias;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}
