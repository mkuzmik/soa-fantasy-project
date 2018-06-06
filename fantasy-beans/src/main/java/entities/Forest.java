package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class Forest implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  private int trees;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "forest")
  private Collection<Elf> elves;

  public Forest(String name, int trees) {
    this.name = name;
    this.trees = trees;
  }
}
