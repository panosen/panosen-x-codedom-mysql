package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import com.panosen.codedom.mysql.enums.JoinType;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilderJoinTest {

    @Test
    public void build1() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count()
                .from("student");

        selectSqlBuilder.join("book").on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student` join `book` on x = y;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build2() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count()
                .from("student");

        selectSqlBuilder.join("book", JoinType.RightJoin).on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student` right join `book` on x = y;";

        Assert.assertEquals(expected, actual);
    }
}