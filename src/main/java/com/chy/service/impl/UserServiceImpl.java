package com.chy.service.impl;

import com.chy.dao.UserDao;
import com.chy.model.User;
import com.chy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl extends AbstractServiceImpl<User, String>
  implements UserService
{
  private UserDao dao;

  @Autowired
  public UserServiceImpl(UserDao dao)
  {
    super(dao);
    this.dao = dao;
  }

  public User login(String account, String password)
  {
    return this.dao.login(account, password);
  }
}