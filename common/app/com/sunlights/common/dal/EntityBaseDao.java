package com.sunlights.common.dal;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sunlights.common.utils.PersistenceUtils;
import com.sunlights.common.utils.PropertyFilter;
import javacommon.xsqlbuilder.XsqlBuilder;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Project: fsp</p>
 * <p>Title: EntityService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class EntityBaseDao {

  protected EntityManager em = JPA.em();

  public EntityBaseDao() {
  }

  // ----------------------------- 基本 CRUD 操作方法 ------------------------------//

  /**
   * 保存新增的对象.
   */
  public <T> T create(final T entity) {
    Validate.notNull(entity, "entity不能为空");
    em.persist(entity);
    play.Logger.debug("create-create entity: {}", entity);
    return entity;
  }

  public <T> T detach(final T entity) {
    Validate.notNull(entity, "entity不能为空");
    em.detach(entity);
    Logger.debug("create-create entity: {}", entity);
    return entity;
  }

  public boolean isManaged(Object entity) {
    return em.contains(entity);
  }

  /**
   * 保存修改的对象.
   */
  public <T> T update(final T entity) {
    Validate.notNull(entity, "entity不能为空");
    em.merge(entity);
    em.flush();
    Logger.debug("update entity: {}", entity);
    return entity;
  }

  /**
   * 删除对象.
   *
   * @param entity 对象必须是session中的对象或含id属性的transient对象.
   */
  public <T> void delete(final T entity) {
    Validate.notNull(entity, "entity不能为空");
    em.remove(entity);
    em.flush();
    Logger.debug("delete entity: {}", entity);
  }

  /**
   * 按id删除对象.
   */

  public void delete(Class<?> entityClass, final Serializable id) {
    Validate.notNull(id, "id不能为空");
    delete(find(entityClass, id));
    Logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
  }

  /**
   * 按id获取对象.
   */

  public <T> T find(Class<T> entityClass, final Serializable id) {
    Validate.notNull(id, "id不能为空");
    return (T) em.find(entityClass, id);
  }

  /**
   * 获取全部对象
   *
   * @param entityClass 实体类的类型
   * @param <T>
   * @return
   */
  public <T> List<T> findAll(Class<T> entityClass) {
    return this.findAll(entityClass, null, null);
  }

  /**
   * 获取全部对象, 支持按属性行序.
   *
   * @param entityClass
   * @param orderByProperty
   * @param isAsc
   * @param <T>
   * @return
   */
  public <T> List<T> findAll(Class<T> entityClass, String orderByProperty, Boolean isAsc) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
    Root<T> entityRoot = criteriaQuery.from(entityClass);
    criteriaQuery.select(entityRoot);

    if (orderByProperty != null) {
      if (isAsc) {
        criteriaQuery.orderBy(builder.asc(entityRoot.get(orderByProperty)));
      } else {
        criteriaQuery.orderBy(builder.desc(entityRoot.get(orderByProperty)));
      }
    }

    return em.createQuery(criteriaQuery).getResultList();
  }

  // --------------------------------- 条件查询方法 -----------------------------------//

  /**
   * 按属性查找对象列表, 匹配方式为相等.
   */
  public <T> List<T> findBy(Class<T> entityClass, final String propertyName, final Object value) {
    Validate.notEmpty(propertyName, "propertyName不能为空");

    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);

    Root<T> entityRoot = criteriaQuery.from(entityClass);
    criteriaQuery.select(entityRoot);
    criteriaQuery.where(builder.equal(entityRoot.get(propertyName), value));

    return em.createQuery(criteriaQuery).getResultList();
  }

  /**
   * 按属性查找唯一对象, 匹配方式为相等.
   */
  public <T> T findUniqueBy(Class<T> entityClass, final String propertyName, final Object value) {
    Validate.notEmpty(propertyName, "propertyName不能为空");
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);

    Root<T> entityRoot = criteriaQuery.from(entityClass);
    criteriaQuery.select(entityRoot);
    criteriaQuery.where(builder.equal(entityRoot.get(propertyName), value));
    return em.createQuery(criteriaQuery).getSingleResult();
  }

  /**
   * 按属性查找对象列表,支持多种匹配方式.
   *
   * @param entityClass
   * @param propertyName 属性名
   * @param value        属性值
   * @param matchType    匹配方式，如：EQ, LIKE, LT, GT, LE, GE, NE，取值见PropertyFilter的MatcheType enum;
   * @return
   */
  public <T> List<T> findBy(Class<T> entityClass, final String propertyName, final Object value, final PropertyFilter.MatchType matchType) {
    Validate.notEmpty(propertyName, "propertyName不能为空");
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);

    Root<T> entity = criteriaQuery.from(entityClass);
    EntityType<T> entityType = entity.getModel();

    criteriaQuery.select(entity);

    Predicate predicate = PersistenceUtils.buildPropertyFilterPredicate(entityClass, criteriaBuilder, criteriaQuery, entity, entityType, true,
        propertyName, value, matchType, PropertyFilter.LikeMatchPatten.ALL);

    // criteriaBuilder.and(predicate);

    criteriaQuery.where(predicate);

    return em.createQuery(criteriaQuery).getResultList();
  }

  /**
   * 按JPQL查询对象列表.
   *
   * @param values 数量可变的参数,按顺序绑定.
   */

  public <X> List<X> find(final String jpql, final Object... values) {
    return createQuery(jpql, values).getResultList();
  }

  /**
   * 按JPQL查询唯一对象.
   *
   * @param values 数量可变的参数,按顺序绑定.
   */

  public <X> X findUnique(final String jpql, final Object... values) {
    return (X) createQuery(jpql, values).getSingleResult();
  }

  /**
   * 按JPQL查询对象列表.
   *
   * @param values 命名参数,按名称绑定.
   */

  public <X> List<X> find(final String jpql, final Map<String, ?> values) {
    return createQuery(jpql, values).getResultList();
  }

  /**
   * 按JPQL查询唯一对象.
   *
   * @param values 命名参数,按名称绑定.
   */
  public <X> X findUnique(final String jpql, final Map<String, ?> values) {
    return (X) createQuery(jpql, values).getSingleResult();
  }

  // ---------------------------- execute 及其他方法 ------------------------------ //

  /**
   * 执行JPQL进行批量修改/删除操作.
   *
   * @param values 数量可变的参数,按顺序绑定.
   * @return 更新记录数.
   */

  public int batchExecute(final String jpql, final Object... values) {
    return createQuery(jpql, values).executeUpdate();
  }

  /**
   * 执行JPQL进行批量修改/删除操作.
   *
   * @param values 命名参数,按名称绑定.
   * @return 更新记录数.
   */

  public int batchExecute(final String jpql, final Map<String, ?> values) {
    return createQuery(jpql, values).executeUpdate();
  }

  /**
   * Flush当前Session.
   */
  public void flush() {
    em.flush();
  }

  public void refresh(Object entity) {
    em.refresh(entity);
  }

  public EntityManager getEm() {
    return em;
  }

  // ----------------------------------- List 查询 --------------------------------------//

  /**
   * Find all results by CriteriaQuery and PropertyFilter.
   *
   * @param targetClass
   * @param filters
   * @return
   */
  public <T> List<T> find(final Class<T> targetClass, final List<PropertyFilter> filters) {
    return this.find(targetClass, filters, false);
  }

  /**
   * Find distinct or all results by CriteriaQuery and PropertyFilter.
   *
   * @param targetClass
   * @param filters
   * @param isDistinct
   * @return
   */
  public <T> List<T> find(final Class<T> targetClass, final List<PropertyFilter> filters, final boolean isDistinct) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(targetClass);

    Root<T> entity = criteriaQuery.from(targetClass);
    EntityType<T> entityType = entity.getModel();

    criteriaQuery.select(entity);
    criteriaQuery.distinct(isDistinct);

    Predicate predicates[] = PersistenceUtils.buildPropertyFilterPredicates(targetClass, criteriaBuilder, criteriaQuery, entity, entityType,
        isDistinct, filters);

    if (!ArrayUtils.isEmpty(predicates)) {
      criteriaQuery.where(predicates);
    } else {
      criteriaQuery.where(criteriaBuilder.conjunction());
    }

    TypedQuery<T> finalCriteriaQuery = em.createQuery(criteriaQuery);
    return (List<T>) finalCriteriaQuery.getResultList();
  }

  /**
   * <p>
   * 按 XSQL 查询，参数以 Map 形式提供. xsql的写法参考：http://code.google.com/p/rapid-xsqlbuilder/，例如： String xsql = "from Book where 1=1" + " /~ and name = {name}~/ " ; 位于/~ ~/之间的语句为可选部分，{name}表示变量值，当 map 中没有
   * name值或name为空（null或“”）时，/~ ~/之间的语句被忽略。 filterMap 为jsf页面与Bean的传值容器，命名方法示例：EQL_id, LIKES_name等
   * <p/>
   * 用法例子：
   * <p/>
   * 页面代码： 书名：<h:inputText value="#{bookBean.map['EQS_name']}"/> 作者：<h:inputText value="#{bookBean.map['LIKES_author']}"/> 价格：<h:inputText value="#{bookBean.map['EQN_price']}"/> 页数：<h:inputText
   * value="#{bookBean.map['EQI_pageNum']}"/>
   * <p/>
   * Bean类代码： StringBuilder sql = new StringBuilder("select name from Book where 1=1"); sql.append(" /~ and name = {name}~/ ") .append(" /~ and author like {author}~/ ")
   * .append(" /~ and price = {price}~/") .append(" /~ and pageNum = {pageNum}~/");
   * <p/>
   * private Map<String,Object> map = Maps.newHashMapWithExpectedSize(5); //set, get...
   * <p/>
   * List list = entityService.findByMap(sql.toString(), map);
   * <p/>
   * </p>
   *
   * @param xsql      基于 xsqlbuilder 样式的类SQL语句.
   * @param filterMap 从页面上以Map形式传过来的属性集合.
   * @return 分页的查询结果.
   */

  public <X> List<X> findByMap(final String xsql, final Map<String, Object> filterMap) {
    Validate.notEmpty(xsql, "xsql不能为空");
    Query q = createQueryByMap(xsql, filterMap);

    return q.getResultList();
  }

  // ------------------------------ 计算记录条数 count ----------------------------//

  /**
   * 执行count查询获得本次Hql查询所能获得的对象总数.
   * <p/>
   * 本函数只能自动处理简单的jpql语句,复杂的jpql查询请另行编写count语句查询.
   */

  public int countHqlResult(final String jpql, final Object... values) {
    String countHql = prepareCountHql(jpql);

    try {
      int count = Integer.valueOf(String.valueOf(findUnique(countHql, values)));
      return count;
    } catch (Exception e) {
      throw new RuntimeException("jpql can't be auto count, jpql is:" + countHql, e);
    }
  }

  /**
   * 执行count查询获得本次Hql查询所能获得的对象总数.
   * <p/>
   * 本函数只能自动处理简单的jpql语句,复杂的jpql查询请另行编写count语句查询.
   */

  public int countHqlResult(final String jpql, final Map<String, ?> values) {
    String countHql = prepareCountHql(jpql);

    try {
      int count = Integer.valueOf(String.valueOf(findUnique(countHql, values)));
      return count;
    } catch (Exception e) {
      throw new RuntimeException("jpql can't be auto count, jpql is:" + countHql, e);
    }
  }

  public int countSqlResult(final String sql, final Map<String, ?> values) {
    String countHql = prepareCountHql(sql);

    try {
      Query nativeQuery = createNativeQuery(countHql, values);
      int count = Integer.valueOf(String.valueOf(nativeQuery.getSingleResult()));
      return count;
    } catch (Exception e) {
      throw new RuntimeException("jpql can't be auto count, jpql is:" + countHql, e);
    }
  }

  protected <T> int countByPropertyFilter(final Class<T> targetClass, final List<PropertyFilter> filters) {
    String countOfQuery = PersistenceUtils.buildQueryStringWithPropertyFilters(true, targetClass, filters);
    Query query = em.createQuery(countOfQuery);
    return Integer.valueOf(String.valueOf(query.getSingleResult()));
  }

  /**
   * select子句与order by子句会影响count查询,进行简单的排除.
   *
   * @param jpql JPQL 查询语句
   * @return 与 jpql对应的 count 语句
   */
  public String prepareCountHql(String jpql) {
    boolean distinct = false;
    if (jpql.toUpperCase().contains("DISTINCT")) {
      distinct = true;
    }
    String fromQl = jpql.substring(jpql.toUpperCase().indexOf("FROM"));

    int pos = fromQl.toUpperCase().indexOf("ORDER BY");
    if (pos != -1) {
      fromQl = fromQl.substring(0, pos);
    }
    String[] fromQls = fromQl.split("\\s+");// 多个空格
    String fromQl1 = "";
    if ("AS".equals(fromQls[2].toUpperCase())) {
      fromQl1 = fromQls[3];
    } else {
      fromQl1 = fromQls[2];
    }
    StringBuilder countOfQuery = new StringBuilder("SELECT COUNT(" + (distinct ? "DISTINCT " + fromQl1 + ".id" : fromQl1 + ".id") + ") ");

    countOfQuery.append(fromQl);
    return countOfQuery.toString();
  }


  // ------------------------------ createQuery 方法集合 -------------------------//

  /**
   * 设置分页参数到Query对象,辅助函数.
   */

  public <T> Query setPageParameterToQuery(final Query q, final int first, final int pageSize) {
    Validate.isTrue(pageSize > 0, "PageVo Size must larger than zero");

    // hibernate的firstResult的序号从0开始 primeface lazymodel first从0开始
    q.setFirstResult(first);
    q.setMaxResults(pageSize);
    return q;
  }

  /**
   * 根据查询JPQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
   *
   * @param values 数量可变的参数,按顺序绑定.
   */
  public Query createQuery(final String queryString, final Object... values) {
    Validate.notEmpty(queryString, "queryString不能为空");
    Query query = em.createQuery(queryString);
    if (values != null) {
      for (int i = 1; i <= values.length; i++) {
        Object value = values[i - 1];
        if (value != null) {
          query.setParameter(i, value);
        }
      }
    }
    return query;
  }

  /**
   * <p>
   * Description:原生SQL查询
   * </p>
   *
   * @param queryString
   * @param values
   * @return
   */
  public Query createLocalQuery(final String queryString, final Object... values) {
    Validate.notEmpty(queryString, "queryString不能为空");
    Query query = em.createNativeQuery(queryString);
    if (values != null) {
      for (int i = 1; i <= values.length; i++) {
        Object value = values[i - 1];
        if (value != null) {
          query.setParameter(i, value);
        }
      }
    }
    return query;
  }

  /**
   * <p>
   * Description:原生SQL查询
   * </p>
   *
   * @param queryString
   * @param values
   * @return
   */
  public <X> List<X> createNativeQuery(final String queryString, final Object... values) {
    Validate.notEmpty(queryString, "queryString不能为空");
    Query query = em.createNativeQuery(queryString);
    if (values != null) {
      for (int i = 1; i <= values.length; i++) {
        query.setParameter(i, values[i - 1]);
      }
    }
    return query.getResultList();
  }

  public Query createNativeQuery(final String queryString, final Map<String, ?> values) {
    Validate.notEmpty(queryString, "queryString不能为空");
    Query query = em.createNativeQuery(queryString);
    for (Map.Entry<String, ?> kv : values.entrySet()) {
      query.setParameter(Integer.valueOf(kv.getKey()), kv.getValue());
    }
    return query;
  }

  /**
   * 根据查询JPQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
   *
   * @param queryString jpql 查询语句
   * @param values      命名参数,按名称绑定.
   * @return 返回 javax.persistence.Query 对象.
   */
  public Query createQuery(final String queryString, final Map<String, ?> values) {
    Validate.notEmpty(queryString, "queryString不能为空");
    Query query = em.createQuery(queryString);
    for (Map.Entry<String, ?> kv : values.entrySet()) {
      query.setParameter(kv.getKey(), kv.getValue());
    }
    return query;
  }

  /**
   * 根据查询XSQL与参数结合创建Query对象. 与 findModelByMap() 函数可进行更加灵活的操作.
   *
   * @param xsql      基于 xsqlbuilder 样式的类SQL语句.
   * @param filterMap 参数集合，从页面上以Map形式传过来的属性集合.
   * @return 返回 javax.persistence.Query 对象
   */
  public Query createQueryByMap(String xsql, Map<String, Object> filterMap) {
    Map<String, Object> paramMap = Maps.newHashMapWithExpectedSize(5);
    String jpql = this.buildJpqlAndParams(xsql, filterMap, paramMap);

    return createQuery(jpql, paramMap);
  }

  /**
   * 用法参考
   *
   * @param xsql      基于 xsqlbuilder 样式的类SQL语句.
   * @param filterMap 参数集合，从页面上以Map形式传过来的属性集合.
   * @param paramMap  回调的参数列表，Map的key剔除了前缀
   * @return JPQL的查询语句
   */
  public String buildJpqlAndParams(String xsql, Map<String, Object> filterMap, Map<String, Object> paramMap) {
    // 得到需要动态构建的字段
    List<String> avialableKeys = getAvaliableKeys(xsql);
    // 剔除不需要的过滤属性 和 空值的属性
    Map<String, Object> tmpMap = Maps.newHashMap();
    for (Map.Entry<String, Object> kv : filterMap.entrySet()) {
      if (kv.getValue() == null || "".equals(kv.getValue())) {
        continue;
      }
      if (hasKey(avialableKeys, kv)) {
        tmpMap.put(kv.getKey(), kv.getValue());
      }
    }
    // Assert.isTrue(avialableKeys.size()== filterMap.size());
    this.convertMap(tmpMap, paramMap);

    // 构建 JPQL 语句
    XsqlBuilder builder = new XsqlBuilder();
    String jpql = builder.generateHql(xsql, paramMap).getXsql().toString();

    return jpql;
  }

  private boolean hasKey(List<String> avialableKeys, Map.Entry<String, Object> kv) {
    boolean hasIt = false;
    for (String s : avialableKeys) {
      if (kv.getKey().contains(s)) {
        hasIt = true;
        break;
      }
    }
    return hasIt;
  }

  public Query createNativeQueryByMap(String xsql, Map<String, Object> filterMap) {
    Map<String, Object> paramMap = Maps.newHashMapWithExpectedSize(5);
    String sql = this.buildSqlAndParams(xsql, filterMap, paramMap);

    return createNativeQuery(sql, paramMap);
  }

  private String buildSqlAndParams(String xsql, Map<String, Object> filterMap, Map<String, Object> paramMap) {
    // 得到需要动态构建的字段
    List<String> avialableKeys = getAvaliableKeys(xsql);
    int index = 1;
    // 剔除不需要的过滤属性 和 空值的属性
    Map<String, Object> tmpMap = Maps.newHashMap();
    Map<String, Object> avialableFilterMap = Maps.newHashMap();
    for (int i = 0; i < avialableKeys.size(); i++) {
      String key = avialableKeys.get(i);
      for (Map.Entry<String, Object> kv : filterMap.entrySet()) {
        if (kv.getValue() == null || "".equals(kv.getValue())) {
          continue;
        }
        if (kv.getKey().contains(key)) {
          tmpMap.put(kv.getKey().replace(key, String.valueOf(index)), kv.getValue());
          avialableFilterMap.put(key, kv.getValue());
          index++;
          break;
        }
      }
    }

    this.convertMap(tmpMap, paramMap);

    // 构建 SQL 语句
    XsqlBuilder builder = new XsqlBuilder();
    String sql = builder.generateSql(xsql, avialableFilterMap).getXsql().toString();

    return sql;
  }

  private List<String> getAvaliableKeys(String xsql) {
    List<String> avialableKeys = Lists.newArrayList();

    Pattern p = Pattern.compile("\\{(.+?)\\}");
    Matcher m = p.matcher(xsql);

    while (m.find()) {
      avialableKeys.add(m.group(1));
    }
    return avialableKeys;
  }

  private Map<String, Object> convertMap(Map<String, Object> filterMap, Map<String, Object> paramMap) {
    for (Map.Entry<String, Object> kv : filterMap.entrySet()) {
      // 获取属性的 Name (key)
      String filterName = kv.getKey();
      String propertyName = StringUtils.substringAfter(filterName, "_");

      // 分离属性的 Type
      String matchTypeStr = StringUtils.substringBefore(filterName, "_");
      String propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());

      // 如果带有 LIKE，则一定为字符串，此时，需要加上 % （目前只处理了全 Like）
      if (filterName.contains("LIKE")) {
        if (!("S".equals(propertyTypeCode.toUpperCase()))) {
          throw new IllegalArgumentException("filter name: " + filterName + "'LIKE' needs 'S'.");
        }
        paramMap.put(propertyName, "%" + kv.getValue() + "%"); // 构建 paramMap
        continue;
      }

      // 获得属性的 Type 的 Class 类型
      Class<?> propertyType = null;
      try {
        propertyType = Enum.valueOf(PropertyFilter.PropertyType.class, propertyTypeCode).getValue();
      } catch (RuntimeException e) {
        throw new IllegalArgumentException("filter name: " + filterName
            + "Not prepared in accordance with the rules, attribute value types can not be.", e);
      }

      // 比较属性value的类型是否给定类型相同，如果相同则不转换，不相同，则需要将value转换为propertyType指定的类型
      if (kv.getValue().getClass().equals(propertyType)) {
        paramMap.put(propertyName, kv.getValue());
      } else {
        Object propertyValue = ConvertUtils.convert(kv.getValue().toString(), propertyType);
        paramMap.put(propertyName, propertyValue);
      }
    }

    return paramMap;
  }
}

