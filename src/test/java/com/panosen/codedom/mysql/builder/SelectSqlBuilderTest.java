package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilderTest {

    @Test
    public void build1() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build3() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.from("table").schema("information_schema");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `information_schema`.`table`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build2() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.column("name");
        selectSqlBuilder.column("age");
        selectSqlBuilder.from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `name`, `age` from `student`;";

        Assert.assertEquals(expected, actual);
    }
}