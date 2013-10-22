package com.chy.dao.impl;

import com.chy.dao.ItemDao;
import com.chy.model.Story.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl extends AbstractDaoImpl<Item, String> implements
		ItemDao {
	protected ItemDaoImpl() {
		super(Item.class);
	}
}