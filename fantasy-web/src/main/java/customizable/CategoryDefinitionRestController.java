package customizable;

import entities.customizable.CategoryDefinition;
import repos.customizable.CategoryDefinitionRepository;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categoryDefinition")
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
}
