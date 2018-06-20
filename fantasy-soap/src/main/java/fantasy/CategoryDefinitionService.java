package fantasy;

import entities.customizable.CategoryDefinition;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CategoryDefinitionService {

  @WebMethod
  void create(CategoryDefinition categoryDefinition);

  @WebMethod
  List<CategoryDefinition> getAll();
}
