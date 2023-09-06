package com.panosen.codedom.mysql.enums;

public enum JoinType {

    /**
     * 无
     */
    None,

    /**
     * 内连接
     */
    InnerJoin,

    /**
     * 外连接(左)
     */
    LeftJoin,

    /**
     * 外连接(右)
     */
    RightJoin,

    /**
     * 交叉连接
     */
    CrossJoin;
}
