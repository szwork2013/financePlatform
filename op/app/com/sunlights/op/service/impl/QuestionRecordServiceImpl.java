package com.sunlights.op.service.impl;

import com.sunlights.common.AppConst;
import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.QuestionRecordDao;
import com.sunlights.op.dal.impl.QuestionRecordDaoImpl;
import com.sunlights.op.service.QuestionRecordService;
import com.sunlights.op.vo.QuestionRecordVo;
import models.QuestionRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: QuestionRecordServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class QuestionRecordServiceImpl implements QuestionRecordService {

    private QuestionRecordDao questionRecordDao = new QuestionRecordDaoImpl();

    @Override
    public List<QuestionRecordVo> findQuestionRecords(PageVo pageVo) throws ConverterException{
        List<QuestionRecord> list = questionRecordDao.findQuestionRecords(pageVo);

        List<QuestionRecordVo> voList = new ArrayList<QuestionRecordVo>();
        for (QuestionRecord questionRecord : list) {
            QuestionRecordVo questionRecordVo = new QuestionRecordVo();
            questionRecordVo = ConverterUtil.fromEntity(questionRecordVo, questionRecord);
            voList.add(questionRecordVo);
        }
        return voList;
    }

    @Override
    public void createQuestionRecord(QuestionRecordVo questionRecordVo) throws ConverterException{
        QuestionRecord questionRecord = new QuestionRecord();
        questionRecord = ConverterUtil.toEntity(questionRecord, questionRecordVo);
        questionRecord.setStatus(AppConst.STATUS_VALID);
        questionRecord.setCreateTime(DBHelper.getCurrentTime());
        questionRecordDao.createQuestionRecord(questionRecord);
    }

    @Override
    public void updateQuestionRecord(QuestionRecordVo questionRecordVo) throws ConverterException{
        QuestionRecord questionRecord = new QuestionRecord();
        questionRecord = ConverterUtil.toEntity(questionRecord, questionRecordVo);
        questionRecord.setUpdateTime(DBHelper.getCurrentTime());
        questionRecordDao.updateQuestionRecord(questionRecord);
    }

    @Override
    public void deleteQuestionRecord(QuestionRecordVo questionRecordVo) {
        QuestionRecord questionRecord = questionRecordDao.findQuestionRecordById(questionRecordVo.getId());
        questionRecordDao.deleteQuestionRecord(questionRecord);
    }
}
