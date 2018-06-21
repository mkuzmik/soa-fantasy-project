package repos.customizable;

import entities.Forest;
import entities.customizable.Category;
import entities.customizable.CategoryDefinition;
import repos.ForestRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ForestRepository.class)
public class ForestCustomizableRepoBean implements ForestRepository {

  @EJB
  private CategoryDefinitionRepository categoryDefinitionRepository;

  @EJB
  private CategoryRepository categoryRepository;

  @EJB
  private ElementRepository elementRepository;

  private CategoryDefinition init() {
    return categoryDefinitionRepository.getByName("forest");
  }

  @Override
  public void save(Forest forest) {
    CategoryDefinition categoryDefinition = init();
    Category category = new Category(forest.getName(), forest.getTrees(), categoryDefinition, forest.getUser());
    categoryRepository.save(category);
  }

  @Override
  public List<Forest> getAll() {
    CategoryDefinition catDef = init();
    return categoryRepository.getByCatDefId(catDef.getId()).stream()
      .map(category ->  Category.toForest(category))
      .collect(Collectors.toList());
  }

  @Override
  public Forest getById(int id) {
    Category cat = categoryRepository.getById(id);
    return Category.toForest(cat);
  }

  @Override
  public void removeById(int id, int fromUserId) {
    Category cat = categoryRepository.getById(id);
    cat.getElements().forEach(el -> elementRepository.removeById(el.getId()));
    categoryRepository.removeById(id);
  }

  @Override
  public void update(Forest forest, int fromUserId) {
    Category cat = categoryRepository.getById(forest.getId());
    cat.setName(forest.getName());
    cat.setFieldValue(forest.getTrees());
    categoryRepository.update(cat);
  }
}
