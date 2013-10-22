package com.chy.model;

import java.io.Serializable;

public abstract class BaseObject
  implements Serializable
{
  private static final long serialVersionUID = -7018051492221602377L;

  public abstract boolean equals(Object paramObject);

  public abstract int hashCode();
}