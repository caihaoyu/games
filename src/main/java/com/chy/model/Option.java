package com.chy.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="game_option")
public class Option extends BaseObject
{
  private static final long serialVersionUID = -6755758645823818162L;

  @Id
  @Column(name="id", length=32, nullable=false, unique=true)
  @GenericGenerator(name="generator", strategy="uuid.hex")
  @GeneratedValue(generator="generator")
  private String id;

  @Column
  private String name;

  @Column(length=2000)
  private String pinyinName;

  @ManyToMany(fetch=FetchType.LAZY, targetEntity=Round.class, mappedBy="options")
  private Set<Round> rounds=new HashSet<Round>();


  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Round> getRounds() {
    return this.rounds;
  }

  public void setRounds(Set<Round> rounds) {
    this.rounds = rounds;
  }

  public String getPinyinName() {
    return this.pinyinName;
  }

  public void setPinyinName(String pinyinName) {
    this.pinyinName = pinyinName;
  }

  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Option)) {
      return false;
    }
    Option option = (Option)o;

    return !(id != null ? !(option.getId().equals( id)) : option.getId() != null);
  }

  public int hashCode()
  {
    return ((this.id != null) ? this.id.hashCode() : 0);
  }
}