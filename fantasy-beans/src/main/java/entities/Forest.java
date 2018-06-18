package entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class Forest implements Serializable {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  private int trees;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "forest")
  private Collection<Elf> elves;

  @ManyToOne(optional = false)
  @JsonIgnore
  private User user;

  @Transient
  @JsonInclude
  private int userId;

  @JsonGetter("userId")
  public int getUserId() {
    return user.getId();
  }

  public Forest(String name, int trees, User user) {
    this.name = name;
    this.trees = trees;
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTrees() {
    return trees;
  }

  public void setTrees(int trees) {
    this.trees = trees;
  }

  public Collection<Elf> getElves() {
    return elves;
  }

  public void setElves(Collection<Elf> elves) {
    this.elves = elves;
  }

  @XmlTransient
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Forest forest = (Forest) o;
    return id == forest.id &&
      trees == forest.trees &&
      userId == forest.userId &&
      Objects.equals(name, forest.name) &&
      Objects.equals(elves, forest.elves) &&
      Objects.equals(user, forest.user);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, trees, elves, user, userId);
  }
}
