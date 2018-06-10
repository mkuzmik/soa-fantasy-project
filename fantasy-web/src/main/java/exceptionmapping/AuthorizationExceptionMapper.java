package exceptionmapping;

import request.AuthorizationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationExceptionMapper implements ExceptionMapper<AuthorizationException> {

  @Override
  public Response toResponse(AuthorizationException e) {
    return Response.status(401).entity(new ExceptionResponse(e.getMessage())).build();
  }
}
