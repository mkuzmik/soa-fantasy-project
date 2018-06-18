package entities.customizable;

import lombok.Data;

import java.io.Serializable;


@Data
public class ElementDefinition implements Serializable {

  private String elementName;
  private String elementFieldName;
  private String elementEnum1Name;
  private String elementEnum2Name;
}
