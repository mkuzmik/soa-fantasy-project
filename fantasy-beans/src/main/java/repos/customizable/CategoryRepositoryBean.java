package repos.customizable;

import entities.Role;
import entities.User;
import entities.customizable.Category;
import repos.UserRepository;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(CategoryRepository.class)
public class CategoryRepositoryBean implements CategoryRepository {

  @EJB
  UserRepository userRepository;

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
  public void update(Category category, int fromUserId) {
    auth(category, fromUserId);
    entityManager.merge(category);
  }

  @Override
  public void removeById(int id, int fromUserId) {
    Category cat = getById(id);
    auth(cat, fromUserId);
    entityManager.remove(cat);
  }

  @Override
  public List<Category> getByCatDefId(int id) {
    return entityManager.createQuery("select e from Category e where e.categoryDefinition.id = :id")
      .setParameter("id", id)
      .getResultList();
  }

  private void auth(Category cat, int fromId) {
    User usr = userRepository.getById(fromId);
    if (usr.getRole() != Role.ADMIN && fromId != cat.getUser().getId()) {
      throw new EJBException("Not authorized");
    }
  }
}
