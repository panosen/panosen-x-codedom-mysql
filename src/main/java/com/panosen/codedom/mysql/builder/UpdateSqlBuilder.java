package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.Statements;
import com.panosen.codedom.mysql.UpdateSql;
import com.panosen.codedom.mysql.Where;

public class UpdateSqlBuilder {

    private final UpdateSql updateSql = new UpdateSql();

    public UpdateSql getUpdateSql() {
        return updateSql;
    }

    public UpdateSqlBuilder from(String tableName) {
        updateSql.setTableName(tableName);
        return this;
    }

    public WhereBuilder where() {
        Where where = new Where();
        updateSql.setWhere(where);
        return new WhereBuilder(where);
    }

    public StatementsBuilder set() {
        Statements statements = new Statements();
        updateSql.setStatements(statements);
        StatementsBuilder statementsBuilder = new StatementsBuilder(statements);
        return statementsBuilder;
    }
}
