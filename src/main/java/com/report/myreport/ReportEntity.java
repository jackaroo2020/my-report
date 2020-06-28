package com.report.myreport;

import lombok.Data;

/**
 * 实体类
 */
@Data
public class ReportEntity {

    private int tc_id=0;
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
     * 错误信息
     */
    private String errorinfo="";

}
