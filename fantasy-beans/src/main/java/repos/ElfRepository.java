package repos;

import entities.Elf;

import java.util.List;

public interface ElfRepository {

  void save(Elf elf, int fromId);

  List<Elf> getAll();

  Elf getById(int id);

  void removeById(int id, int fromId);

  void update(Elf elf, int fromId);

  List<Elf> getBest(int amount);
}
