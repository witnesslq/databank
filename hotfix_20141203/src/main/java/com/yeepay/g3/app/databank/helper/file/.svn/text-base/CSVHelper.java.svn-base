/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper.file;

import com.yeepay.g3.app.databank.utils.config.ConfigUtils;
import com.yeepay.g3.app.databank.utils.result.FileResult;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.*;
import java.sql.ResultSet;

/**
 * <p>Title: CSV 文件生成 Helper</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-8-6 下午8:12
 */
public class CSVHelper implements OutputHelper {

    /**
     * 保存 CSV 文件
     *
     * @param rs       内容
     * @param fileName 文件目录及文件名
     */
    public FileResult doSave(Long logId, ResultSet rs, String path, String fileName) throws Exception {
        // 构造输出流
        OutputStream targetFile = null;
        int num = 0;
        try {
            //byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            targetFile = new BufferedOutputStream(new FileOutputStream(path + fileName + ".csv", true), 8 * 1024);
            targetFile.write(" ".getBytes("GBK"));

            // 封装表头
            StringBuilder row = new StringBuilder();
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; ++i) {
                row.append(rs.getMetaData().getColumnLabel(i).trim()).append(",");
            }
            row.deleteCharAt(row.length() - 1).append("\r\n");

            // 封装正文
            while (rs.next()) {
                for (int i = 1; i <= columnCount; ++i) {
                    String item = rs.getString(i);
                    item = null == item ? "" : StringEscapeUtils.escapeCsv(StringEscapeUtils.escapeHtml4(item));
                    row.append(item).append(",");
                }
                row.deleteCharAt(row.length() - 1).append("\r\n");
                num = rs.getRow();
                if (num % 200 == 151) {
                    targetFile.write(row.toString().getBytes("GBK"));
                    targetFile.flush();
                    row = new StringBuilder();
                }

                ConfigUtils.needInterrupt(logId, rs.getStatement());
            }

            targetFile.write(row.toString().getBytes("GBK"));
            targetFile.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Create target file failed.", e);
        } catch (IOException e) {
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
        return new FileResult(fileName + ".csv", num);
    }

}
