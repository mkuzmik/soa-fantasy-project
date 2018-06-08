package repos;

import entities.Elf;
import entities.Forest;
import entities.Role;
import entities.User;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(ForestRepository.class)
@SuppressWarnings("unchecked")
public class ForestRepositoryBean implements ForestRepository{

  @PersistenceContext(unitName = "postgresDb")
  private EntityManager entityManager;

  @EJB
  private ElfRepository elfRepository;

  @EJB
  private UserRepository userRepository;

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
  public void removeById(int id, int fromId) {
    Forest forest = getById(id);
    User reguestFrom = userRepository.getById(fromId);

    if (!reguestFrom.getRole().equals(Role.ADMIN) && !(fromId == forest.getUser().getId())) {
      throw new EJBException("Not authorized to update this forest");
    }

    forest.getElves().stream().map(Elf::getId).forEach(e -> elfRepository.removeById(e, fromId));
    entityManager.remove(forest);
  }

  @Override
  public void update(Forest forest, int fromId) {
    Forest edited = getById(forest.getId());
    User reguestFrom = userRepository.getById(fromId);

    if (!reguestFrom.getRole().equals(Role.ADMIN) && !(fromId == edited.getUser().getId())) {
      throw new EJBException("Not authorized to update this forest");
    }

    entityManager.merge(forest);
  }
}
