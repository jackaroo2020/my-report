package com.report;

import com.report.myreport.ReportEntity;
import com.report.myreport.ReportUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.util.DateUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

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
        List<ReportEntity> list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            ReportEntity entity = new ReportEntity();
            Date date = new Date();
            String time = DateUtils.format(date,"yyyy-mm-dd hh:mm:ss", Locale.CHINA);
            entity.setExec_date(time);
            Random random = new Random();
            int n = random.nextInt(5);
            String[] arr ={"通过","失败","错误","未执行","通过"};
            entity.setExec_rlt(arr[n]);
            entity.setErrorinfo(error);
            entity.setTc_name("test.runner.Runner1Test.report."+i);
            list.add(entity);
        }
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
