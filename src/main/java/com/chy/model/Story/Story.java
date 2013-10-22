package com.chy.model.Story;

import com.chy.model.BaseObject;
import com.chy.model.User;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="story")
public class Story extends BaseObject
{
  private static final long serialVersionUID = -1710013705173760453L;

  @Id
  @Column(name="id", length=32, nullable=false, unique=true)
  @GenericGenerator(name="generator", strategy="uuid.hex")
  @GeneratedValue(generator="generator")
  private String id;

  @Column(length=1000)
  private String title;

  @Column(length=2000)
  private String rule;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="author_id")
  private User author;

  @ManyToMany(fetch=FetchType.LAZY, targetEntity=User.class)
  @JoinTable(name="story_user", joinColumns={@JoinColumn(name="story_id")}, inverseJoinColumns={@JoinColumn(name="user_id")})
  private Set<User> userList=new HashSet<User>(0);

  @OneToMany(fetch=FetchType.LAZY, mappedBy="story")
  @Cascade({org.hibernate.annotations.CascadeType.DELETE})
  private Set<Item> Items=new HashSet<Item>(0);

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

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getRule() {
    return this.rule;
  }

  public void setRule(String rule) {
    this.rule = rule;
  }

  public User getAuthor() {
    return this.author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Set<User> getUserList() {
    return this.userList;
  }

  public void setUserList(Set<User> userList) {
    this.userList = userList;
  }

  public Set<Item> getItems() {
    return this.Items;
  }

  public void setItems(Set<Item> items) {
    this.Items = items;
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
    if (!(o instanceof Story)) {
      return false;
    }
    Story story = (Story)o;

    return !(id != null ? !(story.getId().equals( id)) : story.getId() != null);
  }

  public int hashCode()
  {
    return ((this.id != null) ? this.id.hashCode() : 0);
  }
}