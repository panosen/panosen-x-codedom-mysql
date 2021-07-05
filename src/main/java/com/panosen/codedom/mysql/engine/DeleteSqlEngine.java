package com.panosen.codedom.mysql.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.mysql.DeleteSql;
import com.panosen.codedom.mysql.Parameters;
import com.panosen.codedom.mysql.builder.DeleteSqlBuilder;

import java.io.StringWriter;

public class DeleteSqlEngine extends SqlEngine {

    public GenerationResponse generate(DeleteSqlBuilder deleteSqlBuilder) {
        GenerationResponse generationResponse = new GenerationResponse();

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        Parameters parameters = new Parameters();

        generate(deleteSqlBuilder.getDeleteSql(), codeWriter, parameters);

        generationResponse.setSql(stringWriter.toString());
        generationResponse.setParameters(parameters);

        return generationResponse;
    }

    private void generate(DeleteSql deleteSql, CodeWriter codeWriter, Parameters parameters) {

        // delete from
        codeWriter.write(Keywords.DELETE).write(Marks.WHITESPACE).write(Keywords.FROM).write(Marks.WHITESPACE);

        // `table`
        codeWriter.write(Marks.BACKQUOTE).write(deleteSql.getTableName()).write(Marks.BACKQUOTE);

        // where
        generateWhere(deleteSql.getWhere(), codeWriter, parameters);

        codeWriter.write(Marks.SEMICOLON);
    }
}
