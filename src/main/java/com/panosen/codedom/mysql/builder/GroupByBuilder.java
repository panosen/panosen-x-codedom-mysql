package com.panosen.codedom.mysql.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.mysql.GroupBy;
import com.panosen.codedom.mysql.Having;

public class GroupByBuilder {

    private GroupBy groupBy;

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public GroupByBuilder(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public GroupByBuilder column(String column) {
        if (groupBy.getColumnNames() == null) {
            groupBy.setColumnNames(Lists.newArrayList());
        }
        groupBy.getColumnNames().add(column);
        return this;
    }

    public GroupByBuilder columns(String... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        if (groupBy.getColumnNames() == null) {
            groupBy.setColumnNames(Lists.newArrayList());
        }
        groupBy.getColumnNames().addAll(Lists.newArrayList(columns));
        return this;
    }

    public GroupByBuilder having(Having having) {
        groupBy.setHaving(having);
        return this;
    }

    public HavingBuilder having() {
        Having having = new Having();
        HavingBuilder havingBuilder = new HavingBuilder(having);
        groupBy.setHaving(having);
        return havingBuilder;
    }
}
