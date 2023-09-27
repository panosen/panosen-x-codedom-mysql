package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.Join;
import com.panosen.codedom.mysql.Table;
import com.panosen.codedom.mysql.enums.JoinType;

public class JoinBuilder {

    /**
     * join
     */
    private final Join join = new Join();

    public Join getJoin() {
        return join;
    }

    public JoinBuilder table(String tableName) {
        if (join.getTable() == null) {
            join.setTable(new Table());
        }
        join.getTable().setTableName(tableName);
        return this;
    }

    public JoinBuilder schema(String tableSchema) {
        join.getTable().setTableSchema(tableSchema);
        return this;
    }

    public JoinBuilder alias(String tableAlias) {
        join.getTable().setTableAlias(tableAlias);
        return this;
    }

    public JoinBuilder type(JoinType joinType) {
        join.setJoinType(joinType);
        return this;
    }

    public JoinBuilder on(String on) {
        join.setOn(on);
        return this;
    }
}
