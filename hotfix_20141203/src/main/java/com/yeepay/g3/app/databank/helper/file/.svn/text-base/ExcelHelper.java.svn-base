/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper.file;

import com.yeepay.g3.app.databank.utils.config.ConfigUtils;
import com.yeepay.g3.app.databank.utils.result.FileResult;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * <p>Title: Excel 文件生成 Helper</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-7-29 下午11:04
 */
public class ExcelHelper implements OutputHelper {

    /**
     * 保存 CSV 文件
     *
     * @param rs       内容
     * @param fileName 文件目录及文件名
     */
    public FileResult doSave(Long logId, ResultSet rs, String path, String fileName) throws Exception {
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        int num = 0;

        // 大数据提示选用 CSV 下载, 24字段/记录,20个中文字/字段,1100条记录/64M
        long limit = Math.min(Math.round(Runtime.getRuntime().freeMemory() * columnCount * 0.00000085), 65535);

        // 创建Excel的工作书册 Workbook,对应到一个excel文档
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建Excel的工作sheet,对应到一个excel文档的tab
        HSSFSheet sheet = wb.createSheet("sheet1");

        // 封装表头
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnCount; ++i) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(resultSetMetaData.getColumnLabel(i + 1).trim());
        }

        // 封装正文
        int rowIndex = 0;
        while (rs.next()) {
            if (limit <= rs.getRow()) {
                break;
            }

            row = sheet.createRow(++rowIndex);
            for (int i = 0; i < columnCount; ++i) {
                String item = rs.getString(i + 1);
                row.createCell(i).setCellValue(item);
            }
            num = rs.getRow();

            ConfigUtils.needInterrupt(logId, rs.getStatement());
        }

        // 自动宽度
        for (int i = 0; i < columnCount; ++i) {
            sheet.autoSizeColumn(i);

            ConfigUtils.needInterrupt(logId, rs.getStatement());
        }

        // 构造输出流
        OutputStream targetFile = null;
        try {
            targetFile = new BufferedOutputStream(new FileOutputStream(path + fileName + ".xls", true), 8 * 1024);
            wb.write(targetFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Create target file failed.", e);
        } finally {
            if (null != targetFile) {
                try {
                    targetFile.close();
                } catch (IOException e) {
                    // Do nothing
                }
            }
        }
        return new FileResult(fileName + ".xls", num);
    }

}
