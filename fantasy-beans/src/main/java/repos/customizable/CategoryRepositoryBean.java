package repos.customizable;

import entities.customizable.Category;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(CategoryRepository.class)
public class CategoryRepositoryBean implements CategoryRepository {

  @PersistenceContext(unitName = "postgresDb")
  private EntityManager entityManager;

  @Override
  public void save(Category category) {
    entityManager.persist(category);
  }

  @Override
  public Category getById(int id) {
    return (Category) entityManager.createQuery("select e from Category e where e.id = :id")
      .setParameter("id", id)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }

  @Override
  public List<Category> getAll() {
    return entityManager.createQuery("select e from Category e")
      .getResultList();
  }

  @Override
  public void update(Category category) {
    entityManager.merge(category);
  }

  @Override
  public void removeById(int id) {
    Category cat = getById(id);
    entityManager.remove(cat);
  }

  @Override
  public List<Category> getByCatDefId(int id) {
    return entityManager.createQuery("select e from Category e where e.categoryDefinition.id = :id")
      .setParameter("id", id)
      .getResultList();
  }
}
