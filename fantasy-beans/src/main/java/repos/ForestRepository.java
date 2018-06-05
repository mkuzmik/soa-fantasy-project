package repos;

import entities.Forest;

import java.util.List;

public interface ForestRepository {

  void save(Forest forest);

  List<Forest> getAll();
}
