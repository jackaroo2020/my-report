package com.report.myreport;

import lombok.Data;

/**
 * @description  报告展示实体类
 *
 * @author  LT
 * @date  2020/6/27
 */
@Data
public class ReportVO {

    /**
     * 测试类
     */
    private String tc_name="";
    /**
     * 执行时间
     */
    private String exec_date;
    /**
     * 执行结果
     */
    private String exec_rlt;

    /**
     * 错误信息切割处理
     */
    private String[]  tempError;


}
