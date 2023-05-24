package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilderCountTest {

    @Test
    public void build1() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count()
                .from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build3() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count()
                .from("table","information_schema");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `information_schema`.`table`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build2() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count()
                .columns("name", "age")
                .from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student`;";

        Assert.assertEquals(expected, actual);
    }
}