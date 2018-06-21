package customizable;

import entities.customizable.CategoryDefinition;
import repos.customizable.CategoryDefinitionRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Path("/categoryDefinitions")
public class CategoryDefinitionRestController {

  @EJB
  private CategoryDefinitionRepository categoryDefinitionRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(CategoryDefinitionInput categoryDefinitionInput) {
    CategoryDefinition categoryDefinition = new CategoryDefinition(categoryDefinitionInput.getName(),
      categoryDefinitionInput.getFieldName(), categoryDefinitionInput.getElementDefinition());

    categoryDefinitionRepository.save(categoryDefinition);
    return Response.status(201).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<CategoryDefinition> getAll(@Context HttpHeaders httpHeaders) {
    if (httpHeaders.getAcceptableLanguages().contains(Locale.CHINESE)) {
      return categoryDefinitionRepository.getAll().stream().peek(cat -> cat.setName("CHINESE NAME")).collect(Collectors.toList());
    } else {
      return categoryDefinitionRepository.getAll();
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public CategoryDefinition getById(@PathParam("id") int id) {
    return categoryDefinitionRepository.getById(id);
  }
}
