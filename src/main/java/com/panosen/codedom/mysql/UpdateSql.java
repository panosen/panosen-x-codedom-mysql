package com.panosen.codedom.mysql;

public class UpdateSql extends Sql {

    /**
     * delete from ${tableName};
     */
    private String tableName;

    /**
     * update table set ${statements}
     */
    private Statements statements;

    /**
     * where
     */
    private ConditionStatement where;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Statements getStatements() {
        return statements;
    }

    public void setStatements(Statements statements) {
        this.statements = statements;
    }

    public ConditionStatement getWhere() {
        return where;
    }

    public void setWhere(ConditionStatement where) {
        this.where = where;
    }
}
