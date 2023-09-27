package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import com.panosen.codedom.mysql.enums.JoinType;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilder_FromTable_Test {

    @Test
    public void build1() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count();
        selectSqlBuilder.from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build2() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count();
        selectSqlBuilder.from("student").schema("school");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `school`.`student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build3() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count();
        selectSqlBuilder.from("student").alias("s");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student` s;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build4() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count();
        selectSqlBuilder.from("student").schema("school").alias("s");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `school`.`student` s;";

        Assert.assertEquals(expected, actual);
    }
}