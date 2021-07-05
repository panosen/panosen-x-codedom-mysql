package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.Statement;
import com.panosen.codedom.mysql.Statements;

public class StatementsBuilder {

    private final Statements statements;

    public StatementsBuilder(Statements statements) {
        this.statements = statements;
    }

    public StatementsBuilder set(String fieldName, int fieldType, Object value) {
        if (statements.getStatementList() == null) {
            statements.setStatementList(Lists.newArrayList());
        }
        Statement statement = new Statement();
        statement.setFieldName(fieldName);
        statement.setFieldType(fieldType);
        statement.setValue(value);
        statements.getStatementList().add(statement);

        return this;
    }
}
