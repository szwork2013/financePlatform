package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by loki on 2/2/15.
 */
public class Application extends Controller {
    public static Result swagger() {
        return ok(views.html.swagger.render());
    }
}
