package com.chy.model.Story;

import com.chy.model.BaseObject;
import com.chy.model.User;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="item")
public class Item extends BaseObject
{
  private static final long serialVersionUID = 3057321048493293333L;

  @Id
  @Column(name="id", length=32, nullable=false, unique=true)
  @GenericGenerator(name="generator", strategy="uuid.hex")
  @GeneratedValue(generator="generator")
  private String id;

  @Column(length=2097152)
  private String text;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="author_id")
  private User author;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="story_id")
  private Story story;

  @Column
  private Date createDate;

  @Column
  private Date modificationdDate;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public User getAuthor() {
    return this.author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Story getStory() {
    return this.story;
  }

  public void setStory(Story story) {
    this.story = story;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getModificationdDate() {
    return this.modificationdDate;
  }

  public void setModificationdDate(Date modificationdDate) {
    this.modificationdDate = modificationdDate;
  }

  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Item item = (Item)o;

    return !(id != null ? !(item.getId().equals( id)) : item.getId() != null);
  }

  public int hashCode()
  {
    return ((this.id != null) ? this.id.hashCode() : 0);
  }
}