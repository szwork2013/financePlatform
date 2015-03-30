package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.UserVo;

import java.util.List;

/**
 * Created by Yuan on 2015/3/6.
 */
public interface UserService {

	public List<UserVo> findUsersBy(PageVo pageVo);

	public void save(UserVo userVo);
}
