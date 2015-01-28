package weixin;

import org.fest.assertions.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsapiTicketTest {

    @Test
    public void testGetTicket() throws Exception {
        String ticket1 = JsapiTicket.getInstance().getTicket();
        String ticket2 = JsapiTicket.getInstance().getTicket();

        Assertions.assertThat(ticket1).isEqualTo(ticket2);
    }
}