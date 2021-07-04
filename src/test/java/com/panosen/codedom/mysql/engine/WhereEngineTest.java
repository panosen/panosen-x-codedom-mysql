package com.panosen.codedom.mysql.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.mysql.Parameters;
import com.panosen.codedom.mysql.Where;
import com.panosen.codedom.mysql.EqualCondition;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.sql.Types;

public class WhereEngineTest {

    @Test
    public void generate() {

        Where where = new Where();
        {
            EqualCondition equalCondition = new EqualCondition();
            equalCondition.setFieldName("a");
            equalCondition.setDbType(Types.INTEGER);
            equalCondition.setValue(1);
            where.setCondition(equalCondition);
        }

        StringWriter stringWriter = new StringWriter();
        CodeWriter codeWriter = new CodeWriter(stringWriter);

        Parameters parameters = new Parameters();

        new WhereEngine().generate(where, codeWriter, parameters);

        String actual = stringWriter.toString();
        String expected = "where `a` = ?";

        Assert.assertEquals(expected, actual);
    }
}