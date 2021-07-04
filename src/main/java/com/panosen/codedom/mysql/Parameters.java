package com.panosen.codedom.mysql;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Parameters implements Iterable<Parameter> {

    private List<Parameter> parameters = new LinkedList<>();

    public boolean isEmpty() {
        return parameters.isEmpty();
    }

    public int size() {
        return parameters.size();
    }

    public Parameter get(int index) {
        return parameters.get(index);
    }

    public Parameters add(int dbType, Object value) {
        Parameter parameter = new Parameter();
        parameter.setDbType(dbType);
        parameter.setValue(value);
        this.parameters.add(parameter);

        return this;
    }

    @Override
    public Iterator<Parameter> iterator() {
        return parameters.iterator();
    }
}
