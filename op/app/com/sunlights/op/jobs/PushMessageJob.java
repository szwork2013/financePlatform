package com.sunlights.op.jobs;

import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.MessageRuleService;
import com.sunlights.op.service.impl.MessageRuleServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import play.Logger;
import play.Play;
import play.db.jpa.JPA;
import play.libs.F;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: PushMessageJob.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class PushMessageJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Logger.info("=========[PushMessageJob execute begin]=========== " + DBHelper.getCurrentTime());

                PageVo pageVo = new PageVo();
                String jobPageSize = Play.application().configuration().getString("job.pageSize");
                if (jobPageSize != null && !"".equals(jobPageSize.trim())) {
                    pageVo.setPageSize(Integer.valueOf(jobPageSize));
                }

                int index = 0;
                MessageRuleService pushMessageService = new MessageRuleServiceImpl();

//                while (true) {
//                    pageVo.setIndex(index);
//                    List<PushMessageVo> list = pushMessageService.findPushMessageList(pageVo);
//                    if (list.isEmpty()) {
//                        break;
//                    }
//                    index += pageVo.getPageSize();
//                    pushMessageService.pushMessage(list);
//                }

                Logger.info("=========[PushMessageJob execute end]============= " + DBHelper.getCurrentTime());
            }
        });

    }
}
