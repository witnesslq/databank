/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-24 下午9:21
 */
public interface GenericDao<E> {

    /**
     * 保存一个实体
     *
     * @param entity 实体对象
     */
    void add(E entity);

    /**
     * 根据ID保存一个实体
     *
     * @param sql
     * @param entity
     */
    void add(String sql, E entity);

    /**
     * 更新一个实体
     *
     * @param entity 实体对象
     */
    void update(E entity);

    /**
     * 删除一个实体
     *
     * @param id 主键
     */
    void delete(Long id);

    /**
     * 删除一个实体
     */
    void delete(String ql, Object... args);

    /**
     * 删除一个实体
     *
     * @param entity 实体对象
     */
    void delete(E entity);

    /**
     * 查询所有
     *
     * @return
     */
    List<E> getAll();

    /**
     * 通过主键查询
     *
     * @param id 主键
     * @return 实体对象
     */
    E get(Long id);

    /**
     * 通过查询语句查询
     *
     * @param ql  查询语句
     * @param arg 查询条件参数
     * @return 查询结果
     */
    List<E> query(String ql, Object... arg);

    /**
     * 通过查询语句查询
     *
     * @param sql    查询语句
     * @param offset 查询起始行,注意：最小有效值为0
     * @param limit  查询返回的总行数
     * @param arg    查询条件参数
     * @return
     */
    List<E> query(String sql, int offset, int limit, Object... arg);

    /**
     * 根据唯一的条件的E
     *
     * @param ql  查询语句
     * @param arg 查询条件参数
     * @return e 查询结果
     */
    Object queryOne(String ql, Object... arg);

    /**
     * 通过更新语句更新
     *
     * @param ql  更新语句
     * @param arg 更新条件参数
     */
    void update(String ql, Object... arg);

    /**
     * 通过查询语句查询,以Map形式返回,key:字段名
     *
     * @param ql  查询语句
     * @param arg 查询条件参数
     * @return 查询结果Map
     */
    Map<String, Object> getMap(String ql, Object... arg);

    /**
     * 批量更新实体
     *
     * @param entities 列表
     */
    void batchUpdate(List<E> entities);

    /**
     * 批量插入实体
     */
    void batchInsert(String sql, List<E> entities);

    @Deprecated
    void batchInsert(List<E> entities);

    /**
     * 批量删除实体
     *
     * @param entities 实体列表
     */
    void batchDelete(List<E> entities);

    /**
     * 批量删除实体
     *
     * @param ids 实体的Id列表
     */
    void batchDeleteById(List<Long> ids);

}