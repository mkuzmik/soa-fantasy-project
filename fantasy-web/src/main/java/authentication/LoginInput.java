package authentication;

import lombok.Data;

@Data
public class LoginInput {
  private String username;
  private String password;
}
