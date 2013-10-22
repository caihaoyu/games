package com.chy.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;

public abstract interface AbstractDao<E, I extends Serializable>
{
  public abstract E saveOrUpdate(E paramE);

  public abstract E findById(I paramI);

  public abstract List<E> findAll();

  public abstract void delete(E paramE);

  public abstract List<E> findByCriteria(Criterion paramCriterion);
}