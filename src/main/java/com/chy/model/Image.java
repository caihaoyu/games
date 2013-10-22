package com.chy.model;

import java.io.Serializable;
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
@Table(name="image")
public class Image extends BaseObject
  implements Serializable
{
  private static final long serialVersionUID = -2548160021943925677L;

  @Id
  @Column(name="id", length=32, nullable=false, unique=true)
  @GenericGenerator(name="generator", strategy="uuid.hex")
  @GeneratedValue(generator="generator")
  private String id;

  @Column(length=2097152)
  private String base64;

  @Column(name="create_date")
  private Date createDate;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="round_id")
  private Round round;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBase64() {
    return this.base64;
  }

  public void setBase64(String base64) {
    this.base64 = base64;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Round getRound() {
    return this.round;
  }

  public void setRound(Round round) {
    this.round = round;
  }

  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Image)) {
      return false;
    }
    Image image = (Image)o;

    return !(id != null ? !(image.getId().equals( id)) : image.getId() != null);
  }

  public int hashCode()
  {
    return ((this.id != null) ? this.id.hashCode() : 0);
  }
}