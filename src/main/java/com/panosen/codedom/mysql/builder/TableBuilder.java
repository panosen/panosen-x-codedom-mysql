package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.Table;

public class TableBuilder {

    private final Table table = new Table();

    public Table getTable() {
        return table;
    }

    public TableBuilder schema(String tableSchema) {
        table.setTableSchema(tableSchema);
        return this;
    }

    public TableBuilder name(String tableName) {
        table.setTableName(tableName);
        return this;
    }

    public TableBuilder alias(String tableAlias) {
        table.setTableAlias(tableAlias);
        return this;
    }
}
