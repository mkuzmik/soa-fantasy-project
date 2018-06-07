package repos;

import entities.Role;
import entities.User;

import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(UserRepository.class)
@SuppressWarnings("unchecked")
public class UserRepositoryBean implements UserRepository {

  @PersistenceContext(unitName = "postgresDb")
  private EntityManager entityManager;

  @Override
  public void save(User user) {
    entityManager.persist(user);
  }

  @Override
  public List<User> getAll() {
    return entityManager.createQuery("select a from User a").getResultList();
  }

  @Override
  public User getById(int id) {
    return (User) entityManager.createQuery("select a from User a where a.id = :id")
      .setParameter("id", id)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }

  @Override
  public void removeById(int id, int requestFromUserId) {
    User reguestFrom = getById(requestFromUserId);

    if (!reguestFrom.getRole().equals(Role.ADMIN) && !(requestFromUserId == id)) {
      throw new EJBException("Not authorized to remove this user");
    }

    User user = getById(id);
    entityManager.remove(user);
  }

  @Override
  public void update(User user, int requestFromUserId) {
    User reguestFrom = getById(requestFromUserId);

    if (!reguestFrom.getRole().equals(Role.ADMIN) && !(requestFromUserId == user.getId())) {
      throw new EJBException("Not authorized to remove this user");
    }
    entityManager.merge(user);
  }
}
