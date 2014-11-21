package com.sunlights.common.jobs.data;


import play.Logger;

import java.util.List;

/**
 * Created by Yuan on 2014/9/3.
 */
public class JsonDataWriter extends DataWriter {

    @Override
    public void write() {
        List data = getDataLoader().load();
//        if (data != null && data.size() > 0) {
//            Ebean.createUpdate(Fund.class, "delete from fund").execute();
//        }
//        for (Fund fund : data) {
//            fund.createdAt = new Timestamp(new Date().getTime());
//            fund.save();
//        }

        Logger.info("save json data size is " + data.size());
    }

}
