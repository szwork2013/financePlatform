import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

import static play.mvc.Results.ok;

public class Global extends GlobalSettings {
    private static final String PATH_APP = "/.*";

    @Override
    public void onStart(Application application) {
        super.onStart(application);
    }

    @Override
    public F.Promise<Result> onError(Http.RequestHeader reqHeader, Throwable reqthrow) {
        String uri = reqHeader.uri();
        String path = reqHeader.path();
        String host = reqHeader.host();
        String method = reqHeader.method();
        String clientAddress = reqHeader.remoteAddress();

        String pattern = "onError {0} {1} {2} {3}\n{4}";
        Logger.error(MessageFormat.format(pattern, clientAddress, method, host, path, uri), reqthrow);

        if (path.matches(PATH_APP)) {
            // 将异常转成Json返回
            JsonNode json = null;
            String errorCode = null;
            String errorMessage = null;
            Message.Severity severity = null;
            String detailMsg = null;

            Throwable cause = reqthrow.getCause();
            if (cause != null) {
                if (cause instanceof BusinessRuntimeException) {
                    errorCode = ((BusinessRuntimeException) cause).getErrorCode();
                    errorMessage = cause.getMessage();
                    severity = ((BusinessRuntimeException) cause).getSeverity();
                    detailMsg = ((BusinessRuntimeException) cause).getDetailMsg();
                }
                json = MessageUtil.getInstance().msgToJson(new Message(severity, errorCode, errorMessage, detailMsg));
            } else {
                errorCode = "fatal";
                errorMessage = cause.toString();
                String errorDetail = null;

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                cause.printStackTrace(pw);
                pw.flush();
                sw.flush();
                if (sw.toString().length() >= 200) {
                    errorDetail = sw.toString().substring(0, 200);
                }
                try {
                    sw.close();
                    pw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cause.printStackTrace();
                json = MessageUtil.getInstance().msgToJson(new Message(Message.SEVERITY_FATAL, errorCode, errorMessage, errorDetail));
            }

            Result result = ok(json);
            return F.Promise.pure(result);
        }
        return super.onError(reqHeader, reqthrow);
    }
}
