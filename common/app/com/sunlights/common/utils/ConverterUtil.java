package com.sunlights.common.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sunlights.common.exceptions.ConverterException;
import org.apache.commons.beanutils.BeanUtils;
import play.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/11/13.
 */
public final class ConverterUtil {

    private ConverterUtil() {
    }

    /**
     * 把VO对象转换为对应的实体对象，要求VO对象和实体对象有相同的属性
     *
     * @param entity
     * @param vo
     * @param <E>
     * @param <V>
     * @return
     * @throws ConverterException
     */
    public static <E, V> E toEntity(E entity, V vo) throws ConverterException {
        try {
            BeanUtils.copyProperties(entity, vo);
        } catch (Exception e) {
            throw new ConverterException("转换" + vo.getClass().getName() + "失败", e);
        }
        return entity;
    }

    /**
     * 把实体对象转换为对应的VO对象，要求VO对象和实体对象有相同的属性
     */
    public static <E, V> V fromEntity(V vo, E entity) throws ConverterException {
        try {
            BeanUtils.copyProperties(vo, entity);
        } catch (Exception e) {
            throw new ConverterException("转换" + entity.getClass().getName() + "失败", e);
        }
        return vo;
    }

    /**
     * 把Map对象转换为对应的对象，map的key和实体的属性需要对应
     * @param mapObj
     * @param object
     * @param <O>
     * @return
     * @see #createMap(java.util.List, java.util.List)
     */
    public static <O> O convertMap2Object(Map<String, Object> mapObj, O object) throws ConverterException {
        for (String key : mapObj.keySet()) {
            try {
                BeanUtils.setProperty(object, key, mapObj.get(key));
            } catch (Exception e) {
                throw new ConverterException(object.getClass().getName()+" 没有对应的属性：" + key, e);
            }
        }
        return object;
    }

    /**
     * 把两个list对象转换为一个map对象
     * @param keys
     * @param values
     * @return
     */
    public static Map<String, Object> createMap(List<String> keys, List<Object> values){
        if(keys == null || values == null || keys.size()!=values.size()){
            throw new IllegalArgumentException("keys和values不能为空，且他们的size要相等");
        }

        Map<String, Object> result = Maps.newHashMap();
        for (int i = 0; i < keys.size(); i++) {
            result.put(keys.get(i), values.get(i));
        }

        return result;
    }

    public static<T> List<T> convert(String keys, List<Object[]> resultRows, Class<T> clazz) {
        List<T> list = Lists.newArrayList();
        for (Object[] row : resultRows) {
            List<Object> objects = Arrays.asList(row);
            Map<String, Object> objectMap = ConverterUtil.createMap(Splitter.on(",").trimResults().splitToList(keys), objects);
            try {
                T vo = clazz.newInstance();
                ConverterUtil.convertMap2Object(objectMap, vo);
                list.add(vo);
            } catch (Exception e) {
                Logger.error(e.toString(), e);
            }

        }
        return list;
    }

}
