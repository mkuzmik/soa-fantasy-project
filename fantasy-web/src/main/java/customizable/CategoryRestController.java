package customizable;

import entities.customizable.Category;
import entities.customizable.CategoryDefinition;
import repos.customizable.CategoryDefinitionRepository;
import repos.customizable.CategoryRepository;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
public class CategoryRestController {

  @EJB
  private CategoryRepository categoryRepository;

  @EJB
  private CategoryDefinitionRepository categoryDefinitionRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(CategoryInput input) {
    CategoryDefinition catDef = categoryDefinitionRepository.getById(input.getCategoryDefinitionId());
    Category category = new Category(input.getName(), input.getFieldValue(), catDef);
    categoryRepository.save(category);
    return Response.status(201).build();
  }
}
