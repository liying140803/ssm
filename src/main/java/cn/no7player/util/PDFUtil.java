package cn.no7player.util;

import cn.no7player.controller.UserController;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liying
 * Date 2020-08-12
 */


public class PDFUtil {

    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 创建一个pdf并打开
     *
     * @param outpath pdf路径
     */
    public static void createPdf(String outpath) throws DocumentException, IOException {
        //页面大小
        //Rectangle rect = new Rectangle(PageSize.A4.rotate());//文档横方向
        Rectangle rect = new Rectangle(PageSize.A4);//文档竖方向
        //如果没有则创建
        File saveDir = new File(outpath);
        File dir = saveDir.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Document doc = new Document(rect);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(outpath));
        //PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
        //文档属性
        doc.addTitle("Title@wpixel");
        doc.addAuthor("Author@wpixel");
        doc.addSubject("Subject@wpixel");
        doc.addKeywords("Keywords@wpixel");
        doc.addCreator("Creator@wpixel");
        //页边空白
        doc.setMargins(40, 40, 40, 40);
        //打开文档
        doc.open();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        doc.add(PdfFontUtils.getFont(1, "合作协议"));
        doc.add(PdfFontUtils.getFont(6, "甲方：***"));
        doc.add(PdfFontUtils.getFont(6, "乙方：XXX"));
        doc.add(PdfFontUtils.getFont(6, "时间：" + sdf1.format(new Date())));
        doc.add(PdfFontUtils.getFont(6, "地点：广东省深圳市南山区"));
        Paragraph text05 = PdfFontUtils.getFont(5, "《根据中华人民共和国合同法》的有关规定，经甲、乙双方友好协商，本着长期平等合作.....吧啦吧啦吧啦吧啦吧啦吧啦吧啦吧啦");
        doc.add(text05);

        //一、合作方式及条件
        doc.add(PdfFontUtils.getFont(2, "一、合作方式及条件"));
        doc.add(PdfFontUtils.getFont(5, "1.双方根据国家法律规定建立合作关系，双方严格遵守和执行国家各项方针政策和有关法律、法规和条例规定。 "));
        doc.add(PdfFontUtils.getFont(5, "2.双方严格按照《中华人民共和国招标投标法》及相关规定实施合作。 "));
        doc.add(PdfFontUtils.getFont(5, "3.双方本着密切配合、分工协作、保证质量、按期完成的原则，共同做好工作。 "));

        //二、权利义务
        doc.add(PdfFontUtils.getFont(2, "二、权利义务"));
        doc.add(PdfFontUtils.getFont(5, "1.双方根据国家法律规定建立合作关系，双方严格遵守和执行国家各项方针政策和有关法律、法规和条例规定。 "));
        doc.add(PdfFontUtils.getFont(5, "2.双方严格按照《中华人民共和国招标投标法》及相关规定实施合作。 "));
        doc.add(PdfFontUtils.getFont(5, "3.双方本着密切配合、分工协作、保证质量、按期完成的原则，共同做好工作。 "));

        //三、其他
        doc.add(PdfFontUtils.getFont(2, "三、其他"));
        doc.add(PdfFontUtils.getFont(5, "1.双方根据国家法律规定建立合作关系，双方严格遵守和执行国家各项方针政策和有关法律、法规和条例规定。 "));
        doc.add(PdfFontUtils.getFont(5, "2.双方严格按照《中华人民共和国招标投标法》及相关规定实施合作。 "));
        doc.add(PdfFontUtils.getFont(5, "3.自定义 "));

        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "甲方：（盖章）")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "乙方：（盖章）")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "法定代表人或负责人签章")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "法定代表人或负责人签章")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "地址：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "地址：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "开户银行：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "开户银行：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "邮编：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "邮编：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "授权代理人：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "项目经理：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "电话：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        cell.setBorder(Rectangle.NO_BORDER);
        cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "电话：")));
        cell.setColspan(1);
        cell.setBorder(0);
        table.addCell(cell);
        doc.add(table);
        doc.close();
    }


    public static void downloadFile(HttpServletResponse response, String fileName, String filePath) {
        // 防止中文乱码
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream();
            InputStream is = new FileInputStream(filePath);

            int length = 0;
            byte[] bytes = new byte[1024];
            while ((is != null) && ((length = is.read(bytes)) != -1)) {
                out.write(bytes, 0, length);
            }
            out.close();
            response.flushBuffer();
            is.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("下载pdf异常：" + e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("下载pdf异常：" + e);
        }
    }

}
