package exceptionmapping;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

  @Override
  public Response toResponse(EJBException e) {
    return Response.status(400).entity(new ExceptionResponse(e.getMessage())).build();
  }
}
