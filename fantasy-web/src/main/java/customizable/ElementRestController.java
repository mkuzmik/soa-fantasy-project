package customizable;

import entities.customizable.Category;
import entities.customizable.Element;
import repos.customizable.CategoryRepository;
import repos.customizable.ElementRepository;
import request.RequestContext;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/elements")
public class ElementRestController {

  @EJB
  private ElementRepository elementRepository;

  @EJB
  private CategoryRepository categoryRepository;

  @Inject
  private RequestContext requestContext;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(ElementInput input) {
    Category cat = categoryRepository.getById(input.getCategoryId());
    Element element = new Element(input.getName(), input.getFieldValue(), input.getEnum1Value(), input.getEnum2Value(), cat);
    elementRepository.save(element, requestContext.getUserId());
    return Response.status(201).build();
  }
}
