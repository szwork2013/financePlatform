package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ShortUrlDao;
import models.ActivityScene;
import models.ShortUrl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/16.
 */
public class ShortUrlDaoImpl extends EntityBaseDao implements ShortUrlDao {

    @Override
    public void doSave(ShortUrl shortUrl) {
        shortUrl.setCreateTime(new Date());
        super.create(shortUrl);
    }

    @Override
    public ShortUrl getShortUrl(String type, String refId) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from ShortUrl h where 1 =1")
                .append("/~  and h.shareType  = {shareType} ~/")
                .append("/~  and h.refId  = {refId} ~/");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_shareType", type);
        params.put("EQS_refId", refId);

        List<ShortUrl> list = findByMap(jpql.toString(), params);

        if(list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
