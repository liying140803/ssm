package cn.no7player.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by liying
 * Date 2020-08-11
 */
public class WordUtil {


    /**
     * 生成word文件
     *
     * @param dataMap      word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param filePath     文件生成的目标路径，例如：D:/wordFile/
     * @param fileName     生成的文件名称，例如：test.doc
     */
    @SuppressWarnings("unchecked")
    public static void createWord(HttpServletResponse response, Map dataMap, String templateName, String filePath, String fileName) {
        try {
            Assert.notNull(fileName, "导出文件名不能为空");
            Assert.notNull(templateName, "模板文件不能为空");
            //创建配置实例
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            //ftl模板文件
            configuration.setClassForTemplateLoading(WordUtil.class, "/templates");
            //获取模板
            Template template = configuration.getTemplate(templateName);
          /* //输出文件
            File outFile = new File(filePath + File.separator + fileName);
            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }*/
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            OutputStream out = response.getOutputStream();
            //将模板和数据模型合并生成文件
            //Writer out1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            Writer out2 = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            //生成文件
            template.process(dataMap, out2);
            //关闭流
            out2.flush();
            out2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
