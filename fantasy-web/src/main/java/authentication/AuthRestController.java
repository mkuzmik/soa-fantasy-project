package authentication;

import entities.User;
import repos.UserRepository;
import request.AuthorizationException;
import request.RequestContext;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthRestController {

  @EJB
  private UserRepository userRepository;

  @Inject
  private JwtService jwtService;

  @Inject
  private RequestContext requestContext;

  @POST
  @Path("/login")
  public Response login(LoginInput input) {
    // TODO md5 password
    User user = userRepository.getByUsername(input.getUsername());
    if (user == null || !user.getPassword().equals(input.getPassword())) {
      throw new AuthorizationException("Username or password is incorrect");
    }

    return Response.ok().header("Authorization", jwtService.generateTokenFor(user)).build();
  }

  @DELETE
  @Path("/logout")
  public Response logout() {
    jwtService.logout(requestContext.getUserId());
    return Response.ok().build();
  }

  @GET
  @Path("/logout/all")
  public Response logoutAll() {
    jwtService.logoutAll();

    return Response.ok().build();
  }
}
