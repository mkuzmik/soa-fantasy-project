package repos;

import entities.Elf;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(ElfRepository.class)
@SuppressWarnings("unchecked")
public class ElfRepositoryImpl implements ElfRepository {

  @PersistenceContext(unitName = "postgresDb")
  EntityManager entityManager;

  @Override
  public void save(Elf elf) {
    entityManager.persist(elf);
  }

  @Override
  public List<Elf> getAll() {
    return entityManager.createQuery("select a from Elf a").getResultList();
  }

  @Override
  public Elf getById(int id) {
    return (Elf) entityManager.createQuery("select a from Elf a where a.id = :id")
      .setParameter("id", id)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }

  @Override
  public void removeById(int id) {
    Elf elf = getById(id);
    entityManager.remove(elf);
  }

  @Override
  public void update(Elf elf) {
    entityManager.merge(elf);
  }
}
