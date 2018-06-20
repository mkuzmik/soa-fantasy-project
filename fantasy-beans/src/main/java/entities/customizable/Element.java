package entities.customizable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Element implements Serializable {

  @Id
  @GeneratedValue
  private int id;
  private String name;
  private int fieldValue;
  private String enum1Value;
  private String enum2Value;

  @ManyToOne(optional = false)
  @JsonIgnore
  private Category category;

  public Element(String name, int fieldValue, String enum1Value, String enum2Value, Category category) {
    this.name = name;
    this.fieldValue = fieldValue;
    this.enum1Value = enum1Value;
    this.enum2Value = enum2Value;
    this.category = category;
  }
}
