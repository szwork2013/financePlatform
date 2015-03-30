package com.sunlights.op.service.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.FeedBackService;
import com.sunlights.op.vo.FeedBackVo;
import models.FeedBack;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FeedBackServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FeedBackServiceImpl implements FeedBackService {
	private EntityBaseDao entityBaseDao = new EntityBaseDao();

    private PageService pageService = new PageService();

    @Override
    public List<FeedBackVo> findFeedBacksBy(PageVo pageVo) {

        StringBuilder xsql = new StringBuilder();

        xsql.append(" select new com.sunlights.op.vo.FeedBackVo(f) from FeedBack f");
        xsql.append(" where 1=1");
        xsql.append(" /~ and f.customerId = {customerId} ~/ ");
        xsql.append(" /~ and f.context like {context} ~/ ");
        xsql.append(" /~ and f.mobile = {remark} ~/ ");
        xsql.append(" /~ and f.status = {status} ~/ ");
        xsql.append(" /~ and f.deviceNo = {deviceNo} ~/ ");
        xsql.append(" /~ and f.createTime >= {createTime} ~/ ");
        xsql.append(" /~ and f.updateBy like {updateBy} ~/ ");
        xsql.append(" order by f.createTime desc ");

        List<FeedBackVo> feedBackVos = pageService.findXsqlBy(xsql.toString(), pageVo);

        return feedBackVos;
    }

    @Override
    public void createFeedBack(FeedBackVo feedBackVo) {

        FeedBack feedBack = feedBackVo.convertToFeedback();
        feedBack.setCreateTime(new Date());
        feedBack.setUpdateTime(new Date());
		entityBaseDao.create(feedBack);

    }

    @Override
    public void updateFeedBack(FeedBackVo feedBackVo) {
        FeedBack feedBack = feedBackVo.convertToFeedback();
        feedBack.setStatus("Y");
        feedBack.setUpdateTime(new Date());
		entityBaseDao.update(feedBack);
    }

    @Override
    public void deleteFeedBack(FeedBackVo feedBackVo) {
        FeedBack feedBack = entityBaseDao.find(FeedBack.class, feedBackVo.getId());
		entityBaseDao.delete(feedBack);
    }
}
