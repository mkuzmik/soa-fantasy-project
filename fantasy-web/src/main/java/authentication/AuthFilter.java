package authentication;

import entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import request.AuthorizationException;
import request.RequestContext;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Provider
public class AuthFilter implements ContainerRequestFilter {

  @Data
  @AllArgsConstructor
  private static class Request {
    private String path;
    private String method;
  }

  @Inject
  private RequestContext requestContext;

  @Inject
  private JwtService jwtService;

  private Request[] publicPaths = new Request[] {
    new Request("/auth/login", "POST"),
    new Request("/users", "POST")
  };

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    if (isPublicPath(containerRequestContext)) {
      return;
    }

    String authorization = containerRequestContext.getHeaderString("Authorization");
    if (authorization == null) {
      throw new AuthorizationException("Missing authorization token");
    }

    Map<String, String> claims = jwtService.parse(authorization);
    requestContext.setUserId(Integer.valueOf(claims.get("userId")));
    requestContext.setUserRole(Role.valueOf(claims.get("role")));
  }

  private boolean isPublicPath(ContainerRequestContext containerRequestContext) {
    return Arrays.stream(publicPaths).anyMatch(req ->
    containerRequestContext.getUriInfo().getAbsolutePath().getPath().equals(req.getPath()) &&
    containerRequestContext.getMethod().equals(req.getMethod()));
  }
}
