package com.chy.service.impl;

import com.chy.dao.RoundDao;
import com.chy.model.Round;
import com.chy.service.RoundService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roundService")
@Transactional(readOnly=true)
public class RoundServiceImpl
  implements RoundService
{

  @Autowired
  RoundDao dao;

  @Transactional(readOnly=true)
  public Round getRound(String id)
  {
    return ((Round)this.dao.findById(id));
  }

  @Transactional(readOnly=false)
  public Round saveOrUpdate(Round round)
  {
    return this.dao.saveOrUpdate(round);
  }

  @Transactional(readOnly=false)
  public void deleteRound(Round round)
  {
    this.dao.delete(round);
  }

  @Transactional(readOnly=true)
  public List<Round> findAllRound()
  {
    return this.dao.findAll();
  }
}