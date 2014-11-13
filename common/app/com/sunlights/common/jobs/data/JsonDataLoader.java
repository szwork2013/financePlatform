package com.sunlights.common.jobs.data;

import play.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan on 2014/9/3.
 */
public class JsonDataLoader implements DataLoader {

    private String url = null;

    public JsonDataLoader(String url) {
        this.url = url;
    }

    @Override
    public List load() {
        Logger.info(" the request url is " + this.url);
        List funds = new ArrayList();
//        List<Fund> funds = new ArrayList<Fund>();
//        StringBuffer json = new StringBuffer();
//        String resource = null;
//        try {
//            URL url = new URL(this.url);
//            URLConnection connection = url.openConnection();
//            connection.setDoOutput(false);
//            connection.setAllowUserInteraction(false);
//            connection.connect();
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
//            while ((resource = br.readLine()) != null) {
//                json.append(resource);
//            }
//            br.close();
//
//            for (JsonNode jsonNode : Json.parse(json.toString())) {
//                Fund fund = Json.fromJson(jsonNode, Fund.class);
//                funds.add(fund);
//            }
//            System.out.println("[funds]" + funds.size());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return funds;
    }
}
