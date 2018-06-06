package entities;

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

  public Forest(String name, int trees) {
    this.name = name;
    this.trees = trees;
  }
}
