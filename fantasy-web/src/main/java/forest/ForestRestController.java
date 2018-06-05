package forest;

import entities.Forest;
import repos.ForestRepository;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/forests")
public class ForestRestController {

  @EJB
  private ForestRepository forestRepository;

  @GET
  @Path("/create/{amount}")
  public Response create(@PathParam("amount") int amount) {
    forestRepository.save(new Forest(amount));
    return Response.ok().build();
  }
  
  @GET
  @Path("/get")
  public Response getAll() {
    List<Forest> forests = forestRepository.getAll();
    return Response.ok(forests, MediaType.APPLICATION_JSON_TYPE).build();
  }
}
