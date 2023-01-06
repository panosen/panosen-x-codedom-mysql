package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.UpdateSql;

public class UpdateSqlBuilder {

    private final UpdateSql updateSql = new UpdateSql();

    public UpdateSql getUpdateSql() {
        return updateSql;
    }

    public UpdateSqlBuilder table(String tableName) {
        updateSql.setTableName(tableName);
        return this;
    }

    public ConditionsBuilder where() {
        ConditionsBuilder conditionsBuilder = new ConditionsBuilder();
        updateSql.setWhere(conditionsBuilder.getConditionStatement());
        return conditionsBuilder;
    }

    public StatementsBuilder set() {
        StatementsBuilder statementsBuilder = new StatementsBuilder();
        updateSql.setStatements(statementsBuilder.getStatements());
        return statementsBuilder;
    }
}
