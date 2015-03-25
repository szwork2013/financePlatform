package services;

import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import util.DateCalcUtil;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: DateCalcService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class DateCalcService {

    private ParameterService parameterService = new ParameterService();

    public LocalDate getEndTradeDate(String startDateStr, int durationInDays){
        String holidayStr = parameterService.getParameterByName(ParameterConst.HOLIDAY_DATE);
        if (StringUtils.isEmpty(holidayStr)) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.MISSING_PARAM_CONFIG, ParameterConst.HOLIDAY_DATE);
        }

        LocalDate endDate = DateCalcUtil.getEndTradeDate(startDateStr, durationInDays, holidayStr);

        return endDate;
    }

}
