package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.op.dal.ShortUrlDao;

/**
 * Created by Administrator on 2015/4/2.
 */
public class ShortUrlDaoImpl extends EntityBaseDao implements ShortUrlDao {

    @Override
    public void deleteAll() {
        super.batchExecute("delete from ShortUrl where 1 = 1");
    }
}
