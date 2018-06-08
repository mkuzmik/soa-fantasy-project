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
import javax.persistence.Query;
import java.util.List;

@Stateless
@Remote(ElfRepository.class)
@SuppressWarnings("unchecked")
public class ElfRepositoryBean implements ElfRepository {

  @PersistenceContext(unitName = "postgresDb")
  EntityManager entityManager;

  @EJB
  private UserRepository userRepository;

  @EJB
  private ForestRepository forestRepository;

  @Override
  public void save(Elf elf, int fromId) {
    authorize(fromId, elf.getForest().getId());
    entityManager.persist(elf);
  }

  @Override
  public List<Elf> getAll() {
    return entityManager.createQuery("select a from Elf a").getResultList();
  }

  @Override
  public Elf getById(int id) {
    return (Elf) entityManager.createNamedQuery("findById")
      .setParameter("id", id)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }

  @Override
  public void removeById(int id, int fromId) {
    Elf elf = getById(id);
    authorize(fromId, elf.getForest().getId());
    entityManager.remove(elf);
  }

  @Override
  public void update(Elf elf, int fromId) {
    authorize(fromId, elf.getForest().getId());
    entityManager.merge(elf);
  }

  private void authorize(int fromId, int forestId) {
    Forest forest = forestRepository.getById(forestId);
    User user = userRepository.getById(fromId);

    if (!user.getRole().equals(Role.ADMIN) && !(fromId == forest.getUser().getId())) {
      throw new EJBException("Not authorized");
    }
  }
}
