/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper.file;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.exception.DatabankException;
import com.yeepay.g3.app.databank.schedule.DiskCleanScanner;
import com.yeepay.g3.app.databank.utils.config.ConfigEnum;
import com.yeepay.g3.app.databank.utils.config.ConfigUtils;
import com.yeepay.g3.app.databank.utils.result.FileResult;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-26 下午9:48
 */
public class OutputHelperContext implements OutputHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputHelperContext.class);

    private static String OS = System.getProperty("os.name").toLowerCase();

    private Map<String, OutputHelper> helperMap = Maps.newHashMapWithExpectedSize(4);

    private DiskCleanScanner diskCleanScanner;

    public FileResult save(Long logId, ResultSet rs, String path, String fileName, String type) throws Exception {
        if ((Long) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_FREESPACE_LIMIT) > getFreeSpaceOnLinux("/")) {
            diskCleanScanner.execute(DateUtils.addMinute(new Date(), -30));
            throw DatabankException.FREE_DISKSPACE_LOW_EXCEPTION
                    .newInstance("剩余可用磁盘空间不足, 请稍后再试");
        }

        return helperMap.get(type).doSave(logId, rs, path, fileName);
    }


    @Override
    public FileResult doSave(Long logId, ResultSet rs, String path, String fileName) throws Exception {
        return null;
    }

    private long getFreeSpaceOnLinux(String path) {
        try {
            Process p = Runtime.getRuntime().exec("df " + path);
            InputStream reader = new BufferedInputStream(p.getInputStream());
            StringBuilder buffer = new StringBuilder();
            for (; ; ) {
                int c = reader.read();
                if (c == -1)
                    break;
                buffer.append((char) c);
            }
            String outputText = buffer.toString();
            reader.close();

            // parse the output text for the bytes free info
            StringTokenizer tokenizer = new StringTokenizer(outputText, "\n");
            tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) {
                String line2 = tokenizer.nextToken();
                StringTokenizer tokenizer2 = new StringTokenizer(line2, " ");
                if (tokenizer2.countTokens() >= 4) {
                    tokenizer2.nextToken();
                    tokenizer2.nextToken();
                    tokenizer2.nextToken();

                    long bytesFree = Long.parseLong(tokenizer2.nextToken());
                    if (isMacOS()) {
                        bytesFree /= 2;
                    }
                    return bytesFree / 1000 / 1000;
                }
            }
        } catch (Exception e) {
            LOGGER.warn("无法获取磁盘剩余可用空间", e);
        }
        return 30;
    }

    public boolean isMacOS() {
        return OS.contains("mac") && OS.indexOf("os") > 0;
    }

    public void setHelperMap(Map<String, OutputHelper> helperMap) {
        this.helperMap = helperMap;
    }

    public void setDiskCleanScanner(DiskCleanScanner diskCleanScanner) {
        this.diskCleanScanner = diskCleanScanner;
    }

}
