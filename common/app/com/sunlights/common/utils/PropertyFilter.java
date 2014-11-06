/**
 * Copyright (c) 2005-20010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * $Id: PropertyFilter.java 1205 2010-09-09 15:12:17Z calvinxiu $
 */
package com.sunlights.common.utils;


import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Date;


/**
 * 与具体ORM实现无关的属性过滤条件封装类, 主要记录页面中简单的搜索过滤条件.
 *
 * @author calvin
 */
public final class PropertyFilter {
  public static final String OR_SEPARATOR = "_OR_";
  public static final String PARAM_PREFIX = "_";

  /**
   * GT: Greater Than , >
   * GE: Greater than or Equivalent with , >=
   * LT: Less than, <
   * LE: Less than or Equivalent with, <=
   * EQ: EQuivalent with, ==
   * NE: Not Equivalent with, !=
   */
  public enum MatchType {
    EQ, LIKE, LT, GT, LE, GE, NE;
  }

  public enum LikeMatchPatten {
    P, S, ALL;
  }

  public enum PropertyType {
    S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class);


    private Class<?> clazz;


    PropertyType(Class<?> clazz) {
      this.clazz = clazz;
    }


    public Class<?> getValue() {
      return clazz;
    }
  }


  private String[] propertyNames = null;
  private Class<?> propertyType = null;
  private Object propertyValue = null;
  private MatchType matchType = null;

  private LikeMatchPatten likeMatchPatten = null;

  /**
   * @param filterName
   * @param value
   */
  public PropertyFilter(final String filterName, final String value) {

    String matchTypeStr;
    String matchPattenCode = LikeMatchPatten.ALL.toString();
    String matchTypeCode;
    String propertyTypeCode;

    if (filterName.contains("LIKE") && filterName.charAt(0) != 'L') {
      matchTypeStr = StringUtils.substringBefore(filterName, PARAM_PREFIX);
      matchPattenCode = StringUtils.substring(matchTypeStr, 0, 1);
      matchTypeCode = StringUtils.substring(matchTypeStr, 1, matchTypeStr.length() - 1);
      propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
    } else {
      matchTypeStr = StringUtils.substringBefore(filterName, PARAM_PREFIX);
      matchTypeCode = StringUtils.substring(matchTypeStr, 0, matchTypeStr.length() - 1);
      propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
    }

    try {
      matchType = Enum.valueOf(MatchType.class, matchTypeCode);
      likeMatchPatten = Enum.valueOf(LikeMatchPatten.class, matchPattenCode);
    } catch (RuntimeException e) {
      throw new IllegalArgumentException("filter name: " + filterName
          + "Not prepared in accordance with rules, not get more types of property.", e);
    }


    try {
      propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
    } catch (RuntimeException e) {
      throw new IllegalArgumentException("filter name: " + filterName
          + "Not prepared in accordance with the rules, attribute value types can not be.", e);
    }


    String propertyNameStr = StringUtils.substringAfter(filterName, PARAM_PREFIX);
    propertyNames = StringUtils.split(propertyNameStr, PropertyFilter.OR_SEPARATOR);

    Validate.isTrue(propertyNames.length > 0, "filter name: " + filterName
        + "Not prepared in accordance with the rules, property names can not be.");

    this.propertyValue = ConvertUtils.convert(value, propertyType);
  }

  public boolean isMultiProperty() {
    return (propertyNames.length > 1);
  }


  public String[] getPropertyNames() {
    return propertyNames;
  }


  public String getPropertyName() {
    if (propertyNames.length > 1) {
      throw new IllegalArgumentException("There are not only one property");
    }
    return propertyNames[0];
  }


  public Object getPropertyValue() {
    return propertyValue;
  }


  public Class<?> getPropertyType() {
    return propertyType;
  }


  public MatchType getMatchType() {
    return matchType;
  }


  public LikeMatchPatten getLikeMatchPatten() {
    return likeMatchPatten;
  }
}
