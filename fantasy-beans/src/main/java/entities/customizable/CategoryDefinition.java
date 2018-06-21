package entities.customizable;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class CategoryDefinition implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  @Column(unique = true)
  private String name;
  private String fieldName;

  @OneToMany(mappedBy = "categoryDefinition")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Category> categories;

  @Embedded
  private ElementDefinition elementDefinition;

  public CategoryDefinition(String name, String fieldName, ElementDefinition elementDefinition) {
    this.name = name;
    this.fieldName = fieldName;
    this.elementDefinition = elementDefinition;
  }
}
