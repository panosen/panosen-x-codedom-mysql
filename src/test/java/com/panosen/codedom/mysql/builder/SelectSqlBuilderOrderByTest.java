package com.panosen.codedom.mysql.builder;

import com.panosen.codedom.mysql.engine.GenerationResponse;
import com.panosen.codedom.mysql.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilderOrderByTest {

    @Test
    public void build() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .orderBy("name")
                .orderBy("age", true);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` order by `name`, `age` desc;";

        Assert.assertEquals(expected, actual);
    }
}
