package com.chy.games;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chy.model.User;
import com.chy.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/Spring-config.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserTest {

	private static User user = new User();
	private static User user2 = new User();

	@Autowired
	private UserService service;
	
	@Autowired
    private ApplicationContext applicationContext;


	@Test
	@Transactional
	@Rollback(false)
	public void testSave() {
		user = new User();
		user.setNickname("test a");
		service.saveOrUpdate(user);
		user2 = new User();
		user2.setNickname("test b");
		service.saveOrUpdate(user2);
		user.getFriends().add(user2);
		service.saveOrUpdate(user);
		user2 = service.get(user2.getUserId());
		user2.getFriendOf().add(user);
		assertNotNull(user.getUserId());
		assertNotNull(user2.getUserId());
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testFirends() {
		user2=service.get(user2.getUserId());
		assertEquals(user2.getFriendOf().size(), 1);
		user= service.get(user.getUserId());
		user2.getFriends().add(user);
		service.saveOrUpdate(user2);
		service.delete(user);
		service.delete(user2);
	}
	
	@Test
	@Transactional
	public void testStory(){
		
		
	}

}
