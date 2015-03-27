package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.service.ShowStatisticsService;
import com.sunlights.customer.service.impl.ShowStatisticsServiceImpl;
import com.sunlights.customer.vo.CustomerVo;
import com.wordnik.swagger.annotations.*;
import models.ShowStatistics;
import play.db.jpa.Transactional;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/3.
 */
@Transactional
@Api(value = "/customer", description = "为活动提供的加工后的数据的服务")
public class DataProcess4ActivityController extends ActivityBaseController {

    private ShowStatisticsService showStatisticsService = new ShowStatisticsServiceImpl();

    /**
     * 获取注册人数(提供给活动中的h5展示的，不是真正的注册用户数
     * @return
     */
    @ApiOperation(value = "获取注册人数(提供给活动中的h5展示的，不是真正的注册用户数",
            notes = "", response = MessageVo.class, nickname = "getRegisterNumbers4Activity", httpMethod = "GET")
    @ApiImplicitParams({

    })
    @ApiResponses(value = {@ApiResponse(code = 0000, message = "查询成功")

    })
    public Result getRegisterNumbers4Activity() {

        ShowStatistics showStatistics = showStatisticsService.getShowStatisticsByType(ShowStatistics.StatType.REGISTE_STAT.getType()).get(0);

        Map<String, Long> numberMap = new HashMap<String, Long>();
        numberMap.put("registerNums", showStatistics.getStatCount());
        Message message = new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS);
        messageUtil.setMessage(message, numberMap);

        return ok(messageUtil.toJson());
    }

}
