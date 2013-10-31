package com.chy.service.impl;

import java.util.Date;

import com.chy.dao.ItemDao;
import com.chy.dao.StoryDao;
import com.chy.model.User;
import com.chy.model.Story.Item;
import com.chy.model.Story.Story;
import com.chy.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("storyService")
@Transactional(readOnly = true)
public class StoryServiceImpl extends AbstractServiceImpl<Story, String>
		implements StoryService {
	StoryDao dao;
	
	@Autowired
	public StoryServiceImpl(StoryDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Autowired
	ItemDao itemDao;

	@Transactional(readOnly=false)
	@Override
	public String addStory(String title, String header, String rule,User user) {
		Story story=new Story();
		story.setAuthor(user);
		story.setRule(rule);
		story.setTitle(title);
		story.getUserList().add(user);
		story.setCreateDate(new Date());
		story.setModificationdDate(new Date());
		dao.saveOrUpdate(story);
		Item item=new Item();
		item.setAuthor(user);
		item.setStory(story);
		item.setText(header);
		item.setCreateDate(new Date());
		item.setModificationdDate(new Date());
		itemDao.saveOrUpdate(item);
		return story.getId();
	}
}