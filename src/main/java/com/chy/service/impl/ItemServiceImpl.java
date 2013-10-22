package com.chy.service.impl;

import com.chy.dao.ItemDao;
import com.chy.model.Story.Item;
import com.chy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("itemService")
@Transactional(readOnly=true)
public class ItemServiceImpl extends AbstractServiceImpl<Item, String>
  implements ItemService
{
  @Autowired
  public ItemServiceImpl(ItemDao dao)
  {
    super(dao);
  }
}