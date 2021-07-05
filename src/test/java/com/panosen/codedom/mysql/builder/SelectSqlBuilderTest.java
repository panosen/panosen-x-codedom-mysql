package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.mysql.*;
import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.sql.Types;

public class SelectSqlBuilderTest {

    @Test
    public void build1() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build2() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .columns("name", "age")
                .from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `name`, `age` from `student`;";

        Assert.assertEquals(expected, actual);
    }
}