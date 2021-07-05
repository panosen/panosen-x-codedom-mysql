package com.panosen.codedom.mysql;

/**
 * delete from table where 1 =1
 */
public class DeleteSql extends Sql {

    /**
     * delete from ${tableName};
     */
    private String tableName;

    /**
     * where
     */
    private Where where;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }
}
