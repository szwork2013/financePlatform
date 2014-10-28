package com.sunlights.common.page;

import java.util.List;

public interface PageDao {
	public <X> List<X> findBy(String queryString, Pager pager);

    public <X> List<X> findNativeBy(String queryString, Pager pager);

    public <X> List<X> findXsqlBy(String xsql, Pager pager);

    public <X> List<X> findNativeXsqlBy(String xsql, Pager pager);
}
