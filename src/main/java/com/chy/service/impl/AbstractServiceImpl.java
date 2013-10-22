package com.chy.service.impl;

import com.chy.dao.AbstractDao;
import com.chy.service.AbstractService;
import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AbstractServiceImpl<E, I extends Serializable>
  implements AbstractService<E, I>
{
  private AbstractDao<E, I> dao;

  public AbstractServiceImpl(AbstractDao<E, I> dao)
  {
    this.dao = dao;
  }

  @Transactional(readOnly=true)
  public List<E> findAll()
  {
    return this.dao.findAll();
  }

  @Transactional(readOnly=false)
  public E saveOrUpdate(E e)
  {
    return this.dao.saveOrUpdate(e);
  }

  @Transactional(readOnly=false)
  public void delete(E e)
  {
    this.dao.delete(e);
  }

  @Transactional(readOnly=true)
  public E get(I id)
  {
    return this.dao.findById(id);
  }
}