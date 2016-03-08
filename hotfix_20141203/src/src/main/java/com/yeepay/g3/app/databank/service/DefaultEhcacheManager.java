/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-25 下午3:58
 */
@Component
public class DefaultEhcacheManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEhcacheManager.class);

	private static final String CACHE_NAME = "myCache";

	private CacheManager ehcacheManager;

	private Cache cache;

	public Object get(String key) {
		LOGGER.debug("从缓存加载数据：{}", key);
		Element element = cache.get(key);
		if (null != element) {
			return element.getObjectValue();
		} else {
			return null;
		}
	}

	public void put(String key, Object value) {
		LOGGER.debug("往缓存保存数据：{}", key);
		Element element = new Element(key, value);
		cache.put(element);
	}

	@Autowired
	public void setEhcacheManager(CacheManager ehcacheManager) {
		this.ehcacheManager = ehcacheManager;
		cache = ehcacheManager.getCache(CACHE_NAME);
	}

}
