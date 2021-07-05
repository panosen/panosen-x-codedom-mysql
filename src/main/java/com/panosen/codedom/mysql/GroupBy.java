package com.panosen.codedom.mysql;

import java.util.List;

public class GroupBy {

    /**
     * select * from table group by ${columnNames}
     */
    private List<String> columnNames;

    /**
     * having
     */
    private Having having;

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public Having getHaving() {
        return having;
    }

    public void setHaving(Having having) {
        this.having = having;
    }
}
