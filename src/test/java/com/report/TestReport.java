package com.report;

import com.report.myreport.ReportEntity;
import com.report.myreport.ReportUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestReport {

    @Test
    void generate_report() {
        String error = "";
        try {
            int a = 3/0;
        }catch (Exception e){
            error = getExceptionToString(e);
        }
        //System.out.println(error);
        List<ReportEntity> list = new ArrayList<>();
        ReportEntity entity = new ReportEntity();
        entity.setExec_date("2020-06-23 14:11:43");
        entity.setExec_rlt("失败");
        entity.setErrorinfo(error);
        entity.setTc_name("test.runner.Runner1Test.testconffile1.1");

        ReportEntity entity1 = new ReportEntity();
        entity1.setExec_date("2012-06-23 14:11:43");
        entity1.setExec_rlt("错误");
        entity1.setErrorinfo(error);
        entity1.setTc_name("test.runner.Runner1Test.testconffile1.2");

        list.add(entity);
        list.add(entity1);

        ReportUtil.generator(ReportUtil.htmlData(list));
    }

    /**
     * 将 Exception 转化为 String
     */
    public static String getExceptionToString(Throwable e) {
        if (e == null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
