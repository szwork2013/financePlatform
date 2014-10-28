package com.sunlights.common.page;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Project: fsp</p>
 * <p>Title: PageServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;


    @Override
    public <X> List<X> findBy(String queryString, Pager pager) {
        return pageDao.findBy(queryString, pager);
    }

    @Override
    public <X> List<X> findNativeBy(String queryString, Pager pager) {
        return pageDao.findNativeBy(queryString, pager);
    }

    @Override
    public <X> List<X> findXsqlBy(String xsql, Pager pager) {
        return pageDao.findXsqlBy(xsql, pager);
    }

    @Override
    public <X> List<X> findNativeXsqlBy(String xsql, Pager pager) {
        return pageDao.findNativeXsqlBy(xsql, pager);
    }
}
