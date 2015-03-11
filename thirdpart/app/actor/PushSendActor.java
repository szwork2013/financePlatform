package actor;

import akka.actor.UntypedActor;
import com.sunlights.common.vo.PushMessageVo;
import play.db.jpa.JPA;
import play.libs.F;
import services.PushMessageService;

/**
 * <p>Project: tradingsystem</p>
 * <p>Title: SmsSendActor.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class PushSendActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof PushMessageVo) {
            final PushMessageVo pushMessageVo = (PushMessageVo) message;
            JPA.withTransaction(new F.Callback0() {
                @Override
                public void invoke() throws Throwable {
                    PushMessageService pushMessageService = new PushMessageService();
                    pushMessageService.sendPush(pushMessageVo);
                }
            });
        } else {
            unhandled(message);
        }
    }
}
