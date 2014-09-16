/**
 * 
 */
package com.bx.notice.core.dao.jpa.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bx.notice.core.dao.jpa.JpaDAO;
import com.bx.notice.util.QueryResult;

/**
 * @author lzh
 *
 */
@Transactional
@Repository
public class JpaDaoSupport<T> implements JpaDAO<T> {
	@PersistenceContext
	private EntityManager em;
	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#getCount(java.lang.Class)
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> long getCount(Class<T> entityClass) {
		return (Long)em.createQuery("select count(" + getCountField(entityClass) + ") from " + 
				getEntityName(entityClass) + "o ").getSingleResult();
	}
	
	public <T> long getCount(String hql) {
		return (Long)em.createQuery(hql).getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#clear()
	 */
	public void clear() {
		em.clear();
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#save(java.lang.Object)
	 */
	public void save(Object entity) {
		em.persist(entity);
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#update(java.lang.Object)
	 */
	public void update(Object entity) {
		em.merge(entity);
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#delete(java.lang.Class, java.lang.Object)
	 */
	public <T> void delete(Class<T> entityClass, Object entityId) {
		delete(entityClass,new Object[]{entityId});
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#delete(java.lang.Class, java.lang.Object[])
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityIds) {
		for(Object id : entityIds) {
			em.remove(em.getReference(entityClass, id));
		}
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#find(java.lang.Class, java.lang.Object)
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, Object entityId) {
		return em.find(entityClass, entityId);
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#getScrollData(java.lang.Class, int, int, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityName = getEntityName(entityClass);
		Query query = em.createQuery("select o from " + entityName + " o " + 
				(wherejpql == null ? "" : " where " + wherejpql) + buildOrderby(orderby));
		setQueryParams(query,queryParams);
		if(firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex);
			query.setMaxResults(maxresult);
		}
		qr.setResultList(query.getResultList());
		query = em.createQuery("select count(" + getCountField(entityClass) + ") from " + entityName + " o " + 
				(wherejpql == null ? "" : "where " + wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long)query.getSingleResult());
		return qr;
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#getScrollData(java.lang.Class, int, int, java.lang.String, java.lang.Object[])
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String wherejpql,
			Object[] queryParams) {
		return getScrollData(entityClass,firstindex,maxresult,wherejpql,queryParams,null); 
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#getScrollData(java.lang.Class, int, int, java.util.LinkedHashMap)
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass,firstindex,maxresult,null,null,orderby);
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#getScrollData(java.lang.Class, int, int)
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult) {
		return getScrollData(entityClass,firstindex,maxresult,null,null,null);
	}

	/* (non-Javadoc)
	 * @see com.bx.core.dao.jpa.JpaDAO#getScrollData(java.lang.Class)
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass, -1, -1);
	}
	
	protected void setQueryParams(Query query, Object[] queryParams){  
        if(queryParams!=null && queryParams.length>0){  
            for(int i=0; i<queryParams.length; i++){  
                query.setParameter(i+1, queryParams[i]);  
            }  
        }  
    }  
    /** 
     * 组装order by语句 
     * @param orderby 
     * @return 
     */  
    protected String buildOrderby(LinkedHashMap<String, String> orderby){  
        StringBuffer orderbyql = new StringBuffer("");  
        if(orderby!=null && orderby.size()>0){  
            orderbyql.append(" order by ");  
            for(String key : orderby.keySet()){  
                orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");  
            }  
            orderbyql.deleteCharAt(orderbyql.length()-1);  
        }  
        return orderbyql.toString();  
    }  
    /** 
     * 获取实体的名称 
     * @param <T> 
     * @param entityClass 实体类 
     * @return 
     */  
    protected <T> String getEntityName(Class<T> entityClass){  
        String entityname = entityClass.getSimpleName();  
        Entity entity = entityClass.getAnnotation(Entity.class);  
        if(entity.name()!=null && !"".equals(entity.name())){  
            entityname = entity.name();  
        }  
        return entityname;  
    }  
      
    protected <T> String getCountField(Class<T> clazz){  
        String out = "o";  
        try {  
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();  
            for(PropertyDescriptor propertydesc : propertyDescriptors){  
                Method method = propertydesc.getReadMethod();  
                if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){                     
                    PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();  
                    out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());  
                    break;  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return out;  
    }  
    
    /**
	 * 取得当前类的实际表名(前提是用注解的方式来配置实体)
	 * @return
	 */
	protected String getTableName(Class<T> clazz){
		return clazz.getAnnotation(Table.class).name();
	}

	public List findByNativeSQL(String sql, Object[] params) {
		List result = null;
		if (null != sql && !"".equals(sql.trim())) {
			try {
				Query query = em.createNativeQuery(sql);
				this.setQueryParams(query, params);
				result = query.getResultList();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public QueryResult<T> findByNativeSQLPage(String sql, String cntSql, int firstindex, int maxresult) {
		QueryResult<T> qr = new QueryResult<T>();
		Query query = em.createNativeQuery(sql);
		if(firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex);
			query.setMaxResults(maxresult);
		}
		qr.setResultList(query.getResultList());
		query = em.createNativeQuery(cntSql);
		Object o = query.getSingleResult();
		if(o.getClass().getName().equals("java.math.BigDecimal")) {
			qr.setTotalrecord(Long.valueOf(((java.math.BigDecimal)o).toString()));
		}
		return qr;
	}

	public void saveOrUpdateOrDelBySql(String sql, Object[] params) {
		try {
			Query query = em.createNativeQuery(sql);
			this.setQueryParams(query, params);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public QueryResult<T> findByHQLPage(String hql, String cntHql,
			int firstindex, int maxresult) {
		QueryResult<T> qr = new QueryResult<T>();
		Query query = em.createQuery(hql);
		if(firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex);
			query.setMaxResults(maxresult);
		}
		qr.setResultList(query.getResultList());
		query = em.createQuery(cntHql);
		qr.setTotalrecord((Long)query.getSingleResult());
		return qr;
	}

	public List<T> findByHQL(String hql) {
		Query query = em.createQuery(hql);
		return query.getResultList();
	}

	public List<T> findFirstResult(String hql) {
		Query query = em.createQuery(hql);
		query.setMaxResults(1);
		return query.getResultList();
	}

	public List<T> findByFunc(String fname, List<T> params) {
		return null;
	}
	
}
