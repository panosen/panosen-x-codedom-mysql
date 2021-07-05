package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.DeleteSql;
import com.panosen.codedom.mysql.Where;

public class DeleteSqlBuilder {

    private final DeleteSql deleteSql = new DeleteSql();

    public DeleteSql getDeleteSql() {
        return deleteSql;
    }

    public DeleteSqlBuilder from(String tableName) {
        deleteSql.setTableName(tableName);
        return this;
    }

    public WhereBuilder where() {
        Where where = new Where();
        deleteSql.setWhere(where);
        return new WhereBuilder(where);
    }
}
