package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.Join;
import com.panosen.codedom.mysql.enums.JoinType;

public class JoinBuilder {

    /**
     * join
     */
    private final Join join = new Join();

    public Join getJoin() {
        return join;
    }

    public void setJoinType(JoinType joinType) {
        join.setJoinType(joinType);
    }

    public JoinBuilder setTableName(String tableName) {
        join.setTableName(tableName);
        return this;
    }

    public JoinBuilder on(String on) {
        join.setOn(on);
        return this;
    }
}
