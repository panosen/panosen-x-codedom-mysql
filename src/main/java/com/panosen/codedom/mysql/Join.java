package com.panosen.codedom.mysql;

import com.panosen.codedom.mysql.enums.JoinType;

public class Join {

    private JoinType joinType;

    private String tableName;

    private String on;

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }
}
