package com.chy.service;

import com.chy.model.Option;
import java.util.Collection;
import java.util.List;

public abstract interface OptionService extends AbstractService<Option, String>
{
  public abstract List<Option> searchOptions(String paramString, Collection<String> paramCollection);

  public abstract Option getOptionByName(String paramString);
}