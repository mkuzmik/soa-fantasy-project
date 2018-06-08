package repos;

import entities.Forest;

import java.util.List;
import java.util.Optional;

public interface ForestRepository {

  void save(Forest forest);

  List<Forest> getAll();

  Forest getById(int id);

  void removeById(int id, int fromUserId);

  void update(Forest forest, int fromUserId);
}
