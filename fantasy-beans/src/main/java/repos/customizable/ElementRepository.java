package repos.customizable;

import entities.customizable.Element;

import java.util.List;

public interface ElementRepository {
  void save(Element element, int fromUserId);
  List<Element> getAll();
  Element getbyId(int id);
  void removeById(int id, int fromUserId);
  void update(Element element, int fromUserId);
  List<Element> getBestByCategoryDefId(int categoryDefId, int amount);
}
