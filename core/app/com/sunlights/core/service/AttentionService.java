package com.sunlights.core.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.AttentionVo;
import com.sunlights.core.vo.ProductVo;

import java.util.List;

/**
 * <p>Project: financePlatform</p>
 * <p>Title: AttentionService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface AttentionService {
    /**
     * 批量关注
     *
     * @param attentionVos
     */
    public void createAttentions(List<AttentionVo> attentionVos);

    /**
     * 取消关注
     *
     * @param attentionVo
     */
    public void cancelAttention(AttentionVo attentionVo);


    /**
     * 查找关注
     *
     * @param pageVo
     * @return
     */
    public <X> List<X> findAttentions(PageVo pageVo);
}
