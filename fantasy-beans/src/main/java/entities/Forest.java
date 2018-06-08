package entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forest implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  private int trees;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "forest")
  private Collection<Elf> elves;

  @ManyToOne(optional = false)
  @JsonIgnore
  private User user;

  @Transient
  @JsonInclude
  private int userId;

  @JsonGetter("userId")
  public int getUserId() {
    return user.getId();
  }

  public Forest(String name, int trees, User user) {
    this.name = name;
    this.trees = trees;
    this.user = user;
  }
}
