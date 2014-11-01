package com.sunlights.common.service;


import com.sunlights.common.dal.PageDao;
import com.sunlights.common.vo.PageVo;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: PageServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */

public class PageService {


    private PageDao pageDao;


    public <X> List<X> findBy(String queryString, PageVo pager) {
        return pageDao.findBy(queryString, pager);
    }

    public <X> List<X> findNativeBy(String queryString, PageVo pager) {
        return pageDao.findNativeBy(queryString, pager);
    }

    public <X> List<X> findXsqlBy(String xsql, PageVo pager) {
        return pageDao.findXsqlBy(xsql, pager);
    }

    public <X> List<X> findNativeXsqlBy(String xsql, PageVo pager) {
        return pageDao.findNativeXsqlBy(xsql, pager);
    }
}
