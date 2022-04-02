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
        parameter.setIndex(parameters.size() + 1);
        parameter.setDbType(dbType);
        parameter.setValue(value);
        this.parameters.add(parameter);

        return this;
    }

    @Override
    public Iterator<Parameter> iterator() {
        return parameters.iterator();
    }

    public int[] getDbTypes() {
        int[] dbTypes = new int[parameters.size()];
        for (int index = 0, length = parameters.size(); index < length; index++) {
            dbTypes[index] = parameters.get(index).getDbType();
        }
        return dbTypes;
    }

    public Object[] getValues() {
        Object[] values = new Object[parameters.size()];
        for (int index = 0, length = parameters.size(); index < length; index++) {
            values[index] = parameters.get(index).getValue();
        }
        return values;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}
