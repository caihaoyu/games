package com.chy.service.impl;

import com.chy.dao.OptionDao;
import com.chy.model.Option;
import com.chy.service.OptionService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("optionService")
@Transactional(readOnly=true)
public class OptionServiceImpl extends AbstractServiceImpl<Option, String>
  implements OptionService
{
  private OptionDao dao;

  @Autowired
  public OptionServiceImpl(OptionDao dao)
  {
    super(dao);
    this.dao = dao;
  }

  @Transactional(readOnly=true)
  public List<Option> searchOptions(String query, Collection<String> ids)
  {
    return this.dao.searchOptions(query, ids);
  }

  @Transactional(readOnly=true)
  public Option getOptionByName(String name)
  {
    return this.dao.getOptionByName(name);
  }
}