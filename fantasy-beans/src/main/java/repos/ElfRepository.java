package repos;

import entities.Elf;

import java.util.List;

public interface ElfRepository {

  void save(Elf elf);

  List<Elf> getAll();

  Elf getById(int id);

  void removeById(int id);

  void update(Elf elf);
}
