package entities.customizable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementDefinition implements Serializable {

  private String elementName;
  private String elementFieldName;
  private String elementEnum1Name;
  private String elementEnum2Name;
}
