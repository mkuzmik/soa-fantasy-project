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
  private int fieldValue;
  private String enum1Value;
  private String enum2Value;

  @ManyToOne(optional = false)
  @JsonIgnore
  private Category category;
}
