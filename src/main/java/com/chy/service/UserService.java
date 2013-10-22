package com.chy.service;

import com.chy.model.User;

public abstract interface UserService extends AbstractService<User, String>
{
  public abstract User login(String paramString1, String paramString2);
}