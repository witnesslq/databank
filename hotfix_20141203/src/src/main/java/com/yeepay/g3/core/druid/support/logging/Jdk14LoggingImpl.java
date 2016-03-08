package com.yeepay.g3.core.druid.support.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Jdk14LoggingImpl implements Log {

	private Logger LOGGER;

	private int errorCount;
	private int warnCount;
	private int infoCount;
	private int debugCount;

	private String loggerName;

	public Jdk14LoggingImpl(String loggerName) {
		this.loggerName = loggerName;
		LOGGER = Logger.getLogger(loggerName);
	}

	public boolean isDebugEnabled() {
		return LOGGER.isLoggable(Level.FINE);
	}

	public void error(String s, Throwable e) {
		LOGGER.logp(Level.SEVERE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s, e);
		errorCount++;
	}

	public void error(String s) {
		LOGGER.logp(Level.SEVERE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s);
		errorCount++;
	}

	public void debug(String s) {
		debugCount++;
		LOGGER.logp(Level.FINE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s);
	}

	public void debug(String s, Throwable e) {
		debugCount++;
		LOGGER.logp(Level.FINE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s, e);
	}

	public void warn(String s) {
		LOGGER.logp(Level.WARNING, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s);
		warnCount++;
	}

	@Override
	public void warn(String s, Throwable e) {
		LOGGER.logp(Level.WARNING, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s, e);
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
		debugCount = 0;
	}

	@Override
	public boolean isInfoEnabled() {
		return LOGGER.isLoggable(Level.INFO);
	}

	@Override
	public void info(String msg) {
		LOGGER.logp(Level.INFO, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), msg);
		infoCount++;
	}

	@Override
	public int getInfoCount() {
		return infoCount;
	}

	@Override
	public boolean isWarnEnabled() {
		return LOGGER.isLoggable(Level.WARNING);
	}

	public int getDebugCount() {
		return debugCount;
	}

}
