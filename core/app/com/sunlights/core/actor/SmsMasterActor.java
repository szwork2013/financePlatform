package com.sunlights.core.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import com.sunlights.core.models.SmsMessage;

/**
 * <p>Project: tradingsystem</p>
 * <p>Title: SmsMasterActor.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SmsMasterActor extends UntypedActor {

    ActorRef lastSender = getContext().system().deadLetters();

    private final ActorRef smsSendRouter = this
            .getContext()
            .actorOf(Props.create(SmsSendActor.class)
                    .withRouter(new RoundRobinPool(100)), "smsSendRouter");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof SmsMessage) {
            smsSendRouter.tell(message, getSelf());
        } else {
            unhandled(message);
        }
    }
}
