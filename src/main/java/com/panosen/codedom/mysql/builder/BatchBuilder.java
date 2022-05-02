package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Maps;
import com.panosen.codedom.mysql.Batch;

public class BatchBuilder {

    private final Batch batch = new Batch();

    public Batch getBatch() {
        return batch;
    }

    public BatchBuilder value(String columnName, Object value) {

        if (batch.getValues() == null) {
            batch.setValues(Maps.newHashMap());
        }

        batch.getValues().putIfAbsent(columnName, value);

        return this;
    }
}
