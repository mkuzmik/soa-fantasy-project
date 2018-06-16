package entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;

@Entity
@XmlRootElement
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getArrows() {
    return arrows;
  }

  public void setArrows(int arrows) {
    this.arrows = arrows;
  }

  public BowType getBowType() {
    return bowType;
  }

  public void setBowType(BowType bowType) {
    this.bowType = bowType;
  }

  public PowerType getPowerType() {
    return powerType;
  }

  public void setPowerType(PowerType powerType) {
    this.powerType = powerType;
  }

  @XmlTransient
  public Forest getForest() {
    return forest;
  }

  public void setForest(Forest forest) {
    this.forest = forest;
  }

  public void setForestId(int forestId) {
    this.forestId = forestId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Elf elf = (Elf) o;
    return id == elf.id &&
      arrows == elf.arrows &&
      forestId == elf.forestId &&
      Objects.equals(name, elf.name) &&
      bowType == elf.bowType &&
      powerType == elf.powerType &&
      Objects.equals(forest, elf.forest);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, arrows, bowType, powerType, forest, forestId);
  }
}
