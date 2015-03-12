package com.sunlights.core.web;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.core.service.AppVersionService;
import com.sunlights.core.service.VersionRuleConfigService;
import com.sunlights.core.service.impl.AppVersionServiceImpl;
import com.sunlights.core.service.impl.VersionRuleConfigServiceImpl;
import com.wordnik.swagger.annotations.*;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by tangweiqun on 2015/3/12.
 */
@Transactional
public class VersionCheckController extends Controller {

    private VersionRuleConfigService versionRuleConfigService = new VersionRuleConfigServiceImpl();

    private AppVersionService appVersionService = AppVersionServiceImpl.getInstance();

    private MessageUtil messageUtil = MessageUtil.getInstance();

    @ApiOperation(value = "检查版本信息",
            notes = "MessageVo 的value是null", nickname = "checkVersion", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User-Agent", required = true, paramType = "head"),
    })
    @ApiResponses(value = {@ApiResponse(code = 0301, message = "当前版本已经是最新版本:{0}"),
            @ApiResponse(code = 0302, message = "有新版本{0},是否升级"),
            @ApiResponse(code = 0303, message = "您的APP版本过低请升级到:{0}"),
            @ApiResponse(code = 1302, message = "没有配置{0}版本规则"),
    })
    public Result checkVersion() {
        String userAgent = request().getHeader(AppConst.HEADER_USER_AGENT);
        String clientVersion = CommonUtil.getCurrentVersionFromStr(userAgent);
        String platform = CommonUtil.getCurrentPlatformFromStr(userAgent);

        Message message = versionRuleConfigService.checkVersion(platform, clientVersion);
        messageUtil.setMessage(message, null);

        Logger.debug("核对版本信息：" + messageUtil.toJson().toString());

        return ok(messageUtil.toJson());
    }


    @ApiOperation(value = "刷新版本信息",
            notes = "MessageVo 的value是null", nickname = "refreshVersion", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "platform", required = true, paramType = "url"),
    })
    @ApiResponses(value = {@ApiResponse(code = 0000, message = "操作成功"),
    })
    public Result refreshVersion(String platform) {
        String version = appVersionService.refresh(platform);

        messageUtil.setMessage(new Message(MsgCode.OPERATE_SUCCESS), version);

        Logger.debug("刷新版本的信息：" + messageUtil.toJson().toString());

        return ok(messageUtil.toJson());
    }
}
