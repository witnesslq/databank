/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper.file;

import com.yeepay.g3.app.databank.utils.result.FileResult;

import java.sql.ResultSet;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-26 下午9:48
 */
public interface OutputHelper {

    FileResult doSave(Long logId, ResultSet rs, String path, String fileName) throws Exception;

}
