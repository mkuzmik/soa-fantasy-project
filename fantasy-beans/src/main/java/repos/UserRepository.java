package repos;

import entities.User;

import java.util.List;

public interface UserRepository {

  void save(User user);

  List<User> getAll();

  User getById(int id);

  void removeById(int id, int requestFromUserId);

  void update(User user, int requestFromUserId);
}
