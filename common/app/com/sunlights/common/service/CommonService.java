package com.sunlights.common.service;

import com.sunlights.common.vo.DictVo;
import models.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.jpa.JPA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: CommonService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class CommonService {
    private static Logger logger = LoggerFactory.getLogger(CommonService.class);

    private static Map<String, List<DictVo>> catDictMap = null;

    private static List<Dict> dicts = null;

    private static Map<String, DictVo> valueMap = null;

    static {
        initDicts();
    }

    public CommonService() {

    }


    private static void initDicts() {
        catDictMap = new HashMap<String, List<DictVo>>();
        dicts = new ArrayList<Dict>();
        valueMap = new HashMap<String, DictVo>();

        String jpql = " select d from Dict d where d.status = 'Y' order by d.codeCat,d.seqNo";
        dicts = JPA.em().createQuery(jpql).getResultList();
        for (int i = 0; i < dicts.size(); i++) {
            Dict dict = dicts.get(i);
            String catPointKey = dict.getCodeCat() + "." + dict.getCodeKey();
            valueMap.put(catPointKey, new DictVo(dict));
            List<DictVo> catDicts = catDictMap.get(dict.getCodeCat());
            if (catDicts == null) {
                catDicts = new ArrayList<DictVo>();
            }
            catDicts.add(new DictVo(dict));
            catDictMap.put(dict.getCodeCat(), catDicts);
        }

    }


    public String findValueByCatPointKey(String catPointKey) {
        DictVo dict = valueMap.get(catPointKey);
        return dict == null ? catPointKey : dict.getCodeVal();
    }


    public List<DictVo> findDictsByCat(String cat) {
        return catDictMap.get(cat);
    }

    public Map<String, String> findDictMapByCat(String cat) {
        HashMap<String, String> dictMap = new HashMap<String, String>();
        List<DictVo> catDicts = catDictMap.get(cat);
        for (DictVo dict : catDicts) {
            dictMap.put(dict.getCodeCat() + "." + dict.getCodeKey(), dict.getCodeVal());
        }
        return dictMap;
    }

    public DictVo findDictByCatPointKey(String catPointKey) {
        return valueMap.get(catPointKey);
    }

}
