package com.sunlights.common.utils;

import com.sunlights.common.exceptions.ConverterException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2014/11/13.
 */
public final class ConverterUtil {

    private ConverterUtil(){}

    public static <E,V> E toEntity(E entity, V vo) throws ConverterException {
        try {
            BeanUtils.copyProperties(entity, vo);
        } catch (Exception e) {
            throw new ConverterException("转换"+vo.getClass().getName()+"失败",e);
        }
        return entity;
    }

    public static <E,V> V fromEntity(V vo, E entity) throws ConverterException {
        try {
            BeanUtils.copyProperties(vo, entity);
        } catch (Exception e) {
            throw new ConverterException("转换"+entity.getClass().getName()+"失败", e);
        }
        return vo;
    }


}
