package elf;

import entities.Elf;
import lombok.Data;
import repos.ElfRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.List;

@ApplicationScoped
@Data
public class ElfHighscores {

  @EJB
  private ElfRepository elfRepository;

  private List<Elf> highScores;

  @PostConstruct
  public void init() {
    refetch();
  }

  public void onElfModification(@Observes ElfModificationEvent event) {
    refetch();
  }

  private void refetch() {
    highScores = elfRepository.getBest(5);
  }
}
