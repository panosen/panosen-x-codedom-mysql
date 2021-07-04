package com.panosen.codedom.mysql;

public class Parameter {

    private int dbType;

    private Object value;

    public int getDbType() {
        return dbType;
    }

    public void setDbType(int dbType) {
        this.dbType = dbType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
