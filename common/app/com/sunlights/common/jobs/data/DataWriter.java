package com.sunlights.common.jobs.data;

import play.Logger;

import java.util.List;

/**
 * Created by Yuan on 2014/9/3.
 */
public class DataWriter {

    private DataLoader dataLoader;
    public void write() {
        List data = dataLoader.load();
        Logger.info("write the data size is " + data.size());
    }


    public void setDataLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public DataLoader getDataLoader() {
         return this.dataLoader;
    }
}
