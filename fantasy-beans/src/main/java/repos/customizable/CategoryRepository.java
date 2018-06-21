package repos.customizable;

import entities.customizable.Category;

import java.util.List;

public interface CategoryRepository {
  void save(Category category);
  Category getById(int id);
  List<Category> getByCatDefId(int id);
  List<Category> getAll();
  void update(Category category);
  void removeById(int id);
}
