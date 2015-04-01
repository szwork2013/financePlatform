package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.BankInfoDao;
import com.sunlights.op.vo.BankInfoVo;
import models.Bank;
import play.Logger;

import java.util.List;

/**
 * Created by yanghong on 2014/11/27.
 */
public class BankInfoDaoImpl extends EntityBaseDao implements BankInfoDao {
    private PageService pageService = new PageService();
    @Override
    public List<BankInfoVo> findBanks(PageVo pageVo) {
//        Bank bank = new Bank();
//        String jpql = " select b from Bank b";
        StringBuilder sb = new StringBuilder();
//        String keys = "id,bankCode,bankName,enName,status,createdTime,updateTime ";
//        String columns = "c.ID,c.BANK_CODE,c.BANK_NAME,c.EN_NAME,c.STATUS,c.CREATE_TIME,c.UPDATE_TIME ";
//        sb.append("select ").append(columns)
//                .append("from c_bank c");
       sb.append(" select new com.sunlights.op.vo.BankInfoVo(c) from Bank c");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and c.id = {id}~/ ");
        sb.append("  /~and c.bankName like {bankName}~/ ");
        sb.append(" /~and c.bankCode = {bankCode} ~/ ");
        sb.append(" order by c.id ");
//        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);
//        filterMap.put("EQL_id", id);
//        filterMap.put("LIKES_bankName", title);
//        filterMap.put("EQS_bankCode", type);
//        pageVo.put("LIKES_bankName",title);
//        pageVo.put("EQS_id", id);
//        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
//        List<BankInfoVo> banklist = ConverterUtil.convert(keys, resultRows, BankInfoVo.class);
        List<BankInfoVo> banklist = pageService.findXsqlBy(sb.toString(), pageVo);

        Logger.debug("banklist size= " + banklist.size());

        return banklist;
    }

    @Override
    public void update(BankInfoVo bankInfoVo) {
        Bank bank= bankInfoVo.convertToBank();
        super.update(bank);
    }

    @Override
    public void save(BankInfoVo bankInfoVo) {
        Bank bank= bankInfoVo.convertToBank();
        super.create(bank);
    }


//    @Override
//    public Activity add(Activity vo) {
//        vo.setClickTime(0L);
//        vo.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        create(vo);
//        return vo;
//    }
//


}
