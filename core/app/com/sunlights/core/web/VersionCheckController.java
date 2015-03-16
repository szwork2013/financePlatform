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
@Api(value = "/core")
public class VersionCheckController extends Controller {

    private VersionRuleConfigService versionRuleConfigService = new VersionRuleConfigServiceImpl();

    private AppVersionService appVersionService = AppVersionServiceImpl.getInstance();

    private MessageUtil messageUtil = MessageUtil.getInstance();

    @ApiOperation(value = "检查版本信息",
            notes = "User-Agent的值类似：Mozilla/5.0 (Android; CPU Android OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\\jindoujialicai\\0.9", nickname = "version", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User-Agent", required = true, paramType = "header"),
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
            notes = "platform的值只能是 ios 或者 android ", nickname = "version", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "platform", required = true, paramType = "path"),
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
