package com.sunlights.common.jobs.data;

/**
 * Created by Yuan on 2014/9/3.
 */
public class DataService {

    //TODO: 这里不能hardcode URL
    public static void saveFunds() {
        JsonDataLoader dateLoader = new JsonDataLoader("http://127.0.0.1:9001/assets/stylesheets/fund.json");
        DataWriter dataWriter = new JsonDataWriter();
        dataWriter.setDataLoader(dateLoader);
        dataWriter.write();
    }
}
