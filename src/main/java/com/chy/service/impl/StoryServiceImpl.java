package com.chy.service.impl;

import com.chy.dao.StoryDao;
import com.chy.model.Story.Story;
import com.chy.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("storyService")
@Transactional(readOnly=true)
public class StoryServiceImpl extends AbstractServiceImpl<Story, String>
  implements StoryService
{
  @Autowired
  public StoryServiceImpl(StoryDao dao)
  {
    super(dao);
  }
}