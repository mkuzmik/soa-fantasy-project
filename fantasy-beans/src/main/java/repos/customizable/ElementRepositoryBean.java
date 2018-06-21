package repos.customizable;

import entities.customizable.Element;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(ElementRepository.class)
public class ElementRepositoryBean implements ElementRepository {

  @PersistenceContext(unitName = "postgresDb")
  private EntityManager entityManager;

  @Override
  public void save(Element element) {
    entityManager.persist(element);
  }

  @Override
  public List<Element> getAll() {
    return entityManager.createQuery("select e from Element e")
      .getResultList();
  }

  @Override
  public Element getbyId(int id) {
    return (Element) entityManager.createQuery("select e from Element e where e.id = :id")
      .setParameter("id", id)
      .getResultList()
      .stream()
      .findFirst()
      .orElse(null);
  }

  @Override
  public void removeById(int id) {
    Element elem = getbyId(id);
    entityManager.remove(elem);
  }

  @Override
  public void update(Element element) {
    entityManager.merge(element);
  }
}
