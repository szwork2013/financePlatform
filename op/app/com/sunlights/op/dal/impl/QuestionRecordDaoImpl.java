package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.QuestionRecordDao;
import models.QuestionRecord;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: QuestionRecordDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class QuestionRecordDaoImpl extends EntityBaseDao implements QuestionRecordDao {
    private PageDao pageDao = new PageDaoImpl();

    @Override
    public QuestionRecord findQuestionRecordById(Long id) {
        return super.find(QuestionRecord.class, id);
    }

    @Override
    public List<QuestionRecord> findQuestionRecords(PageVo pageVo) {
        StringBuffer xsql = new StringBuffer();
        xsql.append(" select q from QuestionRecord q");
        xsql.append(" where 1 = 1");
        xsql.append(" /~ and q.phoneNo like {phoneNo} ~/ ");
        xsql.append(" /~ and q.status = {status} ~/ ");
        xsql.append(" /~ and q.customerName like {customerName} ~/ ");
        xsql.append(" order by q.createTime desc ");
        List<QuestionRecord> list = pageDao.findXsqlBy(xsql.toString(), pageVo);
        return list;
    }

    @Override
    public QuestionRecord createQuestionRecord(QuestionRecord questionRecord) {
        return create(questionRecord);
    }

    @Override
    public QuestionRecord updateQuestionRecord(QuestionRecord questionRecord) {
        return update(questionRecord);
    }

    @Override
    public void deleteQuestionRecord(QuestionRecord questionRecord) {
        delete(questionRecord);
    }

}
