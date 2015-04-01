package com.sunlights.op.service.activity;

import com.sunlights.op.vo.activity.ShareInfoVo;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/9.
 */
public interface ActivityShareInfoService {

    public List<ShareInfoVo> loadAll();

    public ShareInfoVo add(ShareInfoVo shareInfo);

    public ShareInfoVo modify(ShareInfoVo shareInfo);

    public void remove(Long id);

    public List<ShareInfoVo> getByParentId(String parentId);

}
