package com.panosen.codedom.mysql.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.mysql.*;

import java.util.Iterator;
import java.util.List;

public class WhereEngine {

    public void generate(Where where, CodeWriter codeWriter, Parameters parameters) {
        if (where == null || where.getCondition() == null) {
            return;
        }

        codeWriter.write(Keywords.WHERE).write(Marks.WHITESPACE);

        generateCondition(where.getCondition(), codeWriter, parameters, false);
    }

    private void generateCondition(Condition condition, CodeWriter codeWriter, Parameters parameters, boolean parenthesis) {
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
}
