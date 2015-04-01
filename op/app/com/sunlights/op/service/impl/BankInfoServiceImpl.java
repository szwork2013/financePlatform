package com.sunlights.op.service.impl;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.BankInfoDao;
import com.sunlights.op.dal.impl.BankInfoDaoImpl;
import com.sunlights.op.service.BankInfoService;
import com.sunlights.op.vo.BankInfoVo;

import java.util.List;

/**
 * Created by Administrator on 2014/11/13.
 */
public class BankInfoServiceImpl implements BankInfoService {
    private BankInfoDao bankInfoDAO = new BankInfoDaoImpl();
    @Override
    public List<BankInfoVo> findBanks(PageVo pageVo) {
        List<BankInfoVo> banks=bankInfoDAO.findBanks(pageVo);
        return banks;
    }

    @Override
    public void update(BankInfoVo bankInfoVo) {
        bankInfoDAO.update(bankInfoVo);
    }

    @Override
    public void save(BankInfoVo bankInfoVo) {
        bankInfoDAO.save(bankInfoVo);
    }


//    @Override
//    public List<ActivityVo> findActicities() {
//        List<Activity> activities = activityDao.findActicities();
//        List<ActivityVo> vos = new ArrayList<ActivityVo>();
//        for(Activity activity : activities) {
//            vos.add(new ActivityVo(activity));
//        }
//        return vos;
//    }
//
//    @Override
//    public Activity add(ActivityVo vo) {
//        return activityDao.add(vo.convert2Model(vo));
//    }
//
//    @Override
//    public Activity modifyActivity(ActivityVo vo) {
//        return activityDao.modifyActivity(vo.convert2Model(vo));
//    }
//
//    @Override
//    public void deleteActivity(Long id) {
//        activityDao.deleteActivity(id);
//    }
//
//    @Override
//    public List<ActivityVo> findActivityWithRule(Long id, String title, String type) {
//
//        return activityDao.findActivityWithRule(id, title, type);
//    }
}
