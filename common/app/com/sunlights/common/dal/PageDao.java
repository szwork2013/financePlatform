package com.sunlights.common.dal;

import com.sunlights.common.vo.PageVo;

import java.util.List;

public interface PageDao {
    public <X> List<X> findBy(String queryString, PageVo pager);

    public <X> List<X> findNativeBy(String queryString, PageVo pager);

    public <X> List<X> findXsqlBy(String xsql, PageVo pager);

    public <X> List<X> findNativeXsqlBy(String xsql, PageVo pager);

    /**
     * 复杂sql分页查询接口
     * @param xsql  查询sql
     * @param countSql  计数sql
     * @param pageVo 分页对象
     * @param <X>
     * @return
     */
    public <X> List<X> findNativeComplexBy(String xsql, String countSql, PageVo pageVo);
}
