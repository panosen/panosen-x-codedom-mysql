package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import com.panosen.codedom.mysql.enums.JoinType;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilder_Join_Test {

    @Test
    public void build1() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count();
        selectSqlBuilder.from("student");

        selectSqlBuilder.join("book").on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student` join `book` on x = y;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build2() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .count();
        selectSqlBuilder.from("student");

        selectSqlBuilder.join("book").type(JoinType.RightJoin).on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select count(*) from `student` right join `book` on x = y;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build3() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.column("c").table("s").as("vv");
        selectSqlBuilder.column("v").table("s");
        selectSqlBuilder.from("student").alias("s");

        selectSqlBuilder.join("book").type(JoinType.RightJoin).on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `s`.`c` as vv, `s`.`v` from `student` s right join `book` on x = y;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build4() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.column("c").table("s").as("vv");
        selectSqlBuilder.column("v").table("s");
        selectSqlBuilder.from("student").alias("s");

        selectSqlBuilder.join("book").schema("library").on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `s`.`c` as vv, `s`.`v` from `student` s join `library`.`book` on x = y;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build5() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.column("c").table("s").as("vv");
        selectSqlBuilder.column("v").table("s");
        selectSqlBuilder.from("student").alias("s");

        selectSqlBuilder.join("book").alias("b").on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `s`.`c` as vv, `s`.`v` from `student` s join `book` b on x = y;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build6() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder();
        selectSqlBuilder.column("c").table("s").as("vv");
        selectSqlBuilder.column("v").table("s");
        selectSqlBuilder.from("student").alias("s");

        selectSqlBuilder.join("book").schema("library").alias("b").on("x = y");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `s`.`c` as vv, `s`.`v` from `student` s join `library`.`book` b on x = y;";

        Assert.assertEquals(expected, actual);
    }
}