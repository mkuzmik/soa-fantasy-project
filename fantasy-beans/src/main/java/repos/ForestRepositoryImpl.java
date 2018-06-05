package repos;

import entities.Forest;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
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
}
