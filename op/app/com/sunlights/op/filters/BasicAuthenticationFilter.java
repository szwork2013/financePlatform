package com.sunlights.op.filters;

import play.api.libs.iteratee.Iteratee;
import play.api.mvc.EssentialAction;
import play.api.mvc.EssentialFilter;
import play.api.mvc.RequestHeader;
import play.api.mvc.Result;



/**
 * Created by loki on 12/9/14.
 */
public class BasicAuthenticationFilter implements EssentialFilter {

    public BasicAuthenticationFilter() {
        // Left empty
    }

    @Override
    public EssentialAction apply(final EssentialAction next) {
        return new JavaEssentialAction() {
            @Override
            public EssentialAction apply() {
                return next.apply();
            }

            @Override
            public Iteratee<byte[], Result> apply(RequestHeader rh) {
//                String uri = rh.uri();
//                if ("/login".equals(uri) || uri.contains("assets") || uri.contains("webjars")) {
//                    return next.apply(rh);
//                }
//                Option<String> user = rh.session().get("user");
//                //TODO add shiro
//                if (user.isEmpty()) {
//                    return Done.apply(ok(login.render("")).toScala(), null);
//                } else {
//                    return next.apply(rh);
//                }
				return next.apply(rh);

            }
        };
    }

}
