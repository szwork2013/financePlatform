package com.sunlights.customer.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public class ExchangeSceneListVo implements Serializable {

    private List<ExchangeSceneVo> list = new ArrayList<ExchangeSceneVo>();

    public List<ExchangeSceneVo> getList() {
        return list;
    }

    public void setList(List<ExchangeSceneVo> list) {
        this.list = list;
    }

    public void addRecord(ExchangeSceneVo exchangeSceneVo) {
        this.list.add(exchangeSceneVo);
    }
}
