/**
 * 
 */
package com.bx.notice.core.dao.jpa;

import java.util.LinkedHashMap;
import java.util.List;

import com.bx.notice.util.QueryResult;

/**
 * @author lzh
 *
 */
public interface JpaDAO<T> {
	/** 
     * 获取记录总数 
     * @param entityClass 实体类 
     * @return 
     */  
    public <T> long getCount(Class<T> entityClass);  
    
    public <T> long getCount(String hql);
    /** 
     * 清除一级缓存的数据 
     */  
    public void clear();  
    /** 
     * 保存实体 
     * @param entity 实体对象 
     */  
    public void save(Object entity);  
    /** 
     * 更新实体 
     * @param entity 实体对象
     */  
    public void update(Object entity);  
    /** 
     * 删除实体 
     * @param entityClass 实体类 
     * @param entityid 实体id 
     */  
    public <T> void delete(Class<T> entityClass, Object entityId);  
    /** 
     * 删除实体 
     * @param entityClass 实体类 
     * @param entityids 实体id数组 
     */  
    public <T> void delete(Class<T> entityClass, Object[] entityIds);  
    /** 
     * 获取实体 
     * @param <T> 
     * @param entityClass 实体类 
     * @param entityId 实体id 
     * @return 
     */  
    public <T> T find(Class<T> entityClass, Object entityId);  
    
    /**
     * 通过sql语句查询
     * @param sql
     * @param params
     * @return
     */
    public List findByNativeSQL(String sql, Object[] params);
    
    public List<T> findByHQL(String hql);
    
    public QueryResult<T> findByNativeSQLPage(String sql, String cntSql, int firstindex, int maxresult);
    
    public QueryResult<T> findByHQLPage(String hql, String cntHql, int firstindex, int maxresult);
    
    /**
     * 通过sql语句进行插入或者更新或者删除
     * @param sql
     * @param params
     */
    public void saveOrUpdateOrDelBySql(String sql, Object[] params);
    
    
    public List<T> findFirstResult(String hql);
    
    /** 
     * 获取分页数据 
     * @param <T> 
     * @param entityClass 实体类 
     * @param firstindex 开始索引 
     * @param maxresult 需要获取的记录数 
     * @return 
     */  
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex, int maxresult  
            , String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);  
      
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex, int maxresult  
            , String wherejpql, Object[] queryParams);  
      
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex, int maxresult  
            , LinkedHashMap<String, String> orderby);  
      
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstindex, int maxresult);  
      
    public <T> QueryResult<T> getScrollData(Class<T> entityClass);
    
    public List<T> findByFunc(String fname, List<T> params);
}
