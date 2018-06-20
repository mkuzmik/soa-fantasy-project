package entities.customizable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Category implements Serializable {

  @Id
  @GeneratedValue
  private int id;
  private String name;
  private int fieldValue;

  @ManyToOne(optional = false)
  @JsonIgnore
  private CategoryDefinition categoryDefinition;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
  private List<Element> elements;

  public Category(String name, int fieldValue, CategoryDefinition categoryDefinition) {
    this.name = name;
    this.fieldValue = fieldValue;
    this.categoryDefinition = categoryDefinition;
  }

  @Override
  public String toString() {
    return "Category{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", fieldValue=" + fieldValue +
      ", elements=" + elements +
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

  @XmlTransient
  public CategoryDefinition getCategoryDefinition() {
    return categoryDefinition;
  }

  public List<Element> getElements() {
    return elements;
  }
}
