package com.chy.dao;

import com.chy.model.User;

public abstract interface UserDao extends AbstractDao<User, String>
{
  public abstract User login(String paramString1, String paramString2);
}