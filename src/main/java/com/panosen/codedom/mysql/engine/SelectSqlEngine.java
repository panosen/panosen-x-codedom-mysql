package com.panosen.codedom.mysql.engine;

import com.google.common.base.Strings;
import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.mysql.*;
import com.panosen.codedom.mysql.builder.SelectSqlBuilder;

import java.io.StringWriter;
import java.util.List;

public class SelectSqlEngine extends SqlEngine {

    public GenerationResponse generate(SelectSqlBuilder selectSqlBuilder) {
        GenerationResponse generationResponse = new GenerationResponse();

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        Parameters parameters = new Parameters();

        generate(selectSqlBuilder.getSelectSql(), codeWriter, parameters);

        generationResponse.setSql(stringWriter.toString());
        generationResponse.setParameters(parameters);

        return generationResponse;
    }

    public void generate(SelectSql selectSql, CodeWriter codeWriter, Parameters parameters) {
        // select
        codeWriter.write(Keywords.SELECT).write(Marks.WHITESPACE);

        if (selectSql.isUseCount()) {
            codeWriter.write(Keywords.COUNT).write(Marks.LEFT_BRACKET).write(Marks.STAR).write(Marks.RIGHT_BRACKET);
        } else {
            // columns
            if (selectSql.getColumnList() != null && !selectSql.getColumnList().isEmpty()) {
                for (int index = 0, length = selectSql.getColumnList().size(); index < length; index++) {
                    Column column = selectSql.getColumnList().get(index);
                    generateColumn(codeWriter, column);

                    if (index < length - 1) {
                        codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
                    }
                }
            } else {
                codeWriter.write(Marks.STAR);
            }
        }

        // from table
        generateFromTable(codeWriter, selectSql.getTable());

        //join
        if (selectSql.getJoinList() != null && !selectSql.getJoinList().isEmpty()) {
            for (Join join : selectSql.getJoinList()) {

                generateJoin(codeWriter, join);
            }
        }

        // where
        generateWhere(selectSql.getWhere(), codeWriter, parameters);

        // group by
        generateGroupBy(selectSql.getGroupBy(), codeWriter, parameters);

        // order by
        generateOrderBy(selectSql.getOrderByList(), codeWriter);

        // limit
        if (selectSql.getLimitSize() != null && selectSql.getLimitSize() > 0) {
            codeWriter.write(Marks.WHITESPACE).write(Keywords.LIMIT);

            if (selectSql.getLimitFrom() != null && selectSql.getLimitFrom() > 0) {
                codeWriter.write(Marks.WHITESPACE).write(selectSql.getLimitFrom().toString()).write(Marks.COMMA);
            }

            codeWriter.write(Marks.WHITESPACE).write(selectSql.getLimitSize().toString());
        }

        // ;
        codeWriter.write(Marks.SEMICOLON);
    }

    private static void generateColumn(CodeWriter codeWriter, Column column) {

        if (!Strings.isNullOrEmpty(column.getColumnTable())) {
            codeWriter.write(Marks.BACKQUOTE).write(column.getColumnTable()).write(Marks.BACKQUOTE);

            codeWriter.write(Marks.DOT);
        }

        codeWriter.write(Marks.BACKQUOTE).write(column.getColumnName()).write(Marks.BACKQUOTE);

        if (!Strings.isNullOrEmpty(column.getColumnAs())) {
            codeWriter.write(Marks.WHITESPACE).write(Keywords.AS).write(Marks.WHITESPACE).write(column.getColumnAs());
        }
    }

