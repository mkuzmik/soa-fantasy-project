package authentication;

import entities.User;
import repos.UserRepository;
import request.AuthorizationException;
import request.RequestContext;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
  public Response login(@FormParam("username") String username, @FormParam("password") String password) {
    // TODO md5 password
    User user = userRepository.getByUsername(username);
    if (user == null || !user.getPassword().equals(password)) {
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
}
