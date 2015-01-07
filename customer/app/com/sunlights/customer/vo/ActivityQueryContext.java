package com.sunlights.customer.vo;

import com.sunlights.common.vo.PageVo;

/**
 * Created by Administrator on 2014/12/17.
 */
public class ActivityQueryContext {
    private PageVo pageVo;

    private String custNo;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public PageVo getPageVo() {
        return pageVo;
    }

    public void setPageVo(PageVo pageVo) {
        this.pageVo = pageVo;
    }
}
