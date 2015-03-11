package com.sunlights.common.dal;

import com.sunlights.common.vo.PageVo;

import java.util.List;

public interface PageDao {
    public <X> List<X> findBy(String queryString, PageVo pager);

    public <X> List<X> findNativeBy(String queryString, PageVo pager);

    public <X> List<X> findXsqlBy(String xsql, PageVo pager);

    public <X> List<X> findNativeXsqlBy(String xsql, PageVo pager);
}
