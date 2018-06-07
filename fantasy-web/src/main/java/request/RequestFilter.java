package request;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

@Provider
public class RequestFilter implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    Map<String, Cookie> cookies = containerRequestContext.getCookies();
    throw new RuntimeException("authentication error");

  }
}
