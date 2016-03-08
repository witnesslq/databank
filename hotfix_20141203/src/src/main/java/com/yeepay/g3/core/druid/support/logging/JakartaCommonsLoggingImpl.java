package com.yeepay.g3.core.druid.support.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JakartaCommonsLoggingImpl implements com.yeepay.g3.core.druid.support.logging.Log {

	private Log LOGGER;

	private int errorCount;
	private int warnCount;
	private int infoCount;
	private int debugCount;

	/**
	 * @param log
	 * @since 0.2.1
	 */
	public JakartaCommonsLoggingImpl(Log log) {
		this.LOGGER = log;
	}

	public JakartaCommonsLoggingImpl(String loggerName) {
		LOGGER = LogFactory.getLog(loggerName);
	}

	public boolean isDebugEnabled() {
		return LOGGER.isDebugEnabled();
	}

	public void error(String s, Throwable e) {
		LOGGER.error(s, e);
		errorCount++;
	}

	public void error(String s) {
		LOGGER.error(s);
		errorCount++;
	}

	public void debug(String s) {
		debugCount++;
		LOGGER.debug(s);
	}

	public void debug(String s, Throwable e) {
		debugCount++;
		LOGGER.debug(s, e);
	}

	public void warn(String s) {
		LOGGER.warn(s);
		warnCount++;
	}

	@Override
	public void warn(String s, Throwable e) {
		LOGGER.warn(s, e);
		warnCount++;
	}

	@Override
	public int getWarnCount() {
		return warnCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	@Override
	public void resetStat() {
		errorCount = 0;
		warnCount = 0;
		infoCount = 0;
		debugCount++;
	}

	@Override
	public boolean isInfoEnabled() {
		return LOGGER.isInfoEnabled();
	}

	@Override
	public void info(String msg) {
		LOGGER.info(msg);
		infoCount++;
	}

	@Override
	public int getInfoCount() {
		return infoCount;
	}

	@Override
	public boolean isWarnEnabled() {
		return LOGGER.isWarnEnabled();
	}

	public int getDebugCount() {
		return debugCount;
	}

}
