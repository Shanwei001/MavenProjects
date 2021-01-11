package com.shanweicode;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * @author shanweih
 * @create 2020/12/31 上午10:44
 * tool IntelliJ IDEA
 * @Description:
 */
public class TestPoi {
    /* 对excel进行读操作 */
    @Test
    public void exportExcel() throws Exception {
        // 创建工作薄
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("/Users/shanweih/test.xlsx");
        // 获取工作表，即可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        // 遍历工作表获得行对象
        for (Row row : sheet) {
            for (Cell cell : row) {
                // 获取单元格的值
                String value = cell.getStringCellValue();
                System.out.println("value = " + value);
            }
        }
        xssfWorkbook.close();
    }
    @Test
    public void importExcel()throws Exception{
        // 在内存中创建一个Excel文件
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        // 创建工作表，指定工作表的名称
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("尚硅谷");

        // 创建行，0表示第一个单元格
        XSSFRow row = xssfSheet.createRow(0);
        // 创建单元格，0表示第一个单元格
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("性别");
        // 创建第二行
        XSSFRow row1 = xssfSheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("hushanwei");
        row1.createCell(2).setCellValue("male");
        // 创建第三行
        XSSFRow row2 = xssfSheet.createRow(2);
        row1.createCell(0).setCellValue("2");
        row1.createCell(1).setCellValue("shanwei");
        row1.createCell(2).setCellValue("female");
        // 通过输出流将workbook对象下载到磁盘
        FileOutputStream outputStream = new FileOutputStream("/Users/shanweih/qiniu.xls");
        xssfWorkbook.write(outputStream);
        outputStream.flush(); // 刷新
        outputStream.close(); // 关闭
        xssfWorkbook.close();

    }
}
