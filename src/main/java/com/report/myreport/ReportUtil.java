package com.report.myreport;

import com.alibaba.fastjson.JSON;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description  报告生成类
 *
 * @author  LT
 * @date  2020/6/27
 */
public class ReportUtil {

    /**
     * 业务数据存放在map中
     * @param entityList
     * @return
     */
    public static Map<String, Object> htmlData(List<ReportEntity> entityList){
        Map<String, Object> data = new HashMap<>();

        /**
         * 对错误信息进行处理
         */
        // 计算通过等数
        ReportCountVO vo = calcCountVO(entityList);

        // 实体转换VO
        List<ReportVO> vos = toReportVO(entityList);
        data.put(Constants.REPORT_RESULT_DATA,vos);
        data.put(Constants.REPORT_COUNT_DATA,vo);
        return data;
    }


    /**
     * 生成报告
     * @param data
     */
    public static void generator(Map<String, Object> data) {
        // 设置返回html模版的数据
        Context context = new Context();
        context.setVariable(Constants.REPORT_RESULT_DATA, data.get(Constants.REPORT_RESULT_DATA));
        context.setVariable(Constants.REPORT_COUNT_DATA, data.get(Constants.REPORT_COUNT_DATA));
        // 生成报告
        try {
            genReport(context,"report_template_02","report_result_02.html");
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    /**
     * 调用模版引擎生成报告
     * @param context 内容
     * @param templeFilePrefix 模版文件名称，不用后缀名
     * @param targetFile 生成的模版文件名称
     */
    public static void genReport(Context context,String templeFilePrefix,String targetFile) throws IOException {
        //构造模板引擎
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        // 生成报告文件
        FileWriter write = new FileWriter(targetFile);
        templateEngine.process(templeFilePrefix, context, write);
    }


    private static ReportCountVO calcCountVO(List<ReportEntity> entityList) {
        // 总数
        int pass = 0;
        int fail = 0;
        int skip = 0;
        int error =0;
        int total = 0;
        for (ReportEntity reportEntity : entityList) {
            if (StatusEnum.PASS.getName().equals(reportEntity.getExec_rlt())){
                pass = pass+1;
            } else if (StatusEnum.FAIL.getName().equals(reportEntity.getExec_rlt())){
                fail = fail +1;
            } else if (StatusEnum.SKIP.getName().equals(reportEntity.getExec_rlt())){
                skip = skip +1;
            } else {
                error = error +1;
            }
        }
        total = pass+fail+skip+error;

        ReportCountVO vo = new ReportCountVO();
        vo.setFail(fail);
        vo.setPass(pass);
        vo.setSkip(skip);
        vo.setError(error);
        vo.setTotal(total);
        return vo;
    }

    private static  List<ReportVO> toReportVO(List<ReportEntity> entityList){
        List<ReportVO> vos = new ArrayList<>();
        entityList.forEach(item-> {
            String[] tempError = {};
            // 过滤成功数据
            //if (item != null && (item.getExec_rlt() != StatusEnum.PASS.getName())) {
                if(item.getErrorinfo().contains("\r\n")){
                    tempError = item.getErrorinfo().split("\r\n");
                }
                ReportVO vo = new ReportVO();
                // 设置错误日志
                vo.setTempError(tempError);
                vo.setExec_date(item.getExec_date());
                vo.setTc_name(item.getTc_name());
                vo.setExec_rlt(item.getExec_rlt());
                vos.add(vo);
            //}
        });
        return vos;
    }


}
