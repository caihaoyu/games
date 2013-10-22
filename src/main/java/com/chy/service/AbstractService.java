package com.chy.service;

import java.io.Serializable;
import java.util.List;

public abstract interface AbstractService<E, I extends Serializable>
{
  public abstract List<E> findAll();

  public abstract E saveOrUpdate(E paramE);

  public abstract void delete(E paramE);

  public abstract E get(I paramI);
}