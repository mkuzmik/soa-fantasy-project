package repos.customizable;

import entities.customizable.CategoryDefinition;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(CategoryDefinitionRepository.class)
public class CategoryDefinitionRepositoryBean implements CategoryDefinitionRepository {

  @PersistenceContext(unitName = "postgresDb")
  private EntityManager entityManager;

  @Override
  public void save(CategoryDefinition categoryDefinition) {
    entityManager.persist(categoryDefinition);
  }

  @Override
  public List<CategoryDefinition> getAll() {
    return entityManager.createQuery("SELECT e from CategoryDefinition e").getResultList();
  }

  @Override
  public CategoryDefinition getByName(String name) {
    return (CategoryDefinition) entityManager.createQuery("SELECT e from CategoryDefinition e where e.name = :name")
      .setParameter("name", name)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }

  @Override
  public CategoryDefinition getById(int id) {
    return (CategoryDefinition) entityManager.createQuery("SELECT e from CategoryDefinition e where e.id = :id")
      .setParameter("id", id)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }
}
