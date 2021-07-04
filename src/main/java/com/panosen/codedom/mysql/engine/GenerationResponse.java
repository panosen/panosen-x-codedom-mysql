package com.panosen.codedom.mysql.engine;

import com.panosen.codedom.mysql.Parameters;

public class GenerationResponse {

    private String sql;

    private Parameters parameters;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