    private void generateJoin(CodeWriter codeWriter, Join join) {
        //joinType
        if (join.getJoinType() == null) {
            codeWriter.write(Marks.WHITESPACE).write(Keywords.JOIN);
        } else {
            switch (join.getJoinType()) {
                case LeftJoin:
                    codeWriter.write(Marks.WHITESPACE).write(Keywords.LEFT).write(Marks.WHITESPACE).write(Keywords.JOIN);
                    break;
                case CrossJoin:
                    codeWriter.write(Marks.WHITESPACE).write(Keywords.CROSS).write(Marks.WHITESPACE).write(Keywords.JOIN);
                    break;
                case InnerJoin:
                    codeWriter.write(Marks.WHITESPACE).write(Keywords.INNER).write(Marks.WHITESPACE).write(Keywords.JOIN);
                    break;
                case RightJoin:
                    codeWriter.write(Marks.WHITESPACE).write(Keywords.RIGHT).write(Marks.WHITESPACE).write(Keywords.JOIN);
                    break;
                case None:
                default:
                    codeWriter.write(Marks.WHITESPACE).write(Keywords.JOIN);
                    break;
            }
        }

        generateTable(codeWriter, join.getTable());

        //on
        if (!Strings.isNullOrEmpty(join.getOn())) {
            codeWriter.write(Marks.WHITESPACE).write(Keywords.ON).write(Marks.WHITESPACE).write(join.getOn());
        }
    }

    private void generateFromTable(CodeWriter codeWriter, Table table) {
        if (table == null) {
            return;
        }

        codeWriter.write(Marks.WHITESPACE).write(Keywords.FROM);

        generateTable(codeWriter, table);
    }

    private void generateTable(CodeWriter codeWriter, Table table) {
        if (table == null) {
            return;
        }

        codeWriter.write(Marks.WHITESPACE);

        //tableSchema
        if (!Strings.isNullOrEmpty(table.getTableSchema())) {
            codeWriter.write(Marks.BACKQUOTE).write(table.getTableSchema()).write(Marks.BACKQUOTE);
            codeWriter.write(Marks.DOT);
        }

        // tableName
        codeWriter.write(Marks.BACKQUOTE).write(table.getTableName()).write(Marks.BACKQUOTE);

        if (!Strings.isNullOrEmpty(table.getTableAlias())) {
            codeWriter.write(Marks.WHITESPACE).write(table.getTableAlias());
        }
    }

    private void generateOrderBy(List<OrderBy> orderByList, CodeWriter codeWriter) {
        if (orderByList == null || orderByList.isEmpty()) {
            return;
        }
        codeWriter.write(Marks.WHITESPACE).write(Keywords.ORDER_BY);
        for (int index = 0, length = orderByList.size(); index < length; index++) {
            codeWriter.write(Marks.WHITESPACE);
            OrderBy orderBy = orderByList.get(index);
            codeWriter.write(Marks.BACKQUOTE).write(orderBy.getColumnName()).write(Marks.BACKQUOTE);
            if (orderBy.getDesc() != null && orderBy.getDesc()) {
                codeWriter.write(Marks.WHITESPACE).write(Keywords.DESC);
            }
            if (index < length - 1) {
                codeWriter.write(Marks.COMMA);
            }
        }
    }

    private void generateGroupBy(GroupBy groupBy, CodeWriter codeWriter, Parameters parameters) {
        if (groupBy == null) {
            return;
        }

        if (groupBy.getColumnNames() == null || groupBy.getColumnNames().isEmpty()) {
            return;
        }

        generateGroupByColumns(groupBy.getColumnNames(), codeWriter);

        if (groupBy.getHaving() != null) {
            generateHaving(groupBy.getHaving(), codeWriter, parameters);
        }
    }

    private void generateHaving(ConditionStatement having, CodeWriter codeWriter, Parameters parameters) {
        codeWriter.write(Marks.WHITESPACE).write(Keywords.HAVING).write(Marks.WHITESPACE);
        generateCondition(having.getCondition(), codeWriter, parameters, false);
    }

    private void generateGroupByColumns(List<String> columnNames, CodeWriter codeWriter) {
        codeWriter.write(Marks.WHITESPACE).write(Keywords.GROUP_BY);
        for (int index = 0, length = columnNames.size(); index < length; index++) {
            codeWriter.write(Marks.WHITESPACE);
            String groupBy = columnNames.get(index);
            codeWriter.write(Marks.BACKQUOTE).write(groupBy).write(Marks.BACKQUOTE);
            if (index < length - 1) {
                codeWriter.write(Marks.COMMA);
            }
        }
    }
}
