package com.sunlights.customer.vo;

/**
 * Created by tangweiqun on 2014/12/18.
 */
public class ExchangeResultVo {
    private String payed;

    private String accountDate;

    public String getPayed() {
        return payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeResultVo)) return false;

        ExchangeResultVo that = (ExchangeResultVo) o;

        if (payed != null ? !payed.equals(that.payed) : that.payed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return payed != null ? payed.hashCode() : 0;
    }
}
