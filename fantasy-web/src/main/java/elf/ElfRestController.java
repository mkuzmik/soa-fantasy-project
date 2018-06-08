package elf;

import entities.Elf;
import entities.Forest;
import repos.ElfRepository;
import repos.ForestRepository;
import request.RequestContext;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/elves")
public class ElfRestController {

  @EJB
  private ElfRepository elfRepository;

  @EJB
  private ForestRepository forestRepository;

  @Inject
  RequestContext requestContext;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(ElfInput input) {
    Optional<Forest> maybeForest = Optional.ofNullable(forestRepository.getById(input.getForestId()));

    if (!maybeForest.isPresent()) {
      return Response.status(400).entity(String.format("Forest with id=%s does not exists", input.getForestId())).build();
    }

    Elf elf = new Elf(input.getName(), input.getArrows(), input.getBowType(), input.getPowerType(), maybeForest.get());
    elfRepository.save(elf, requestContext.getUserId());
    return Response.status(201).build();
  }

  @GET
  public Response getAll() {
    List<Elf> elves = elfRepository.getAll();
    return Response.ok(elves, MediaType.APPLICATION_JSON).build();
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") int id) {
    Optional<Elf> maybeElf = Optional.ofNullable(elfRepository.getById(id));

    if (maybeElf.isPresent()) {
      return Response.ok(maybeElf.get(), MediaType.APPLICATION_JSON).build();
    } else {
      return Response.status(404).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") int id, ElfInput input) {
    Elf toUpdate = elfRepository.getById(id);
    Forest newForest = forestRepository.getById(input.getForestId());

    toUpdate.setName(input.getName());
    toUpdate.setArrows(input.getArrows());
    toUpdate.setBowType(input.getBowType());
    toUpdate.setPowerType(input.getPowerType());
    toUpdate.setForest(newForest);

    elfRepository.update(toUpdate, requestContext.getUserId());
    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") int id) {
    elfRepository.removeById(id, requestContext.getUserId());
    return Response.ok().build();
  }
}
