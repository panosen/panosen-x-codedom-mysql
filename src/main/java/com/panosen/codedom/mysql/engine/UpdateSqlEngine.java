package com.panosen.codedom.mysql.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.mysql.Parameters;
import com.panosen.codedom.mysql.Statement;
import com.panosen.codedom.mysql.Statements;
import com.panosen.codedom.mysql.UpdateSql;
import com.panosen.codedom.mysql.builder.UpdateSqlBuilder;
import sun.security.util.Length;

import java.io.StringWriter;

public class UpdateSqlEngine extends SqlEngine {

    public GenerationResponse generate(UpdateSqlBuilder updateSqlBuilder) {
        GenerationResponse generationResponse = new GenerationResponse();

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        Parameters parameters = new Parameters();

        generate(updateSqlBuilder.getUpdateSql(), codeWriter, parameters);

        generationResponse.setSql(stringWriter.toString());
        generationResponse.setParameters(parameters);

        return generationResponse;
    }

    private void generate(UpdateSql updateSql, CodeWriter codeWriter, Parameters parameters) {

        // update
        codeWriter.write(Keywords.UPDATE).write(Marks.WHITESPACE);

        // `table`
        codeWriter.write(Marks.BACKQUOTE).write(updateSql.getTableName()).write(Marks.BACKQUOTE);

        //set
        generateStatements(updateSql.getStatements(), codeWriter, parameters);

        // where
        generateWhere(updateSql.getWhere(), codeWriter, parameters);

        codeWriter.write(Marks.SEMICOLON);
    }

    private void generateStatements(Statements statements, CodeWriter codeWriter, Parameters parameters) {
        if (statements == null) {
            return;
        }
        if (statements.getStatementList() == null || statements.getStatementList().isEmpty()) {
            return;
        }

        codeWriter.write(Marks.WHITESPACE).write(Keywords.SET);
        for (int i = 0, length = statements.getStatementList().size(); i < length; i++) {
            Statement statement = statements.getStatementList().get(i);
            codeWriter
                    .write(Marks.WHITESPACE)
                    .write(Marks.BACKQUOTE).write(statement.getFieldName()).write(Marks.BACKQUOTE)
                    .write(Marks.WHITESPACE).write(Marks.EQUAL).write(Marks.WHITESPACE).write(Marks.QUESTION);
            if (i < length - 1) {
                codeWriter.write(Marks.COMMA);
            }
        }
    }
}
