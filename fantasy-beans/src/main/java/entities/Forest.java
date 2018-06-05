package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Forest implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  private int treesAmount;

  public Forest(int treesAmount) {
    this.treesAmount = treesAmount;
  }
}
