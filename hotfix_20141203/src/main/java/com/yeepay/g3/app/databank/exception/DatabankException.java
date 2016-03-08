/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.exception;

import com.yeepay.g3.common.exception.CoreBizException;

/**
 * <p>Title: 数据银行异常类</p>
 * <p>Description: <br/>
 * 00xx 系统内部异常<br/>
 * 01xx 系统内部异常-用户<br/>
 * 02xx 系统内部异常-数据源<br/>
 * 03xx 系统内部异常-笔记<br/>
 * 04xx 系统内部异常-权限<br/>
 * 05xx 系统内部异常-<br/>
 * 9xxx 系统外部异常<br/>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-17 下午10:34
 */
public class DatabankException extends CoreBizException {

    private static final long serialVersionUID = 1L;

    // 00xx 系统内部异常

    /**
     * 避免暴露内部细节
     */
    public static final DatabankException SYSTEM_INTERNAL_EXCEPTION = new DatabankException("0000");

    // 01xx 系统内部异常-用户

    /**
     * 用户信息不存在
     */
    public static final DatabankException USER_NOT_EXISTS_EXCEPTION = new DatabankException("0100");

    // 02xx 系统内部异常-数据源

    /**
     * 数据源不存在
     */
    public static final DatabankException DATASOURCE_NOT_EXISTS_EXCEPTION = new DatabankException("0200");

    /**
     * 数据源数据被篡改
     */
    public static final DatabankException DATASOURCE_DATA_TAMPERING_EXCEPTION = new DatabankException("0201");

    // 04xx 系统内部异常-权限

    /**
     * 无权访问的数据源
     */
    public static final DatabankException NOT_ALLOW_ACCESS_DS_EXCEPTION = new DatabankException("0400");

    /**
     * 无权访问的 Schema 和表
     */
    public static final DatabankException NOT_ALLOW_ACCESS_TBL_EXCEPTION = new DatabankException("0401");

    /**
     * 慢点慢点，奴家都快忙活不过来了！<br/>{} 秒内最多执行 {} 次查询操作。
     */
    public static final DatabankException FREQUENCY_LIMIT_EXCEPTION = new DatabankException("0410");

    /**
     * 磁盘空间不足
     */
    public static final DatabankException FREE_DISKSPACE_LOW_EXCEPTION = new DatabankException("0411");

    // 9xxx 系统外部异常

    /**
     * 通知子系统异常
     */
    public static final DatabankException NOTIFY_SYSTEM_EXCEPTION = new DatabankException("9001");

    private DatabankException(String defineCode) {
        super(defineCode);
    }

    public DatabankException newInstance(String message, Object... args) {
        DatabankException e = new DatabankException(this.defineCode);

        e.setStackTrace(getCoreStackTrace());
        e.setMessage(message, args);
        return e;
    }

}
