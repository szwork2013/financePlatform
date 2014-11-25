package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.vo.PageVo;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("pageDao")
public class PageDaoImpl extends EntityBaseDao implements PageDao {

  @Override
  public <X> List<X> findBy(String queryString, PageVo pager) {
    Query query = createQuery(queryString, pager.getFilter());
    int count = countHqlResult(queryString, pager.getFilter());
    int first = pager.getIndex();
    int pageSize = pager.getPageSize();
    if (first > 0) {
      query.setFirstResult(first);
    }
    if (pageSize > 0) {
      query.setMaxResults(pageSize);
    }
    List list = query.getResultList();
    pager.setCount(count);
    return list;
  }

  @Override
  public <X> List<X> findNativeBy(String queryString, PageVo pager) {
    Query nativeQuery = createNativeQuery(queryString, pager.getFilter());
    int count = countSqlResult(queryString, pager.getFilter());
    int first = pager.getIndex();
    int pageSize = pager.getPageSize();
    if (first > 0) {
      nativeQuery.setFirstResult(first);
    }
    if (pageSize > 0) {
      nativeQuery.setMaxResults(pageSize);
    }
    List list = nativeQuery.getResultList();
    pager.setCount(count);
    return list;
  }

  @Override
  public <X> List<X> findXsqlBy(String xsql, PageVo pager) {
    String countHql = prepareCountHql(xsql);
    Query countQuery = createQueryByMap(countHql, pager.getFilter());
    Query query = createQueryByMap(xsql, pager.getFilter());
    int count = Integer.valueOf(String.valueOf(countQuery.getResultList().get(0)));
    int first = pager.getIndex();
    int pageSize = pager.getPageSize();
    if (first > 0) {
      query.setFirstResult(first);
    }
    if (pageSize > 0) {
      query.setMaxResults(pageSize);
    }
    List list = query.getResultList();
    pager.setCount(count);
    return list;
  }

  @Override
  public <X> List<X> findNativeXsqlBy(String xsql, PageVo pager) {
    String countHql = prepareCountHql(xsql);
    Query countNativeQuery = createNativeQueryByMap(countHql, pager.getFilter());
    Query nativeQuery = createNativeQueryByMap(xsql, pager.getFilter());
    int count = Integer.valueOf(String.valueOf(countNativeQuery.getResultList().get(0)));
    int first = pager.getIndex();
    int pageSize = pager.getPageSize();
    if (first > 0) {
      nativeQuery.setFirstResult(first);
    }
    if (pageSize > 0) {
      nativeQuery.setMaxResults(pageSize);
    }
    List list = nativeQuery.getResultList();
    pager.setCount(count);
    return list;
  }

}
