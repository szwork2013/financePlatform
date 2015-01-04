package com.sunlights.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project: fsp</p>
 * <p>Title: PageVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class PageVo {
    //当前页第一条数据游标
    private int index = 0;
    //每页显示到数据条数
    private int pageSize = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageVo)) return false;

        PageVo pageVo = (PageVo) o;

        if (count != pageVo.count) return false;
        if (index != pageVo.index) return false;
        if (pageNum != pageVo.pageNum) return false;
        if (pageSize != pageVo.pageSize) return false;
        if (filter != null ? !filter.equals(pageVo.filter) : pageVo.filter != null) return false;
        if (list != null ? !list.equals(pageVo.list) : pageVo.list != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + pageSize;
        result = 31 * result + pageNum;
        result = 31 * result + count;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        result = 31 * result + (filter != null ? filter.hashCode() : 0);
        return result;
    }

    //当前页码
    private int pageNum = 0;
    //总条数
    private int count = 0;
    //分页数据
    private List list = new ArrayList();

    //  @JsonIgnore
    private Map<String, Object> filter = new HashMap<String, Object>();

    public void put(String key, Object value) {
        filter.put(key, value);
    }

    public Object get(String key) {
        return filter.get(key);
    }

    public void clear() {
        filter.clear();
    }

    public int getIndex() {
        return pageNum > 0 ? pageSize * (pageNum - 1) : index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
