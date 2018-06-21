package forest;

import entities.Forest;
import entities.User;
import repos.ForestRepository;
import repos.UserRepository;
import request.RequestContext;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/forests")
public class ForestRestController {

  @EJB
  private ForestRepository forestRepository;

  @EJB
  private UserRepository userRepository;

  @Inject
  private RequestContext requestContext;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(ForestInput input) {
    User user = userRepository.getById(requestContext.getUserId());
    Forest forest = new Forest(input.getName(), input.getTrees(), user);
    forestRepository.save(forest);
    return Response.status(201).build();
  }
  
  @GET
  @Produces("application/xml,application/json")
  public List<Forest> getAll(@Context HttpHeaders httpHeaders) {
    // dummy content negotiation
    if (httpHeaders.getAcceptableLanguages().contains(Locale.CHINESE)) {
      return forestRepository.getAll().stream().peek(forest -> forest.setName("chinese forest")).collect(Collectors.toList());
    } else {
      return forestRepository.getAll();
    }
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") int id) {
    Optional<Forest> maybeForest = Optional.ofNullable(forestRepository.getById(id));

    if (maybeForest.isPresent()) {
      return Response.ok(maybeForest.get(), MediaType.APPLICATION_JSON).build();
    } else {
      return Response.status(404).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") int id, ForestInput input) {
    Forest forest = forestRepository.getById(id);
    forest.setName(input.getName());
    forest.setTrees(input.getTrees());
    forestRepository.update(forest, requestContext.getUserId());
    return Response.status(200).build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") int id) {
    forestRepository.removeById(id, requestContext.getUserId());
    return Response.status(200).build();
  }
}
