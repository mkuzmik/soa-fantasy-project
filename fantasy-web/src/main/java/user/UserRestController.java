package user;

import entities.Forest;
import entities.User;
import forest.ForestInput;
import repos.ForestRepository;
import repos.UserRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/users")
public class UserRestController {

  @EJB
  private UserRepository userRepository;

  @EJB
  private ForestRepository forestRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(UserInput input) {
    User user = new User(input.getUsername(), input.getPassword(), input.getRole());
    userRepository.save(user);
    return Response.status(201).build();
  }

  @GET
  public Response getAll() {
    List<User> users = userRepository.getAll();
    return Response.ok(users, MediaType.APPLICATION_JSON).build();
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") int id) {
    Optional<User> maybeUser = Optional.ofNullable(userRepository.getById(id));

    if (maybeUser.isPresent()) {
      return Response.ok(maybeUser.get(), MediaType.APPLICATION_JSON).build();
    } else {
      return Response.status(404).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") int id, UserInput input) {
    User user = userRepository.getById(id);
    user.setUsername(input.getUsername());
    user.setPassword(input.getPassword());
    user.setRole(input.getRole());
    // TODO take fromId from request context
    userRepository.update(user, user.getId());
    return Response.status(200).build();
  }
}
