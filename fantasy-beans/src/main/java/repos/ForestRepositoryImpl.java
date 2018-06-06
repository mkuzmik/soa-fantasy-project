package repos;

import entities.Forest;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(ForestRepository.class)
@SuppressWarnings("unchecked")
public class ForestRepositoryImpl implements ForestRepository{

  @PersistenceContext(unitName = "postgresDb")
  EntityManager entityManager;

  @Override
  public void save(Forest forest) {
    entityManager.persist(forest);
  }

  @Override
  public List<Forest> getAll() {
    return entityManager.createQuery("select a from Forest a").getResultList();
  }

  @Override
  public Forest getById(int id) {
    return (Forest) entityManager.createQuery("select a from Forest a where a.id = :id")
      .setParameter("id", id)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }

  @Override
  public void removeById(int id) {
    Forest forest = getById(id);
    entityManager.remove(forest);
  }

  @Override
  public void update(Forest forest) {
    entityManager.merge(forest);
  }
}
