package filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public class UriFilterMap {

    public static List<String> uris = Lists.newArrayList();

    static {
        uris.add("/customer/qrcode");
        uris.add("/thirdpart/getTicket");
        uris.add("/customer/getusermstr");
        uris.add("/activity/remain");
        uris.add("/activity/isover");
        uris.add("/activity/rule");
        uris.add("/activity/registers/count");
        uris.add("/core/product/index");
        uris.add("/core/register");
        uris.add("/core/verificationcode");
        uris.add("/core/product/attentions");
        uris.add("/core/product/detail");
        uris.add("/core/deposit/interest/current");
		uris.add("/op/");
    }

    public static boolean isContain(String uri) {
        for(String temp : uris) {
            if(uri.contains(temp)) {
                return true;
            }
        }

        return false;
    }
}
