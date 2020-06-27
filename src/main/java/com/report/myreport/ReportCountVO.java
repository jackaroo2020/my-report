package com.report.myreport;

import lombok.Data;

/**
 * @description  描述
 *
 * @author  LT
 * @date  2020/6/27
 */
@Data
public class ReportCountVO {

    //用例总数	用例通过数	用例失败数	用未执行数  错误数
    private int total;

    private int pass;

    private int fail;

    private int skip;

    private int error;

}
