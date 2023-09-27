package com.panosen.codedom.mysql;

public class Column {

    private String columnName;

    private String columnTable;

    private String columnAs;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnTable() {
        return columnTable;
    }

    public void setColumnTable(String columnTable) {
        this.columnTable = columnTable;
    }

    public String getColumnAs() {
        return columnAs;
    }

    public void setColumnAs(String columnAs) {
        this.columnAs = columnAs;
    }
}
