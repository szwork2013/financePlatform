package com.sunlights.op.vo;

/**
 * Created by guxuelong on 2014/12/4.
 */
public class ReconcileVo extends BaseVo{
    private int errorCount;
    private int totalCount;

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
