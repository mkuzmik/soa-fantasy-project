package repos.customizable;

import entities.customizable.Category;

public interface CategoryRepository {
  void save(Category category);
  Category getById(int id);
}
