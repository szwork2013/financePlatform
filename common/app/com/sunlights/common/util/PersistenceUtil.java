package com.sunlights.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;


/**
 * <p>Project: fsp</p>
 * <p>Title: PersistenceUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class PersistenceUtil {
    private static final Logger logger = LoggerFactory.getLogger(PersistenceUtil.class);

    private PersistenceUtil() {
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
        if (isCount) queryBuilder.append(Constants.HQL_SELECT_COUNT_FROM);
        else queryBuilder.append(Constants.HQL_SELECT_FROM);
        queryBuilder.append(clazz.getSimpleName());
        queryBuilder.append(Constants.HQL_ALIAS_OBJECT);
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
            queryBuilder.append(Constants.HQL_KEYWORD_WHERE);
            for (String value : values) {
                queryBuilder.append(value).append(Constants.HQL_KEYWORD_AND_VALUE);
            }
            if (queryBuilder.lastIndexOf(Constants.HQL_KEYWORD_AND) == (queryBuilder.length() - 5)) {
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
        if (!CollectionUtils.isEmpty(params)) {
            queryBuilder.append(Constants.HQL_KEYWORD_WHERE);
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                queryBuilder.append(Constants.HQL_ALIAS_OBJECT.trim() + ".").append(entry.getKey())
                        .append(Constants.HQL_PLACEHOLDER_EQUALITY_COLON).append(entry.getKey())
                        .append(Constants.HQL_KEYWORD_AND);
            }
            if (queryBuilder.lastIndexOf(Constants.HQL_KEYWORD_AND) == (queryBuilder.length() - 5)) {
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
            queryBuilder.append(Constants.HQL_KEYWORD_WHERE);
            for (PropertyFilter filter : filters) {
                if (filter.isMultiProperty()) {
                    queryBuilder.append(Constants.HQL_OPERATOR_BRACKET_BEFORE);
                    for (String propertyName : filter.getPropertyNames()) {
                        queryBuilder.append(Constants.HQL_ALIAS_OBJECT.trim() + ".").append(propertyName);
                        buildQueryStringWithPropertyFilter(filter, queryBuilder);
                        queryBuilder.append(Constants.HQL_KEYWORD_OR);
                    }
                    if (queryBuilder.lastIndexOf(Constants.HQL_KEYWORD_OR) == (queryBuilder.length() - 4)) {
                        queryBuilder.delete(queryBuilder.length() - 4, queryBuilder.length());
                    }
                    queryBuilder.append(Constants.HQL_OPERATOR_BRACKET_AFTER);
                } else {
                    queryBuilder.append(Constants.HQL_ALIAS_OBJECT.trim() + ".").append(filter.getPropertyName());
                    buildQueryStringWithPropertyFilter(filter, queryBuilder);
                }
                queryBuilder.append(Constants.HQL_KEYWORD_AND);
            }
            if (queryBuilder.lastIndexOf(Constants.HQL_KEYWORD_AND) == (queryBuilder.length() - 5)) {
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
        PropertyFilter.MatchType matchType = filter.getMatchType();
        PropertyFilter.LikeMatchPatten likeMatchPatten = filter.getLikeMatchPatten();
        Object propertyValue = filter.getPropertyValue();

        if (PropertyFilter.MatchType.EQ.equals(matchType)) {
            queryBuilder.append(Constants.HQL_PLACEHOLDER_EQUALITY);
        } else if (PropertyFilter.MatchType.LIKE.equals(matchType)) {
            queryBuilder.append(Constants.HQL_KEYWORD_LIKE);
            StringBuffer sbPatten = new StringBuffer();
            if (PropertyFilter.LikeMatchPatten.ALL.equals(likeMatchPatten)) {
                sbPatten.append(Constants.HQL_OPERATOR_PERCENT).append(propertyValue).append(Constants.HQL_OPERATOR_PERCENT);
            } else if (PropertyFilter.LikeMatchPatten.P.equals(likeMatchPatten)) {
                sbPatten.append(Constants.HQL_OPERATOR_PERCENT).append(propertyValue);
            } else if (PropertyFilter.LikeMatchPatten.S.equals(likeMatchPatten)) {
                sbPatten.append(propertyValue).append(Constants.HQL_OPERATOR_PERCENT);
            }
            propertyValue = sbPatten.toString();
        } else if (PropertyFilter.MatchType.LE.equals(matchType)) {
            queryBuilder.append(Constants.HQL_OPERATOR_LE);
        } else if (PropertyFilter.MatchType.LT.equals(matchType)) {
            queryBuilder.append(Constants.HQL_OPERATOR_LT);
        } else if (PropertyFilter.MatchType.GE.equals(matchType)) {
            queryBuilder.append(Constants.HQL_OPERATOR_GE);
        } else if (PropertyFilter.MatchType.GT.equals(matchType)) {
            queryBuilder.append(Constants.HQL_OPERATOR_GT);
        } else if (PropertyFilter.MatchType.NE.equals(matchType)) {
            queryBuilder.append(Constants.HQL_OPERATOR_NE);
        }

        if (PropertyFilter.PropertyType.S.getValue().equals(filter.getPropertyType())) {
            queryBuilder.append(Constants.HQL_PLACEHOLDER_APOSTROPHE);
            queryBuilder.append(propertyValue);
            queryBuilder.append(Constants.HQL_PLACEHOLDER_APOSTROPHE);
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
                                                         final String propertyName, final Object propertyValue, final PropertyFilter.MatchType matchType,
                                                         final PropertyFilter.LikeMatchPatten likeMatchPatten) {
        Predicate predicate = null;
        Expression expression = (Expression) entity.get(entityType.getSingularAttribute(propertyName));
        try {
            if (PropertyFilter.MatchType.EQ.equals(matchType)) {
                predicate = criteriaBuilder.equal(expression, propertyValue);
            } else if (PropertyFilter.MatchType.LIKE.equals(matchType)) {
                StringBuffer sbPatten = new StringBuffer();
                if (PropertyFilter.LikeMatchPatten.ALL.equals(likeMatchPatten)) {
                    sbPatten.append(Constants.HQL_OPERATOR_PERCENT).append(propertyValue)
                            .append(Constants.HQL_OPERATOR_PERCENT);
                } else if (PropertyFilter.LikeMatchPatten.P.equals(likeMatchPatten)) {
                    sbPatten.append(Constants.HQL_OPERATOR_PERCENT).append(propertyValue);
                } else if (PropertyFilter.LikeMatchPatten.S.equals(likeMatchPatten)) {
                    sbPatten.append(propertyValue).append(Constants.HQL_OPERATOR_PERCENT);
                }
                predicate = criteriaBuilder.like(expression, sbPatten.toString());
            } else if (PropertyFilter.MatchType.LE.equals(matchType)) {
                predicate = criteriaBuilder.le(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
            } else if (PropertyFilter.MatchType.LT.equals(matchType)) {
                predicate = criteriaBuilder.lt(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
            } else if (PropertyFilter.MatchType.GE.equals(matchType)) {
                predicate = criteriaBuilder.ge(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
            } else if (PropertyFilter.MatchType.GT.equals(matchType)) {
                predicate = criteriaBuilder.gt(expression, NumberUtils.createNumber(String.valueOf(propertyValue)));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return predicate;
    }

}