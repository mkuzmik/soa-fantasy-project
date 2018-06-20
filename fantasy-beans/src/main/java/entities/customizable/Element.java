package entities.customizable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@Setter
@EqualsAndHashCode
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

  @Override
  public String toString() {
    return "Element{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", fieldValue=" + fieldValue +
      ", enum1Value='" + enum1Value + '\'' +
      ", enum2Value='" + enum2Value + '\'' +
      '}';
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getFieldValue() {
    return fieldValue;
  }

  public String getEnum1Value() {
    return enum1Value;
  }

  public String getEnum2Value() {
    return enum2Value;
  }

  @XmlTransient
  public Category getCategory() {
    return category;
  }
}
