package com.yeepay.g3.app.databank.schedule;

import com.google.common.collect.Lists;
import com.yeepay.g3.app.databank.helper.EmailHelper;
import com.yeepay.g3.utils.common.DateUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: 按用户统计数据的定时</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-27 下午19:51
 */
@Component
public class DiskCleanScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiskCleanScanner.class);

    private static String reportPath;

    //	@Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(cron = "0 38 3 * * ?")
    public void executeDaily() {
//        Date yesterday = DateUtils.getDayStart(DateUtils.addDay(new Date(), 1));
        Date yesterday = DateUtils.getDayStart(new Date());
        execute(yesterday);
    }

    public void execute(Date date) {
        LOGGER.info("定时清理磁盘空间, basePath:{}", reportPath);
        try {
            File tempDir = new File(reportPath);
            if (tempDir.isDirectory() && tempDir.canWrite()) {
                // Set suffix
                List<String> suffixs = Lists.newArrayList();
                suffixs.add(".csv");
                suffixs.add(".xls");
                final IOFileFilter suffixFileFilter = new SuffixFileFilter(suffixs);

                // Set cutoff date
                final IOFileFilter ageFileFilter = new AgeFileFilter(date.getTime(), true);

                // Set Regex
                final IOFileFilter regexFileFilter = new RegexFileFilter("\\d+-.*\\.(csv|xls)");

                FilenameFilter filenameFilter = new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return suffixFileFilter.accept(dir, name)
                                && ageFileFilter.accept(dir, name)
                                && regexFileFilter.accept(dir, name);
                    }
                };

                for (File file : tempDir.listFiles(filenameFilter)) {
                    if (file.canWrite()) {
                        try {
                            LOGGER.info("删除文件, fileName:{}", file.getName());
                            file.delete();
                            file.deleteOnExit();
                        } catch (Exception e) {
                            LOGGER.warn("删除文件时报错, fileName:{}", file.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warn("清理磁盘报错", e);
            EmailHelper.reportError(null, e, " 清理磁盘失败");
        }
    }

    @Value("#{propertiesReader['report.output.dir']}")
    public void setReportPath(String reportPath) {
        if ('/' == reportPath.charAt(reportPath.length() - 1)) {
            this.reportPath = reportPath;
        } else {
            this.reportPath = reportPath + "/";
        }
    }

}
