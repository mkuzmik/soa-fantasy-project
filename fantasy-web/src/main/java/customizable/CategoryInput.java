package customizable;

import lombok.Data;

@Data
public class CategoryInput {
  private String name;
  private int fieldValue;
  private int categoryDefinitionId;
}
