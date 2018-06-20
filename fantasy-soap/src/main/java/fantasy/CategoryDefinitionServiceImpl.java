package fantasy;

import entities.customizable.CategoryDefinition;
import entities.customizable.ElementDefinition;
import repos.customizable.CategoryDefinitionRepository;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "CategoryDefinitionService", portName= "CategoryDefinition", endpointInterface = "fantasy.CategoryDefinitionService")
public class CategoryDefinitionServiceImpl implements CategoryDefinitionService {

  @EJB
  private CategoryDefinitionRepository categoryDefinitionRepository;

  @Override
  public void create(String name, String fieldName, String elementName, String elementFieldName, String elementEnum1Name, String elementEnum2Name) {
    ElementDefinition elemDef = new ElementDefinition(elementName, elementFieldName, elementEnum1Name, elementEnum2Name);
    CategoryDefinition catDef =  new CategoryDefinition(name, fieldName, elemDef);
    categoryDefinitionRepository.save(catDef);
  }

  @Override
  public List<CategoryDefinition> getAll() {
    return categoryDefinitionRepository.getAll();
  }

  @Override
  public String foo() {
    return "bar";
  }
}
