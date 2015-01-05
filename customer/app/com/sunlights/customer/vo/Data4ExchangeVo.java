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

    public void setLogo(String logo) {
        for(Data4ExchangeItem item : this.list) {
            item.setLogo(logo);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data4ExchangeVo)) return false;

        Data4ExchangeVo that = (Data4ExchangeVo) o;

        if (canPayed != null ? !canPayed.equals(that.canPayed) : that.canPayed != null) return false;
        if (maxPayed != null ? !maxPayed.equals(that.maxPayed) : that.maxPayed != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = canPayed != null ? canPayed.hashCode() : 0;
        result = 31 * result + (maxPayed != null ? maxPayed.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        return result;
    }
}
