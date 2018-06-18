package repos.customizable;

import entities.customizable.CategoryDefinition;

import java.util.List;

public interface CategoryDefinitionRepository {
  CategoryDefinition getByName(String name);

  void save(CategoryDefinition t);
  List<CategoryDefinition> getAll();
  CategoryDefinition getById(int id);
}
