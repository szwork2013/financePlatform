package com.sunlights.op.service;

import com.sunlights.common.exceptions.ConverterException;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.QuestionRecordVo;

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
public interface QuestionRecordService {
    public List<QuestionRecordVo> findQuestionRecords(PageVo pageVo) throws ConverterException;

    public void createQuestionRecord(QuestionRecordVo questionRecordVo) throws ConverterException;

    public void updateQuestionRecord(QuestionRecordVo questionRecordVo) throws ConverterException;

    public void deleteQuestionRecord(QuestionRecordVo questionRecordVo);

}
