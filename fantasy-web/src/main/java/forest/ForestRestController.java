package forest;

import entities.Elf;
import entities.Forest;
import repos.ElfRepository;
import repos.ForestRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/forests")
public class ForestRestController {

  @EJB
  private ForestRepository forestRepository;

  @EJB
  private ElfRepository elfRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(ForestInput input) {
    Forest forest = new Forest(input.getName(), input.getTrees());
    forestRepository.save(forest);
    return Response.status(201).build();
  }
  
  @GET
  public Response getAll() {
    List<Forest> forests = forestRepository.getAll();
    return Response.ok(forests, MediaType.APPLICATION_JSON).build();
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
    forestRepository.update(forest);
    return Response.status(200).build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") int id) {
    Forest forest = forestRepository.getById(id);
    forest.getElves().stream()
      .map(Elf::getId)
      .forEach(elfRepository::removeById);
    forestRepository.removeById(id);
    return Response.status(200).build();
  }
}
