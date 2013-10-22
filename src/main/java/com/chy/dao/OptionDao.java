package com.chy.dao;

import com.chy.model.Option;
import java.util.Collection;
import java.util.List;

public abstract interface OptionDao extends AbstractDao<Option, String>
{
  public abstract List<Option> searchOptions(String paramString, Collection<String> paramCollection);

  public abstract Option getOptionByName(String paramString);
}