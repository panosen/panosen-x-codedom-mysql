package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.Column;

public class ColumnBuilder {

    private final Column column = new Column();

    public Column getColumn() {
        return column;
    }

    public ColumnBuilder name(String name) {
        column.setColumnName(name);
        return this;
    }

    public ColumnBuilder table(String table) {
        column.setColumnTable(table);
        return this;
    }

    public ColumnBuilder as(String as) {
        column.setColumnAs(as);
        return this;
    }
}
