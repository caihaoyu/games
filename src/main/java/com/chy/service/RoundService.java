package com.chy.service;

import com.chy.model.Round;
import java.util.List;

public abstract interface RoundService
{
  public abstract Round getRound(String paramString);

  public abstract Round saveOrUpdate(Round paramRound);

  public abstract void deleteRound(Round paramRound);

  public abstract List<Round> findAllRound();
}