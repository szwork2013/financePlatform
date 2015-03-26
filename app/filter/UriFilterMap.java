package filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public class UriFilterMap {

    public static List<String> uris = Lists.newArrayList();

    static {
        uris.add("/activity/remain");
        uris.add("/activity/isover");
        uris.add("/activity/rule");
        uris.add("/activity/registers/count");
        uris.add("/core/product/index");
        uris.add("/core/register");
        uris.add("/core/verificationcode");
        uris.add("/core/product/attentions");
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
