package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Elf implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  private int arrows;

  @Enumerated(EnumType.STRING)
  private BowType bowType;

  @Enumerated(EnumType.STRING)
  private PowerType powerType;

  @ManyToOne(optional = false)
  @JsonIgnore
  private Forest forest;

  public Elf(String name, int arrows, BowType bowType, PowerType powerType, Forest forest) {
    this.name = name;
    this.arrows = arrows;
    this.bowType = bowType;
    this.powerType = powerType;
    this.forest = forest;
  }
}
