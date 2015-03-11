package actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import play.libs.Akka;

/**
 * Created by Administrator on 2014/9/17.
 */
public class Actors {
    public static final ActorRef smsMasterActor = Akka.system().actorOf(Props.create(SmsMasterActor.class));
    public static final ActorRef pushMasterActor = Akka.system().actorOf(Props.create(PushMasterActor.class));
}
