package cn.no7player.util;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;


public class ExcelUtil {


    private static Logger logger = Logger.getLogger(ExcelUtil.class);


    public static boolean downloadExcel(String fileName, String sheetTitle, String[] title, List list, HttpServletResponse response) {

        HSSFWorkbook wb = new HSSFWorkbook();//创建excel表
        HSSFSheet sheet = wb.createSheet(sheetTitle);
        sheet.setDefaultColumnWidth(20);//设置默认行宽

        //表头样式（加粗，水平居中，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //设置边框样式
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框

        HSSFFont fontStyle = wb.createFont();
        /*   fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);*/

        cellStyle.setFont(fontStyle);

        //标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        cellStyle2.setFont(fontStyle);
        //设置边框样式
        cellStyle2.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle2.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle2.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle2.setBorderRight(BorderStyle.THIN);//右边框

        //字段样式（垂直居中）
        HSSFCellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //设置边框样式
        cellStyle3.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle3.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle3.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle3.setBorderRight(BorderStyle.THIN);//右边框

        //创建表头
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(20);//行高

        HSSFCell cell = row.createCell(0);
        cell.setCellValue(sheetTitle);
        cell.setCellStyle(cellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (title.length - 1)));
        //创建标题
        HSSFRow rowTitle = sheet.createRow(1);
        rowTitle.setHeightInPoints(20);

        HSSFCell hc;
        for (int i = 0; i < title.length; i++) {
            hc = rowTitle.createCell(i);
            hc.setCellValue(title[i]);
            hc.setCellStyle(cellStyle2);
        }
        ByteArrayOutputStream out = null;
        try {
            //创建表格数据
            Field[] fields;
            int i = 2;
            for (Object obj : list) {
                //获取实体类字段
                fields = obj.getClass().getDeclaredFields();
                HSSFRow rowBody = sheet.createRow(i);
                rowBody.setHeightInPoints(20);
                int j = 0;
                for (Field f : fields) {
                    f.setAccessible(true);
                    Object va = f.get(obj);
                    if (null == va) {
                        va = "";
                    }
                    hc = rowBody.createCell(j);
                    hc.setCellValue(va.toString());
                    hc.setCellStyle(cellStyle3);
                    j++;
                }
                i++;
            }

     /*      out = new ByteArrayOutputStream();
            wb.write(out);
            result = out.toByteArray();*/
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");
            response.flushBuffer();
            wb.write(response.getOutputStream());
        } catch (Exception ex) {
            Logger.getLogger(ExcelUtil.class.getName()).log(null, ex);
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException ex) {
                logger.info(ExcelUtil.class.getName(), ex);
            } finally {
                try {
                    wb.close();
                } catch (IOException ex) {
                    logger.info(ExcelUtil.class.getName(), ex);
                }
            }
        }
        return true;
    }
}