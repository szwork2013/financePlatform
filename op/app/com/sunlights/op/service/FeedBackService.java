package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.FeedBackVo;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FeedBackService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface FeedBackService {
    public List<FeedBackVo> findFeedBacksBy(PageVo pageVo);

    public void createFeedBack(FeedBackVo feedBackVo);

    public void updateFeedBack(FeedBackVo feedBackVo);

    public void deleteFeedBack(FeedBackVo feedBackVo);
}
