package repos.customizable;

import entities.customizable.Element;

import java.util.List;

public interface ElementRepository {
  void save(Element element);
  List<Element> getAll();
  Element getbyId(int id);
  void removeById(int id);
  void update(Element element);
}
