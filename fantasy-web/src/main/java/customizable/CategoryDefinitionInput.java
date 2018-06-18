package customizable;

import entities.customizable.ElementDefinition;
import lombok.Data;

@Data
public class CategoryDefinitionInput {
  private String name;
  private String fieldName;
  private ElementDefinition elementDefinition;
}
