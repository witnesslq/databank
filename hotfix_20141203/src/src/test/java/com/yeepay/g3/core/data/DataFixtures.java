package com.yeepay.g3.core.data;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

import javax.sql.DataSource;

public class DataFixtures {

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public static void executeScript(DataSource dataSource, String... sqlResourcePaths) throws DataAccessException {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(dataSource);

		for (String sqlResourcePath : sqlResourcePaths) {
			SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate, resourceLoader, sqlResourcePath, true);
		}
	}
}
