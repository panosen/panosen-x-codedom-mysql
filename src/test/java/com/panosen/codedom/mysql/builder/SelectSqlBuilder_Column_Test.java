package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import com.panosen.codedom.mysql.enums.JoinType;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilder_Column_Test {

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

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.column("name");
        selectSqlBuilder.column("age");
        selectSqlBuilder.from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `name`, `age` from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build3() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.column("c").table("s").as("vv");
        selectSqlBuilder.column("v").table("s");
        selectSqlBuilder.from("student").alias("s");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `s`.`c` as vv, `s`.`v` from `student` s;";

        Assert.assertEquals(expected, actual);
    }
}