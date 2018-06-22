package repos.customizable;

import entities.Role;
import entities.User;
import entities.customizable.Category;
import entities.customizable.Element;
import jms.MessageService;
import repos.UserRepository;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(ElementRepository.class)
public class ElementRepositoryBean implements ElementRepository {

  @PersistenceContext(unitName = "postgresDb")
  private EntityManager entityManager;

  @Inject
  MessageService messageService;

  @EJB
  UserRepository userRepository;

  @Override
  public void save(Element element, int fromUserId) {
    auth(element.getCategory(), fromUserId);
    entityManager.persist(element);
    messageService.sendMessage("Element added: " + element.getName());
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
  public void removeById(int id, int fromUserId) {
    Element elem = getbyId(id);
    auth(elem.getCategory(), fromUserId);
    entityManager.remove(elem);
  }

  @Override
  public void update(Element element, int fromUserId) {
    auth(element.getCategory(), fromUserId);
    entityManager.merge(element);
  }

  @Override
  public List<Element> getBestByCategoryDefId(int categoryId, int amount) {
    return entityManager.createQuery("select e from Element e where e.category.categoryDefinition.id = :catId order by e.fieldValue desc")
      .setParameter("catId", categoryId)
      .setMaxResults(amount)
      .getResultList();
  }

  private void auth(Category cat, int fromId) {
    User usr = userRepository.getById(fromId);
    if (usr.getRole() != Role.ADMIN && fromId != cat.getUser().getId()) {
      throw new EJBException("Not authorized");
    }
  }
}
