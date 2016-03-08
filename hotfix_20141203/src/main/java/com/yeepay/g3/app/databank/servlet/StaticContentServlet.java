/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.servlet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;

/**
 * <p>Title: 文件高效读取,客户端缓存控制及Gzip压缩传输</p>
 * <p>Description: static-content?contentPath=static/images/logo.jpg
 * static-content?contentPath=static/images/logo.jpg&download=true</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-8-2 下午4:25
 */
public class StaticContentServlet extends HttpServlet {

	private static final long serialVersionUID = -1855617048198368534L;

	private static final Logger LOGGER = LoggerFactory.getLogger(StaticContentServlet.class);

	private String reportPath;

	/**
	 * 需要被Gzip压缩的Mime类型.
	 */
	private static final String[] GZIP_MIME_TYPES = {"text/html", "application/xhtml+xml", "text/plain", "text/css",
			"text/javascript", "application/x-javascript", "application/json"};

	/**
	 * 需要被Gzip压缩的最小文件大小.
	 */
	private static final int GZIP_MINI_LENGTH = 512;

	private MimetypesFileTypeMap mimetypesFileTypeMap;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取得参数
		String contentPath = request.getParameter("contentPath");
		if (StringUtils.isBlank(contentPath)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "contentPath parameter is required.");
			return;
		}

		//获取请求内容的基本信息.
		ContentInfo contentInfo = getContentInfo(contentPath);

		//根据Etag或ModifiedSince Header判断客户端的缓存文件是否有效, 如仍有效则设置返回码为304,直接返回.
		if (!Servlets.checkIfModifiedSince(request, response, contentInfo.lastModified)
				|| !Servlets.checkIfNoneMatchEtag(request, response, contentInfo.etag)) {
			return;
		}

		//设置Etag/过期时间
		Servlets.setExpiresHeader(response, Servlets.ONE_YEAR_SECONDS);
		Servlets.setLastModifiedHeader(response, contentInfo.lastModified);
		Servlets.setEtag(response, contentInfo.etag);

		//设置MIME类型
		response.setContentType(contentInfo.mimeType);

		//设置弹出下载文件请求窗口的Header
		if (request.getParameter("download") != null) {
			Servlets.setFileDownloadHeader(response, contentInfo.fileName);
		}

		//构造OutputStream
		OutputStream output;
		if (checkAccetptGzip(request) && contentInfo.needGzip) {
			//使用压缩传输的outputstream, 使用http1.1 trunked编码不设置content-length.
			output = buildGzipOutputStream(response);
		} else {
			//使用普通outputstream, 设置content-length.
			response.setContentLength(contentInfo.length);
			output = response.getOutputStream();
		}

		//高效读取文件内容并输出,然后关闭input file
		copyFile(contentInfo.file, output);
		output.flush();
		output.close();

		// TODO baitao.ji 为了节省磁盘空间，暂时不再支持下载链接的共享功能。后续有时间再优化
		try {
			contentInfo.file.delete();
            contentInfo.file.deleteOnExit();
		} catch (Exception e) {
			LOGGER.warn("删除文件时报错, fileName:{}", contentInfo.fileName);
		}
	}

	/**
	 * commons-io 2.4
	 *
	 * @param input
	 * @param output
	 * @return
	 * @throws IOException
	 */
	private static long copyFile(File input, OutputStream output) throws IOException {
		final FileInputStream fis = new FileInputStream(input);
		try {
			return IOUtils.copyLarge(fis, output);
		} catch (IOException ioe) {
			LOGGER.warn("复制文件时报错", ioe);
			return 0;
		} finally {
			fis.close();
		}
	}

	/**
	 * 检查浏览器客户端是否支持gzip编码.
	 */
	private static boolean checkAccetptGzip(HttpServletRequest request) {
		//Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");
		return StringUtils.contains(acceptEncoding, "gzip");
	}

	/**
	 * 设置Gzip Header并返回GZIPOutputStream.
	 */
	private OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 初始化.
	 */
	@Override
	public void init() throws ServletException {
		WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		//初始化mimeTypes, 默认缺少css的定义,添加之.
		mimetypesFileTypeMap = new MimetypesFileTypeMap();
		mimetypesFileTypeMap.addMimeTypes("text/css css");

		// 初始化报表存储位置
		Properties props = new Properties();
		InputStream is = null;
		try {
			ResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource("classpath:/application.properties");
			is = resource.getInputStream();
			props.load(is);
		} catch (IOException ignored) {
		} finally {
			IOUtils.closeQuietly(is);
		}
		reportPath = props.getProperty("report.output.dir");
		if ('/' != reportPath.charAt(reportPath.length() - 1)) {
			reportPath = reportPath + "/";
		}
	}

	/**
	 * 创建Content基本信息.
	 */
	private ContentInfo getContentInfo(String contentPath) {
		ContentInfo contentInfo = new ContentInfo();

		String realFilePath;
		if ('/' == contentPath.charAt(0)) {
			realFilePath = reportPath + contentPath;
		} else {
			realFilePath = getServletContext().getRealPath(contentPath);
		}
		File file = new File(realFilePath);
		contentInfo.file = file;
		contentInfo.contentPath = contentPath;
		contentInfo.fileName = file.getName();
		contentInfo.length = (int) file.length();

		contentInfo.lastModified = file.lastModified();
		contentInfo.etag = "W/\"" + contentInfo.lastModified + "\"";

		contentInfo.mimeType = mimetypesFileTypeMap.getContentType(contentInfo.fileName);

		contentInfo.needGzip = contentInfo.length >= GZIP_MINI_LENGTH && ArrayUtils.contains(GZIP_MIME_TYPES, contentInfo.mimeType);

		return contentInfo;
	}

	/**
	 * 定义Content的基本信息.
	 */
	static class ContentInfo {
		protected String contentPath;
		protected File file;
		protected String fileName;
		protected int length;
		protected String mimeType;
		protected long lastModified;
		protected String etag;
		protected boolean needGzip;
	}

}
