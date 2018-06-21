package repos.customizable;

import entities.BowType;
import entities.Elf;
import entities.Forest;
import entities.PowerType;
import entities.customizable.Category;
import entities.customizable.CategoryDefinition;
import entities.customizable.Element;
import repos.ElfRepository;
import repos.ForestRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ElfRepository.class)
public class ElfCustomizableRepoBean implements ElfRepository {

  @EJB
  CategoryRepository categoryRepository;

  @EJB
  private CategoryDefinitionRepository categoryDefinitionRepository;

  @EJB
  ElementRepository elementRepository;

  @EJB
  ForestRepository forestRepository;

  private CategoryDefinition init() {
    return categoryDefinitionRepository.getByName("forest");
  }

  @Override
  public void save(Elf elf, int fromId) {
    Category cat = categoryRepository.getById(elf.getForest().getId());
    Element element = new Element(elf.getName(), elf.getArrows(), elf.getBowType().toString(), elf.getPowerType().toString(),
      cat);
    elementRepository.save(element, fromId);
  }

  @Override
  public List<Elf> getAll() {
    CategoryDefinition categoryDefinition = init();
    return elementRepository.getAll().stream().filter(el -> el.getCategory().getCategoryDefinition().getId() == categoryDefinition.getId())
      .map(el -> {
        Forest forest = forestRepository.getById(el.getCategory().getId());
        Elf elf = Element.toElf(el);
        elf.setForest(forest);
        return elf;
      }).collect(Collectors.toList());
  }

  @Override
  public Elf getById(int id) {
    Element element = elementRepository.getbyId(id);
    Elf elf = Element.toElf(element);
    return elf;
  }

  @Override
  public void removeById(int id, int fromId) {
    elementRepository.removeById(id, fromId);
  }

  @Override
  public void update(Elf elf, int fromId) {
    Element element = elementRepository.getbyId(elf.getId());
    element.setName(elf.getName());
    element.setFieldValue(elf.getArrows());
    element.setEnum1Value(elf.getBowType().toString());
    element.setEnum2Value(elf.getPowerType().toString());
    elementRepository.update(element, fromId);
  }

  @Override
  public List<Elf> getBest(int amount) {
    CategoryDefinition forestDef = init();
    return elementRepository.getBestByCategoryDefId(forestDef.getId(), amount)
      .stream()
      .map(Element::toElf)
      .collect(Collectors.toList());
  }
}
