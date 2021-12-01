package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.Parameters;
import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.InsertSqlEngine;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;

public class InsertSqlBuilderTest {

    @Test
    public void build1() {

        InsertSqlBuilder insertSqlBuilder = new InsertSqlBuilder()
                .intoTable("student")
                .value("name", Types.VARCHAR, "zhangsan")
                .value("age", Types.INTEGER, 17);

        GenerationResponse generationResponse = new InsertSqlEngine().generate(insertSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "insert into `student` (`name`, `age`) values (?, ?);";

        Assert.assertEquals(expected, actual);

        Parameters parameters = generationResponse.getParameters();
        Assert.assertEquals(2, parameters.size());
        Assert.assertEquals(Types.VARCHAR, parameters.get(0).getDbType());
        Assert.assertEquals("zhangsan", parameters.get(0).getValue());
        Assert.assertEquals(Types.INTEGER, parameters.get(1).getDbType());
        Assert.assertEquals(17, parameters.get(1).getValue());
    }
}