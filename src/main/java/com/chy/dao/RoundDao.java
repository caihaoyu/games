package com.chy.dao;

import com.chy.model.Round;

public abstract interface RoundDao extends AbstractDao<Round, String>
{
  public abstract void delete(Round paramRound);

  public abstract Round saveOrUpdate(Round paramRound);
}