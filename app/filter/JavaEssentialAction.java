package filter;

import play.api.libs.iteratee.Iteratee;
import play.api.mvc.EssentialAction;
import play.api.mvc.RequestHeader;
import play.api.mvc.Result;
import scala.runtime.AbstractFunction1;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: JavaEssentialAction.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public abstract class JavaEssentialAction extends AbstractFunction1<RequestHeader, Iteratee<byte[], Result>> implements EssentialAction {
}
