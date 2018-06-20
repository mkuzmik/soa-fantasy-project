package repos.customizable;

import entities.customizable.Category;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
