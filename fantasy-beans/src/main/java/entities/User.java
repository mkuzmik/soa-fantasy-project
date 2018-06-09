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
@NamedQuery(
  name="findByUsername",
  query="SELECT OBJECT(e) FROM User e WHERE e.username = :username"
)
public class User implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  @Column(unique = true)
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
