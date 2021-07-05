package com.panosen.codedom.mysql.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.mysql.*;
import com.panosen.codedom.mysql.builder.SelectSqlBuilder;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

public class SelectSqlEngine {

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

        // columns
        if (selectSql.getColumnNameList() != null && !selectSql.getColumnNameList().isEmpty()) {
            for (int index = 0, length = selectSql.getColumnNameList().size(); index < length; index++) {
                codeWriter.write(Marks.BACKQUOTE).write(selectSql.getColumnNameList().get(index)).write(Marks.BACKQUOTE);
                if (index < length - 1) {
                    codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
                }
            }
        } else {
            codeWriter.write(Marks.STAR);
        }

        // from
        codeWriter.write(Marks.WHITESPACE).write(Keywords.FROM).write(Marks.WHITESPACE);

        // tableName
        codeWriter.write(Marks.BACKQUOTE).write(selectSql.getTableName()).write(Marks.BACKQUOTE);

        if (selectSql.getWhere() != null) {
            codeWriter.write(Marks.WHITESPACE);
            generateWhere(selectSql.getWhere(), codeWriter, parameters);
        }

        generateOrderBy(selectSql.getOrderByList(), codeWriter);

        generateGroupBy(selectSql.getGroupBy(), codeWriter, parameters);

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

    public void generateWhere(Where where, CodeWriter codeWriter, Parameters parameters) {
        if (where == null || where.getCondition() == null) {
            return;
        }

        codeWriter.write(Keywords.WHERE).write(Marks.WHITESPACE);

        generateCondition(where.getCondition(), codeWriter, parameters, false);
    }

    public void generateCondition(Condition condition, CodeWriter codeWriter, Parameters parameters, boolean parenthesis) {
        if (condition == null) {
            return;
        }

        if (condition instanceof EqualCondition) {
            generateEqualCondition((EqualCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof InCondition) {
            generateInCondition((InCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof MustConditions) {
            generateMustConditions((MustConditions) condition, codeWriter, parameters, parenthesis);
        }

        if (condition instanceof ShouldConditions) {
            generateShoudConditions((ShouldConditions) condition, codeWriter, parameters, parenthesis);
        }
    }

    private void generateMustConditions(MustConditions mustConditions, CodeWriter codeWriter, Parameters parameters, boolean parenthesis) {
        generateConditions(mustConditions.getConditionList(), codeWriter, parameters, Keywords.AND, parenthesis);
    }

    private void generateShoudConditions(ShouldConditions shouldConditions, CodeWriter codeWriter, Parameters parameters, boolean parenthesis) {
        generateConditions(shouldConditions.getConditionList(), codeWriter, parameters, Keywords.OR, parenthesis);
    }

    private void generateConditions(List<Condition> conditionList, CodeWriter codeWriter, Parameters parameters, String logicalOperator, boolean parenthesis) {
        if (conditionList == null || conditionList.isEmpty()) {
            return;
        }

        if (parenthesis && conditionList.size() > 1) {
            codeWriter.write(Marks.LEFT_BRACKET);
        }

        Iterator<Condition> iterator = conditionList.iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext) {
            generateCondition(iterator.next(), codeWriter, parameters, true);

            hasNext = iterator.hasNext();
            if (hasNext) {
                codeWriter.write(Marks.WHITESPACE).write(logicalOperator).write(Marks.WHITESPACE);
            }
        }

        if (parenthesis && conditionList.size() > 1) {
            codeWriter.write(Marks.RIGHT_BRACKET);
        }
    }

    private void generateEqualCondition(EqualCondition equalCondition, CodeWriter codeWriter, Parameters parameters) {
        codeWriter.write(Marks.BACKQUOTE).write(equalCondition.getFieldName()).write(Marks.BACKQUOTE)
                .write(Marks.WHITESPACE)
                .write(Marks.EQUAL)
                .write(Marks.WHITESPACE)
                .write(Marks.QUESTION);
        parameters.add(equalCondition.getDbType(), equalCondition.getValue());
    }

    private void generateInCondition(InCondition inCondition, CodeWriter codeWriter, Parameters parameters) {
        codeWriter.write(Marks.BACKQUOTE).write(inCondition.getFieldName()).write(Marks.BACKQUOTE)
                .write(Marks.WHITESPACE)
                .write(Keywords.IN)
                .write(Marks.WHITESPACE);

        // (
        codeWriter.write(Marks.LEFT_BRACKET);

        for (int i = 0, length = inCondition.getValues().size(); i < length; i++) {
            codeWriter.write(Marks.QUESTION);
            parameters.add(inCondition.getDbType(), inCondition.getValues().get(i));

            if (i < length - 1) {
                codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
            }
        }

        // )
        codeWriter.write(Marks.RIGHT_BRACKET);
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
            generateCondition(groupBy.getHaving(), codeWriter, parameters, false);
        }
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
