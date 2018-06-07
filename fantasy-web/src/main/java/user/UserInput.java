package user;

import entities.Role;
import lombok.Data;

@Data
public class UserInput {

  private String username;
  private String password;
  private Role role;
}
