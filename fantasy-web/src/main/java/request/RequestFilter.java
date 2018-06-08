package request;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class RequestFilter implements ContainerRequestFilter {

  @Inject
  private RequestContext requestContext;

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    String authorization = containerRequestContext.getHeaderString("Authorization");
    if (authorization == null) {
      throw new AuthorizationException("Missing authorization token");
    }

    // TODO parse JWT token here
    requestContext.setUserId(Integer.valueOf(authorization));
  }
}
