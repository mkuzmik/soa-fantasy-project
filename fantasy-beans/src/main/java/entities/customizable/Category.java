package entities.customizable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
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
}
