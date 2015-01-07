package com.sunlights.customer;

import com.sunlights.common.vo.PageVo;
import models.Activity;

import java.util.List;

/**
 * Created by Administrator on 2014/12/19.
 */
public class ActivityPageUtil {

    /**
     * 内存list分页(适用于分页缓存中的数据)
     * @param list
     * @param pageVo
     * @param <T>
     * @return
     */
    public static <T> List<T> page(List<T> list, PageVo pageVo) {
        if(list == null) {
            throw new IllegalArgumentException("list 不能为null");
        }

        int length = list.size();
        int index = pageVo.getIndex();
        int end = pageVo.getIndex() + pageVo.getPageSize();

        pageVo.setCount(length);
        if(length <= pageVo.getPageSize()) {
            return list;
        }
        if(end > length) {
            end = length;
        }
        return list.subList(index, end);
    }
}
