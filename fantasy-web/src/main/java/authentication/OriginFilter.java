package authentication;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Provider
public class OriginFilter implements ContainerRequestFilter {

  private String[] forbiddenOrigins = new String[] {
    "http://forbidden.com",
    "https://danger.com"
  };

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    Optional<String> maybeOrigin = Optional.ofNullable(containerRequestContext.getHeaderString("Origin"));
    String origin = maybeOrigin.orElse("");
    if (Arrays.stream(forbiddenOrigins).anyMatch(origin::equals)) {
      throw new AuthenticationException("request from forbidden origin");
    }
  }
}
