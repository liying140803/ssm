package cn.no7player.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;


public class PDFReport {

    Document document = new Document();// 建立一个Document对象

    private static Font headfont;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小


    int maxWidth = 520;

    static {
        BaseFont bfChinese;
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//设置字体用宋体
            headfont = new Font(bfChinese, 10, Font.BOLD);// 标题字体大小
            keyfont = new Font(bfChinese, 8, Font.BOLD);// 关键标题字体大小
            textfont = new Font(bfChinese, 8, Font.NORMAL);// 字段字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PDFReport(File file) {
        document.setPageSize(PageSize.A4);// 设置页面大小
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public PdfPCell createCell(String value, com.itextpdf.text.Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    public PdfPCell createCell(String value, com.itextpdf.text.Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    public PdfPCell createCell(String value, com.itextpdf.text.Font font, int align, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    public PdfPTable createTable(int colNumber) {
        PdfPTable table = new PdfPTable(colNumber);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public void generatePDF(int i) throws Exception {
        PdfPTable table = createTable(4);
        table.addCell(createCell("学生信息列表：", keyfont, Element.ALIGN_CENTER, 4, false));

        table.addCell(createCell("姓名", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell("年龄", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell("性别", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell("住址", keyfont, Element.ALIGN_CENTER));

        for (int j = 1; j <= i; j++) {
            table.addCell(createCell("姓名" + j, textfont));
            table.addCell(createCell(j + 15 + "", textfont));
            table.addCell(createCell((j % 2 == 0) ? "男" : "女", textfont));
            table.addCell(createCell("地址" + j, textfont));
        }
        document.add(table);

        document.close();
    }

    public static void main(String[] args) throws Exception {
        File file1 = new File("/Users/klook/Documents/test");
        if (!file1.exists()) {
            file1.mkdir();//创建文件夹
        }
        for (int i = 1; i <= 10; i++) {
            String fileName = "/Users/klook/Documents/test/" + i + ".pdf";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();//在指定目录下创建一个文件
                new PDFReport(file).generatePDF(i);
            }
        }

        System.out.println("运行完毕！");
    }

}
