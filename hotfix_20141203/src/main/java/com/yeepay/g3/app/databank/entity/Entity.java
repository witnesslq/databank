/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.entity;

import java.io.Serializable;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 * <p/>
 * http://bugs.sun.com/view_bug.do?bug_id=6788525
 *
 * @author baitao.ji
 * @version 0.1, 13-5-24 下午9:29
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = -7277949963127751206L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
