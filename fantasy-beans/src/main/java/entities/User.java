package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  private List<Forest> forests;

  public User(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
}
