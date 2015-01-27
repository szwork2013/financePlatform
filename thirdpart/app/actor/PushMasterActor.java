package actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;

import com.sunlights.common.vo.PushMessageVo;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: PushMasterActor.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class PushMasterActor extends UntypedActor {
    private final ActorRef pushMessageRouter = this
            .getContext()
            .actorOf(Props.create(PushSendActor.class).withRouter(new RoundRobinPool(100)), "pushMessageRouter");

    @Override
    public void onReceive(Object obj) throws Exception {
        if (obj instanceof PushMessageVo) {
            pushMessageRouter.tell(obj, getSelf());
        } else {
            unhandled(obj);
        }
    }
}
