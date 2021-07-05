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
                .from("student")
                .limit(10);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` limit 10;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build3() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` limit 10, 15;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build4() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .equal("age", Types.INTEGER, 12);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `age` = ? limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, parameters.size());
    }

    @Test
    public void build5() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where().must()
                .equal("x", Types.INTEGER, 12)
                .equal("y", Types.INTEGER, 13);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `x` = ? and `y` = ? limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.size());
    }

    @Test
    public void build6() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        MustConditions must = selectSqlBuilder.where().must();
        must.should()
                .equal("x", Types.INTEGER, 12)
                .equal("y", Types.INTEGER, 13);
        must.should()
                .equal("a", Types.INTEGER, 14)
                .equal("b", Types.INTEGER, 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where (`x` = ? or `y` = ?) and (`a` = ? or `b` = ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.size());
        Assert.assertEquals(12, parameters.get(0).getValue());
        Assert.assertEquals(13, parameters.get(1).getValue());
        Assert.assertEquals(14, parameters.get(2).getValue());
        Assert.assertEquals(15, parameters.get(3).getValue());
    }

    @Test
    public void build7() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        ShouldConditions should = selectSqlBuilder.where().should();
        should.must()
                .equal("x", Types.INTEGER, 12)
                .equal("y", Types.INTEGER, 13);
        should.must()
                .equal("a", Types.INTEGER, 14)
                .equal("b", Types.INTEGER, 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where (`x` = ? and `y` = ?) or (`a` = ? and `b` = ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.size());
        Assert.assertEquals(12, parameters.get(0).getValue());
        Assert.assertEquals(13, parameters.get(1).getValue());
        Assert.assertEquals(14, parameters.get(2).getValue());
        Assert.assertEquals(15, parameters.get(3).getValue());
    }

    @Test
    public void build8() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("age", Types.INTEGER, 12, 13);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `age` in (?, ?) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.size());
        Assert.assertEquals(12, parameters.get(0).getValue());
        Assert.assertEquals(13, parameters.get(1).getValue());
    }

    @Test
    public void build9() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .columns("name", "age")
                .from("student");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select `name`, `age` from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build10() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .orderBy("name")
                .orderBy("age", true);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` order by `name`, `age` desc;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build11() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .groupBy("name")
                .groupBy("age");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` group by `name`, `age`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build12() {

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

        new SelectSqlEngine().generateCondition(where.getCondition(), codeWriter, parameters, false);

        String actual = stringWriter.toString();
        String expected = "`a` = ?";

        Assert.assertEquals(expected, actual);
    }
}