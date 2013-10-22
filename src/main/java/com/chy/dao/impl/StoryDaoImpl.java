package com.chy.dao.impl;

import com.chy.dao.StoryDao;
import com.chy.model.Story.Story;
import org.springframework.stereotype.Repository;

@Repository
public class StoryDaoImpl extends AbstractDaoImpl<Story, String>
  implements StoryDao
{
  protected StoryDaoImpl()
  {
    super(Story.class);
  }
}