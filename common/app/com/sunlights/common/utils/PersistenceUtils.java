package com.sunlights.common.utils;

import com.sunlights.common.utils.PropertyFilter.LikeMatchPatten;
import com.sunlights.common.utils.PropertyFilter.MatchType;
import com.sunlights.common.utils.PropertyFilter.PropertyType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>Project: fsp</p>
 * <p>Title: PersistenceUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class PersistenceUtils {
  private static final Logger logger = LoggerFactory.getLogger(PersistenceUtils.class);

  private PersistenceUtils() {
  }

  public static List<PropertyFilter> buildPropertyFilters(Map<String, Object> filterParamMap) {
    List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
    for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
      String filterName = entry.getKey();
      Object value = entry.getValue();
      // TODO:entry.getValue()可能是数组
      if (value == null || "null".equals(value)) continue;
      if (StringUtils.isNotBlank(value.toString())) {
        // TODO: patch ==>
        String ss = null;
        if (value instanceof java.util.Date) {
          Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
          ss = format.format(value);
        } else {
          ss = value.toString();
        }
        // <== patch
        PropertyFilter filter = new PropertyFilter(filterName, ss);
        filterList.add(filter);
      }
    }
    return filterList;
  }


  /**
   * Build Query String(SELECT COUNT(*) OR SELECT * ) Using class type.
   *
   * @param clazz
   * @param isCount
   * @return
   */
  public static StringBuilder buildQueryString(final Class<?> clazz, final boolean isCount) {
    StringBuilder queryBuilder = new StringBuilder();
    if (isCount) queryBuilder.append(DBHelper.HQL_SELECT_COUNT_FROM);
    else queryBuilder.append(DBHelper.HQL_SELECT_FROM);
    queryBuilder.append(clazz.getSimpleName());
    queryBuilder.append(DBHelper.HQL_ALIAS_OBJECT);
    return queryBuilder;
  }

  /**
   * Build Query String, Using class and values of parameters.
   *
   * @param isCount
   * @param clazz
   * @param values
   * @return
   */
  public static String buildQueryString(final boolean isCount, final Class<?> clazz, final String... values) {
    StringBuilder queryBuilder = buildQueryString(clazz, isCount);
    if (values != null && values.length > 0) {
      queryBuilder.append(DBHelper.HQL_KEYWORD_WHERE);
      for (String value : values) {
        queryBuilder.append(value).append(DBHelper.HQL_KEYWORD_AND_VALUE);
      }
      if (queryBuilder.lastIndexOf(DBHelper.HQL_KEYWORD_AND) == (queryBuilder.length() - 5)) {
        queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
      }
    }
    return queryBuilder.toString();
  }

  /**
   * Build Query String, Using class and values of parameters.
   *
   * @param clazz
   * @param params
   * @return string
   */
  public static String buildQueryStringWithNamedParams(final boolean isCount, final Class<?> clazz,
                                                       final Map<String, ?> params) {
    StringBuilder queryBuilder = buildQueryString(clazz, isCount);
    if (!params.isEmpty()) {
      queryBuilder.append(DBHelper.HQL_KEYWORD_WHERE);
      for (Map.Entry<String, ?> entry : params.entrySet()) {
        queryBuilder.append(DBHelper.HQL_ALIAS_OBJECT.trim() + ".").append(entry.getKey())
            .append(DBHelper.HQL_PLACEHOLDER_EQUALITY_COLON).append(entry.getKey())
            .append(DBHelper.HQL_KEYWORD_AND);
      }
      if (queryBuilder.lastIndexOf(DBHelper.HQL_KEYWORD_AND) == (queryBuilder.length() - 5)) {
        queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
      }
    }
    logger.info(" Build Query String With NamedParams: {}", queryBuilder.toString());
    return queryBuilder.toString();
  }

  /**
   * Build Query String, Using class and propertyFilters.
   *
   * @param clazz
   * @param filters
   * @return
   */
  public static String buildQueryStringWithPropertyFilters(final boolean isCount, final Class<?> clazz,
                                                           final List<PropertyFilter> filters) {
    StringBuilder queryBuilder = buildQueryString(clazz, isCount);
    if (!CollectionUtils.isEmpty(filters)) {
      queryBuilder.append(DBHelper.HQL_KEYWORD_WHERE);
      for (PropertyFilter filter : filters) {
        if (filter.isMultiProperty()) {
          queryBuilder.append(DBHelper.HQL_OPERATOR_BRACKET_BEFORE);
          for (String propertyName : filter.getPropertyNames()) {
            queryBuilder.append(DBHelper.HQL_ALIAS_OBJECT.trim() + ".").append(propertyName);
            buildQueryStringWithPropertyFilter(filter, queryBuilder);
            queryBuilder.append(DBHelper.HQL_KEYWORD_OR);
          }
          if (queryBuilder.lastIndexOf(DBHelper.HQL_KEYWORD_OR) == (queryBuilder.length() - 4)) {
            queryBuilder.delete(queryBuilder.length() - 4, queryBuilder.length());
          }
          queryBuilder.append(DBHelper.HQL_OPERATOR_BRACKET_AFTER);
        } else {
          queryBuilder.append(DBHelper.HQL_ALIAS_OBJECT.trim() + ".").append(filter.getPropertyName());
          buildQueryStringWithPropertyFilter(filter, queryBuilder);
        }
        queryBuilder.append(DBHelper.HQL_KEYWORD_AND);
      }
      if (queryBuilder.lastIndexOf(DBHelper.HQL_KEYWORD_AND) == (queryBuilder.length() - 5)) {
        queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
      }
      logger.info(" Build Query String With PropertyFilter: {}", queryBuilder.toString());
    }
    return queryBuilder.toString();
  }

  /**
   * Build Query String, Using propertyFilter and queryBuilder.
   *
   * @param filter
   * @param queryBuilder
   */
  private static void buildQueryStringWithPropertyFilter(final PropertyFilter filter, StringBuilder queryBuilder) {
    MatchType matchType = filter.getMatchType();
    LikeMatchPatten likeMatchPatten = filter.getLikeMatchPatten();
    Object propertyValue = filter.getPropertyValue();

    if (MatchType.EQ.equals(matchType)) {
      queryBuilder.append(DBHelper.HQL_PLACEHOLDER_EQUALITY);
    } else if (MatchType.LIKE.equals(matchType)) {
      queryBuilder.append(DBHelper.HQL_KEYWORD_LIKE);
      StringBuffer sbPatten = new StringBuffer();
      if (LikeMatchPatten.ALL.equals(likeMatchPatten)) {
        sbPatten.append(DBHelper.HQL_OPERATOR_PERCENT).append(propertyValue).append(DBHelper.HQL_OPERATOR_PERCENT);
      } else if (LikeMatchPatten.P.equals(likeMatchPatten)) {
        sbPatten.append(DBHelper.HQL_OPERATOR_PERCENT).append(propertyValue);
      } else if (LikeMatchPatten.S.equals(likeMatchPatten)) {
        sbPatten.append(propertyValue).append(DBHelper.HQL_OPERATOR_PERCENT);
      }
      propertyValue = sbPatten.toString();
    } else if (MatchType.LE.equals(matchType)) {
      queryBuilder.append(DBHelper.HQL_OPERATOR_LE);
    } else if (MatchType.LT.equals(matchType)) {
      queryBuilder.append(DBHelper.HQL_OPERATOR_LT);
    } else if (MatchType.GE.equals(matchType)) {
      queryBuilder.append(DBHelper.HQL_OPERATOR_GE);
    } else if (MatchType.GT.equals(matchType)) {
      queryBuilder.append(DBHelper.HQL_OPERATOR_GT);
    } else if (MatchType.NE.equals(matchType)) {
      queryBuilder.append(DBHelper.HQL_OPERATOR_NE);
    }

    if (PropertyType.S.getValue().equals(filter.getPropertyType())) {
      queryBuilder.append(DBHelper.HQL_PLACEHOLDER_APOSTROPHE);
      queryBuilder.append(propertyValue);
      queryBuilder.append(DBHelper.HQL_PLACEHOLDER_APOSTROPHE);
    } else {
      queryBuilder.append(propertyValue);
    }
  }

  /**
   * Build predicates, Using target class and propertyFilters.
   *
   * @param targetClass
   * @param criteriaBuilder
   * @param criteriaQuery
   * @param entity
   * @param entityType
   * @param isDistinct
   * @param filters
   * @return
   */
  public static Predicate[] buildPropertyFilterPredicates(final Class<?> targetClass, final CriteriaBuilder criteriaBuilder,
                                                          final CriteriaQuery<?> criteriaQuery, final Root<?> entity, EntityType<?> entityType, final boolean isDistinct,
                                                          final List<PropertyFilter> filters) {
    List<Predicate> predicateList = new ArrayList<Predicate>();
    for (PropertyFilter filter : filters) {
      if (!filter.isMultiProperty()) {
        Predicate predicate = buildPropertyFilterPredicate(targetClass, criteriaBuilder, criteriaQuery, entity,
            entityType, isDistinct, filter.getPropertyName(), filter.getPropertyValue(), filter.getMatchType(),
            filter.getLikeMatchPatten());
        predicateList.add(criteriaBuilder.and(predicate));
      } else {
        List<Predicate> multiPropertiesPredicateList = new ArrayList<Predicate>();
        for (String param : filter.getPropertyNames()) {
          Predicate predicate = buildPropertyFilterPredicate(targetClass, criteriaBuilder, criteriaQuery, entity,
              entityType, isDistinct, param, filter.getPropertyValue(), filter.getMatchType(),
              filter.getLikeMatchPatten());
          multiPropertiesPredicateList.add(predicate);
        }
        predicateList.add(criteriaBuilder.or(multiPropertiesPredicateList
            .toArray(new Predicate[multiPropertiesPredicateList.size()])));
      }
    }
    return predicateList.toArray(new Predicate[predicateList.size()]);
  }

  /**
   * Build predicate, Using target class and propertyFilter's value.
   *
   * @param targetClass
   * @param criteriaBuilder
   * @param criteriaQuery
   * @param entity
   * @param entityType
   * @param isDistinct
   * @param propertyName
   * @param propertyValue
   * @param matchType
   * @param likeMatchPatten
   * @return
   */
  @SuppressWarnings("unchecked")
  public static Predicate buildPropertyFilterPredicate(final Class<?> targetClass, final CriteriaBuilder criteriaBuilder,
                                                       final CriteriaQuery<?> criteriaQuery, final Root entity, EntityType<?> entityType, final boolean isDistinct,
                                                       final String propertyName, final Object propertyValue, final MatchType matchType,
                                                       final LikeMatchPatten likeMatchPatten) {
    Predicate predicate = null;
    Expression expression = (Expression) entity.get(entityType.getSingularAttribute(propertyName));
    try {
      if (MatchType.EQ.equals(matchType)) {
        predicate = criteriaBuilder.equal(expression, propertyValue);
      } else if (MatchType.LIKE.equals(matchType)) {
        StringBuffer sbPatten = new StringBuffer();
        if (LikeMatchPatten.ALL.equals(likeMatchPatten)) {
          sbPatten.append(DBHelper.HQL_OPERATOR_PERCENT).append(propertyValue)
              .append(DBHelper.HQL_OPERATOR_PERCENT);
        } else if (LikeMatchPatten.P.equals(likeMatchPatten)) {
          sbPatten.append(DBHelper.HQL_OPERATOR_PERCENT).append(propertyValue);
        } else if (LikeMatchPatten.S.equals(likeMatchPatten)) {
          sbPatten.append(propertyValue).append(DBHelper.HQL_OPERATOR_PERCENT);
        }
        predicate = criteriaBuilder.like(expression, sbPatten.toString());
      } else if (MatchType.LE.equals(matchType)) {
        predicate = criteriaBuilder.le(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
      } else if (MatchType.LT.equals(matchType)) {
        predicate = criteriaBuilder.lt(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
      } else if (MatchType.GE.equals(matchType)) {
        predicate = criteriaBuilder.ge(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
      } else if (MatchType.GT.equals(matchType)) {
        predicate = criteriaBuilder.gt(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
    return predicate;
  }

}