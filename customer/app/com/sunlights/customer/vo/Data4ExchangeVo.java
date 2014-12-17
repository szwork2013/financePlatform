package com.sunlights.customer.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/12/17.
 */
public class Data4ExchangeVo implements Serializable {
    private String canPayed;

    private String maxPayed;

    private String accountDate;

    private String summary;

    private List<Data4ExchangeItem> list = new ArrayList<Data4ExchangeItem>();


    public String getCanPayed() {
        return canPayed;
    }

    public void setCanPayed(String canPayed) {
        this.canPayed = canPayed;
    }

    public String getMaxPayed() {
        return maxPayed;
    }

    public void setMaxPayed(String maxPayed) {
        this.maxPayed = maxPayed;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Data4ExchangeItem> getList() {
        return list;
    }

    public void setList(List<Data4ExchangeItem> list) {
        this.list = list;
    }

    public void addRecord(Data4ExchangeItem item) {
        this.list.add(item);
    }
}
