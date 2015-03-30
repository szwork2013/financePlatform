package com.sunlights.op.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.DictService;
import com.sunlights.op.vo.DictManageVo;
import models.Dict;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: DictServiceImple.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class DictServiceImpl implements DictService {

	private EntityBaseDao entityBaseDao = new EntityBaseDao();

    private PageService pageService = new PageService();


    @Override
    public List<DictManageVo> findDictsBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.op.vo.DictManageVo(d) from Dict d");
        xsql.append(" where 1=1");
        xsql.append(" /~ and d.codeCat like {codeCat} ~/ ");
        xsql.append(" /~ and d.codeKey like {codeKey} ~/ ");
        xsql.append(" /~ and d.codeVal like {codeVal} ~/ ");
        xsql.append(" /~ and d.status = {status} ~/ ");
        xsql.append(" /~ and d.remarks like {remarks} ~/ ");
        xsql.append(" /~ and d.createTime > {beginTime} ~/ ");
        xsql.append(" /~ and d.createTime <= {endTime} ~/ ");
        xsql.append(" order by d.codeCat,d.seqNo");
        List<DictManageVo> dictManageVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return dictManageVos;
    }

    @Override
    public void create(DictManageVo dictManageVo) {
        if (hasDict(dictManageVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.CODE_EXIST_ERROR));
        }
        Dict dict = dictManageVo.convertToDict();
        dict.setCreateTime(new Date());
		entityBaseDao.create(dict);
    }

    @Override
    public void update(DictManageVo dictManageVo) {
        if (hasDict(dictManageVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.CODE_EXIST_ERROR));
        }
        Dict dict = dictManageVo.convertToDict();
		entityBaseDao.update(dict);
    }

    @Override
    public void delete(DictManageVo dictManageVo) {
        Dict dict = entityBaseDao.find(Dict.class, dictManageVo.getId());
        dict.setStatus(AppConst.STATUS_INVALID);
		entityBaseDao.update(dict);
    }

    private boolean hasDict(DictManageVo dictManageVo) {
        if (AppConst.STATUS_INVALID.equals(dictManageVo.getStatus())) {
            return false;
        }

        StringBuffer jpql = new StringBuffer();
        jpql.append(" select d from Dict d");
        jpql.append(" where d.codeCat = '" + dictManageVo.getCodeCat() + "'");
        jpql.append(" and d.codeKey = '" + dictManageVo.getCodeKey() + "'");
        jpql.append(" and d.status = '" + AppConst.STATUS_VALID + "'");
        if (dictManageVo.getId() != null) {
            jpql.append(" and d.id <> ").append(dictManageVo.getId());
        }
        List<Dict> dicts = entityBaseDao.find(jpql.toString());
        return !dicts.isEmpty();
    }
}
