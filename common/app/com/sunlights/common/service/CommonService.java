package com.sunlights.common.service;

import com.google.common.collect.Maps;
import com.sunlights.common.vo.DictVo;
import models.Dict;
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

    private static Map<String, List<DictVo>> catogeryMap = Maps.newHashMap();
    private static Map<String, DictVo> valueMap = Maps.newHashMap();

    static {
        initDicts();
    }

    public CommonService() {

    }


    private static void initDicts() {
        String jpql = "select d from Dict d where d.status = 'Y' order by d.codeCat,d.seqNo";

        List<Dict> dicts = JPA.em().createQuery(jpql).getResultList();
        for (int i = 0; i < dicts.size(); i++) {
            Dict dict = dicts.get(i);
            String catPointKey = dict.getCodeCat() + "." + dict.getCodeKey();
            valueMap.put(catPointKey, new DictVo(dict));
            List<DictVo> catDicts = catogeryMap.get(dict.getCodeCat());
            if (catDicts == null) {
                catDicts = new ArrayList<>();
            }
            catDicts.add(new DictVo(dict));
            catogeryMap.put(dict.getCodeCat(), catDicts);
        }

    }


    public String findValueByCatPointKey(String catPointKey) {
        DictVo dict = valueMap.get(catPointKey);
        return dict == null ? catPointKey : dict.getCodeVal();
    }


    public List<DictVo> findDictsByCat(String cat) {
        return catogeryMap.get(cat);
    }

    public Map<String, String> findDictMapByCat(String cat) {
        HashMap<String, String> dictMap = new HashMap<>();
        List<DictVo> catDicts = catogeryMap.get(cat);
        for (DictVo dict : catDicts) {
            dictMap.put(dict.getCodeCat() + "." + dict.getCodeKey(), dict.getCodeVal());
        }
        return dictMap;
    }

    public DictVo findDictByCatPointKey(String catPointKey) {
        return valueMap.get(catPointKey);
    }

}
