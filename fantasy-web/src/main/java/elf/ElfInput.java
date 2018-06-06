package elf;

import entities.BowType;
import entities.PowerType;
import lombok.Data;

@Data
public class ElfInput {

  private String name;
  private int arrows;
  private BowType bowType;
  private PowerType powerType;
  private int forestId;
}
