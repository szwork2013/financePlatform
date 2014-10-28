package com.sunlights.common.page;


import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: PageService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface PageService {
    public <X> List<X> findBy(String queryString, Pager pager);

    public <X> List<X> findNativeBy(String queryString, Pager pager);

    public <X> List<X> findXsqlBy(String xsql, Pager pager);

    public <X> List<X> findNativeXsqlBy(String xsql, Pager pager);

}
