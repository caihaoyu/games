package com.chy.games;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chy.model.User;
import com.chy.model.Story.Item;
import com.chy.model.Story.Story;
import com.chy.service.ItemService;
import com.chy.service.StoryService;
import com.chy.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/Spring-config.xml"}) 
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true) 
public class StoryTest {
	
	@Autowired
	private StoryService service;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;
	
	private static User user;
	
	@Before
	@Transactional
	public void before(){
		user=new User();
		user.setNickname("testAdmin");
		user.setCreateDate(new Date());
		userService.saveOrUpdate(user);
	}
	
	
	@Test
	@Transactional
	public void testSave(){
		Story story=new Story();
		story.setTitle("test story");
		story.setAuthor(user);
		story.getUserList().add(user);
		service.saveOrUpdate(story);
		Item item=new Item();
		item.setAuthor(user);
		item.setStory(story);
		item.setText("This is test item text");
		itemService.saveOrUpdate(item);
		org.junit.Assert.assertNotNull(item.getId());
		org.junit.Assert.assertEquals(service.get(story.getId()),itemService.get(item.getId()).getStory());
		org.junit.Assert.assertEquals(userService.get(user.getUserId()),itemService.get(item.getId()).getAuthor());
		org.junit.Assert.assertNotNull(story.getId());
		user.getAuthorStories().add(story);
		userService.saveOrUpdate(user);
		org.junit.Assert.assertEquals(userService.get(user.getUserId()),service.get(story.getId()).getAuthor());
		org.junit.Assert.assertEquals(service.get(story.getId()).getUserList().size(), 1);
		service.delete(story);
		userService.delete(user);
		
	}

}
