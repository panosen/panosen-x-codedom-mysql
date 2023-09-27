package com.panosen.codedom.mysql;

import com.panosen.codedom.mysql.enums.JoinType;

public class Join {

    private JoinType joinType;

    private Table table;

    private String on;

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }
}
