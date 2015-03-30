package com.sunlights.op.dal;

import com.sunlights.common.vo.PageVo;
import models.QuestionRecord;

import java.util.List;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: QuestionRecordService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface QuestionRecordDao {

    public QuestionRecord findQuestionRecordById(Long id);

    public List<QuestionRecord> findQuestionRecords(PageVo pageVo);

    public QuestionRecord createQuestionRecord(QuestionRecord questionRecord);

    public QuestionRecord updateQuestionRecord(QuestionRecord questionRecord);


    public void deleteQuestionRecord(QuestionRecord questionRecord);
}
