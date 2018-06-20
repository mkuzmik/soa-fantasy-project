package repos.customizable;

import entities.customizable.Element;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(ElementRepository.class)
public class ElementRepositoryBean implements ElementRepository {

  @PersistenceContext(unitName = "postgresDb")
  private EntityManager entityManager;

  @Override
  public void save(Element element) {
    entityManager.persist(element);
  }
}
