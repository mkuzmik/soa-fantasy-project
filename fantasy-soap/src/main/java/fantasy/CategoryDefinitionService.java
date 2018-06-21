package fantasy;

import entities.customizable.CategoryDefinition;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CategoryDefinitionService {

  @WebMethod
  void createCategoryDefinition(String name, String fieldName, String elementName, String elementFieldName, String elementEnum1Name,
                                String elementEnum2Name);

  @WebMethod
  List<CategoryDefinition> getAllCategoryDefinitions();

  @WebMethod
  String foo();

  @WebMethod
  void updateElement(int elementId, int newValue);
}
