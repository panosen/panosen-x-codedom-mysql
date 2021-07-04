package com.panosen.codedom.mysql.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.mysql.Parameters;
import com.panosen.codedom.mysql.SelectSql;
import com.panosen.codedom.mysql.builder.SelectSqlBuilder;

import java.io.StringWriter;

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
        if (selectSql.getColumnNames() != null && !selectSql.getColumnNames().isEmpty()) {
            for (int index = 0, length = selectSql.getColumnNames().size(); index < length; index++) {
                codeWriter.write(Marks.BACKQUOTE).write(selectSql.getColumnNames().get(index)).write(Marks.BACKQUOTE);
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
            new WhereEngine().generate(selectSql.getWhere(), codeWriter, parameters);
        }

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
}
