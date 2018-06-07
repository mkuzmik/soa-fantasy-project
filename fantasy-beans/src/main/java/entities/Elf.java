package entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
  name="findById",
  query="SELECT OBJECT(e) FROM Elf e WHERE e.id = :id"
)
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

  @Transient
  @JsonInclude
  private int forestId;

  @JsonGetter("forestId")
  private int getForestId() {
    return forest.getId();
  }

  public Elf(String name, int arrows, BowType bowType, PowerType powerType, Forest forest) {
    this.name = name;
    this.arrows = arrows;
    this.bowType = bowType;
    this.powerType = powerType;
    this.forest = forest;
  }
}
