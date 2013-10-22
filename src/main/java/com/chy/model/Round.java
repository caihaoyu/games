package com.chy.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="round")
public class Round extends BaseObject
{
  private static final long serialVersionUID = -409192716682157855L;

  @Id
  @Column(name="id", length=32, nullable=false, unique=true)
  @GenericGenerator(name="generator", strategy="uuid.hex")
  @GeneratedValue(generator="generator")
  private String id;

  @Column(nullable=false, length=45)
  private String answer;

  @Column(name="create_date")
  private Date createDate;

  @Column(name="edit_date")
  private Date editDate;

  @Column
  private String help;

  @OneToMany(fetch=FetchType.LAZY, mappedBy="round")
  @Cascade({org.hibernate.annotations.CascadeType.DELETE})
  private Set<Image> images=new HashSet<Image>(0);

  @ManyToMany(fetch=FetchType.LAZY, targetEntity=Option.class)
  @JoinTable(name="round_option", joinColumns={@javax.persistence.JoinColumn(name="round_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="option_id")})
  private Set<Option> options=new HashSet<Option>(0);;



  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAnswer()
  {
    return this.answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Set<Option> getOptions() {
    return this.options;
  }

  public void setOptions(Set<Option> options) {
    this.options = options;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getEditDate() {
    return this.editDate;
  }

  public void setEditDate(Date editDate) {
    this.editDate = editDate;
  }

  public String getHelp() {
    return this.help;
  }

  public void setHelp(String help) {
    this.help = help;
  }

  public Set<Image> getImages()
  {
    return this.images;
  }

  public void setImages(Set<Image> images) {
    this.images = images;
  }

  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Round)) {
      return false;
    }
    Round round = (Round)o;

    return !(id != null ? !(round.getId().equals( id)) : round.getId() != null);
  }

  public int hashCode()
  {
    return ((this.id != null) ? this.id.hashCode() : 0);
  }
}