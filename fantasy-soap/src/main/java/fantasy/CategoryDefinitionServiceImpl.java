package fantasy;

import entities.customizable.CategoryDefinition;
import repos.customizable.CategoryDefinitionRepository;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "CategoryDefinitionService", portName= "CategoryDefinition", endpointInterface = "fantasy.CategoryDefinitionService")
public class CategoryDefinitionServiceImpl implements CategoryDefinitionService {

  @EJB
  CategoryDefinitionRepository categoryDefinitionRepository;

  @Override
  public void create(CategoryDefinition categoryDefinition) {
    categoryDefinitionRepository.save(categoryDefinition);
  }

  @Override
  public List<CategoryDefinition> getAll() {
    return categoryDefinitionRepository.getAll();
  }
}
