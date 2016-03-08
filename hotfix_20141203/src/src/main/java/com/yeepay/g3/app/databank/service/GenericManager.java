/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service;

import java.io.Serializable;

/**
 * <p>Title: Generic Manager (Data Access Object) with common methods to CRUD POJOs</p>
 * <p>Description: Extend this interface if you want typesafe (no casting necessary) Manager's for your
 * domain objects</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-4-1 下午4:19:53
 */
public interface GenericManager<T, PK extends Serializable> {

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if
	 * nothing is found.
	 *
	 * @param id the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(PK id);

	/**
	 * Generic method to save an object - handles insert.  will set modified_time to
	 * current time and will set created_time to current_system_time if it's an insert.
	 *
	 * @param object the object to save
	 * @return the persisted object
	 */
	T create(T object);

	/**
	 * Generic method to update an object - handles update.  will set modified_time to
	 * current time.
	 *
	 * @param object the object to save
	 * @return the persisted object
	 */
	void update(T object);

	/**
	 * Generic method to delete an object based on class and id
	 *
	 * @param id the identifier (primary key) of the object to remove
	 */
	void delete(PK id);

}
