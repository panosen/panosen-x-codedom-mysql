package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.Parameters;
import com.panosen.codedom.mysql.engine.DeleteSqlEngine;
import com.panosen.codedom.mysql.engine.GenerationResponse;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;

public class DeleteSqlBuilderTest {

    @Test
    public void test1() {
        DeleteSqlBuilder deleteSqlBuilder = new DeleteSqlBuilder()
                .from("student");

        GenerationResponse generationResponse = new DeleteSqlEngine().generate(deleteSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "delete from `student`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        DeleteSqlBuilder deleteSqlBuilder = new DeleteSqlBuilder()
                .from("student");

        deleteSqlBuilder.where().equal("id", Types.INTEGER, 13);

        GenerationResponse generationResponse = new DeleteSqlEngine().generate(deleteSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();
        String expected = "delete from `student` where `id` = ?;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, parameters.size());
        Assert.assertEquals(13, parameters.get(0).getValue());
    }

    @Test
    public void test3() {
        DeleteSqlBuilder deleteSqlBuilder = new DeleteSqlBuilder()
                .from("student");

        deleteSqlBuilder.where().must()
                .equal("m", Types.INTEGER, 7)
                .notEqual("n", Types.INTEGER, 9)
                .gt("a", Types.INTEGER, 15)
                .gte("b", Types.INTEGER, 17)
                .lt("c", Types.INTEGER, 19)
                .lte("d", Types.INTEGER, 21);

        GenerationResponse generationResponse = new DeleteSqlEngine().generate(deleteSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();
        String expected = "delete from `student` where `m` = ? and `n` != ? and `a` > ? and `b` >= ? and `c` < ? and `d` <= ?;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(6, parameters.size());
        Assert.assertEquals(7, parameters.get(0).getValue());
        Assert.assertEquals(9, parameters.get(1).getValue());
        Assert.assertEquals(15, parameters.get(2).getValue());
        Assert.assertEquals(17, parameters.get(3).getValue());
        Assert.assertEquals(19, parameters.get(4).getValue());
        Assert.assertEquals(21, parameters.get(5).getValue());
    }
}