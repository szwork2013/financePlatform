package actor;

import akka.actor.UntypedActor;
import models.MessageSmsTxn;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import services.SmsMessageService;

/**
 * <p>Project: tradingsystem</p>
 * <p>Title: SmsSendActor.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class SmsSendActor extends UntypedActor {

    @Override
  public void onReceive(Object message) throws Exception {
    if (message instanceof MessageSmsTxn) {
      Logger.info("==============smsSendActor onReceive =====");
      final MessageSmsTxn sm = (MessageSmsTxn) message;
      JPA.withTransaction(new F.Callback0() {
          @Override
          public void invoke() throws Throwable {
              Logger.info("==============smsSendActor invoke =====");
              SmsMessageService smsMessageService = new SmsMessageService();
              smsMessageService.sendSms(sm);
          }
      });
    } else {
      unhandled(message);
    }
  }
}
